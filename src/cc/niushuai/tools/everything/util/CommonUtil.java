package cc.niushuai.tools.everything.util;

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
	
	public static void main(String[] args) {
		List<String> listDisk = listDisk();
		listDisk.forEach(System.out::println);
		
	}
	
	
	static {
		executorService = ExecutorBuilder.create()
				.buildFinalizable();
				
	}
}
