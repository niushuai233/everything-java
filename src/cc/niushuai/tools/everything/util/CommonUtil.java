package cc.niushuai.tools.everything.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.system.oshi.OshiUtil;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HWPartition;

public class CommonUtil {
	
	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);
	
	private static ExecutorService executorService = null;
	
	public static ExecutorService getExecutorService() {
		return executorService;
	}
	
	public static List<String> listDisk() {
		
		List<String> diskPath = new ArrayList<String>(8);
		
		List<HWDiskStore> diskStores = OshiUtil.getDiskStores();
		diskStores.forEach(item -> {
			List<HWPartition> partitions = item.getPartitions();
			
			partitions.forEach(p -> {
				String name = p.getName();
				String type = p.getType();
				String mountPoint = p.getMountPoint();
				log.debug("发现磁盘 name = " + name + ", type = " + type + ", mountPoint = " + mountPoint);
				diskPath.add(mountPoint);
			});
		});
		return diskPath;
	}
	
	public static void deleteDefaultSchema() {
		try {
			DbUtil.getDb().execute("DROP TABLE IF EXISTS t_file_his;", null);
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static void createDefaultSchema() {
		try {
			DbUtil.getDb().execute("create table t_file_his\r\n"
					+ "(\r\n"
					+ "    id char(32) primary key,\r\n"
					+ "    name char(512),\r\n"
					+ "    size char(512),\r\n"
					+ "    type char(512),\r\n"
					+ "    file_imei char(512),\r\n"
					+ "    disk char(512),\r\n"
					+ "    path char(512),\r\n"
					+ "    absolute_path char(512),\r\n"
					+ "    create_time char(255),\r\n"
					+ "    update_time char(255)\r\n"
					+ ")", null);
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		List<String> listDisk = listDisk();
		listDisk.forEach(System.out::println);
		
	}
	
	
	static {
		executorService = ExecutorBuilder.create()
				.setMaxPoolSize(30000)
				.buildFinalizable();
	}
}
