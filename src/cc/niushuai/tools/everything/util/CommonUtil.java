package cc.niushuai.tools.everything.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import org.apache.commons.io.FileUtils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.system.oshi.OshiUtil;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HWPartition;

public class CommonUtil {
	private static ExecutorService executorService = null;
	
	public static ExecutorService getExecutorService() {
		return executorService;
	}
	
	public static List<String> listDisk() {
		
		List<String> diskPath = new ArrayList<String>(8);
		
		File[] roots = File.listRoots();
		
		for (int i = 0; i< roots.length; i++) {
			File root = roots[i];
			
			diskPath.add(root.getAbsolutePath());
		}
		
		return diskPath;
	}
	
	public static void main(String[] args) {
		
		
		List<String> listDisk = listDisk();
		
		listDisk.forEach(System.out::println);
		
		List<HWDiskStore> diskStores = OshiUtil.getDiskStores();
		
		diskStores.forEach(item -> {
			List<HWPartition> partitions = item.getPartitions();
			
			partitions.forEach(p -> {
				String name = p.getName();
				String type = p.getType();
				System.out.println("name = " + name + ", type = " + type);
			});
			
		});
		
	}
	
	
	
	static {
		executorService = ExecutorBuilder.create()
				.buildFinalizable();
				
	}
}
