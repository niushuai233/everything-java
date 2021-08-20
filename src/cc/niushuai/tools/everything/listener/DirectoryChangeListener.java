package cc.niushuai.tools.everything.listener;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.niushuai.tools.everything.entity.FileEntity;
import cc.niushuai.tools.everything.enums.FileTypeEnum;
import cc.niushuai.tools.everything.queue.RecordConsumer;
import cc.niushuai.tools.everything.util.CommonUtil;
import cc.niushuai.tools.everything.util.DbUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.db.Entity;

public class DirectoryChangeListener {
	
	private static Logger log = LoggerFactory.getLogger(DirectoryChangeListener.class);
	
	public void start() {
		// 获取驱动器列表
		List<String> listDisk = CommonUtil.listDisk();
		
		// 从根目录一直遍历 需要使用递归
		for (String disk: listDisk) {
			CommonUtil.getExecutorService().execute(() -> {readFileList(disk, true);});
		}
	}
	
	public void readFileList(String directory, boolean deepLevel) {
		log.info("当前路径: {}", directory);
		if (directory.contains("node_modules")) {
			log.info("node_modules 文件夹跳过录入");
			return;
		}
		File file = new File(directory);
		// 先保存
		try {
			save(file);
		} catch (Exception e) {
			log.error("save {} error {}", directory, e.getMessage(), e);
		}
		
		if (file.isDirectory()) {
			log.info("{} 为文件夹 进入递归", directory);
			File[] rootFiles = file.listFiles();
			if (ArrayUtil.isEmpty(rootFiles)) {
				log.info("{} 目录为空文件夹", directory);
				return;
			}
			for(File currRootFile: rootFiles) {
				if (!deepLevel) {
					readFileList(currRootFile.getAbsolutePath(), false);
				} else {
					CommonUtil.getExecutorService().execute(() -> {readFileList(currRootFile.getAbsolutePath(), false);});
				}
			}
		}
	}
	
	public void save(File file) throws Exception {
		Entity record = Entity.create(DbUtil.DEFAULT_SCHEMA_NAME);
		record.set(FileEntity.FIELD_ID, IdUtil.objectId());
		record.set(FileEntity.FIELD_NAME, file.getName());
		record.set(FileEntity.FIELD_SIZE, file.length());
		record.set(FileEntity.FIELD_DISK, file.getAbsolutePath().substring(0, 1));
		record.set(FileEntity.FIELD_PATH, file.getPath());
		record.set(FileEntity.FIELD_TYPE, FileTypeEnum.FILE.getCode());
		record.set(FileEntity.FIELD_FILE_IMEI, FileUtil.getSuffix(file));
		record.set(FileEntity.FIELD_ABSOLUTE_PATH, file.getAbsolutePath());
		record.set(FileEntity.FIELD_CREATE_TIME, DateTime.of(file.lastModified()).toString());
		record.set(FileEntity.FIELD_UPDATE_TIME, DateUtil.now());
		
		// DbUtil.getDb().insert(record);
		DbUtil.getRecordProducer().sendMessage(record);
	}
	
	
	public static void main(String[] args) {
		
		DirectoryChangeListener listener = new DirectoryChangeListener();
		CommonUtil.getExecutorService().execute(() -> {listener.start();});
		new Thread(DbUtil.getRecordConsumer()).start();
		
	}
}
