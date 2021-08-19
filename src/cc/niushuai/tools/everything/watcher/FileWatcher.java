package cc.niushuai.tools.everything.watcher;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchService;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;


public class FileWatcher {
	
	/*
	 * 整形     1 2 3 4 -1 -2 -3 -4
	 * 浮点型    1.1 1.2  3.4  1.2									1.111111      1.1111111111111111
	 * 字符型   a b c d e f g 1 * /. 
	 * 布尔型   true false   34 > 32  == true  2 > 3 == false
	 * 
	 * c  c++ c#
	 * java python go shell rube javascript 
	 * lua 易语言 
	 * 
	 * short、int、long、char、float、double
	 * 
	 */
	
	private static Logger log = LoggerFactory.getLogger(FileWatcher.class);
	
	private static WatchService watchService = null;
	
	private FileWatcher() {
	}
	
	public static WatchService getWatchService() throws Exception {
		if (null == watchService) {
			watchService = FileSystems.getDefault().newWatchService();
		}
		return watchService;
	}
	
	public static void register(String directory) throws Exception {
		Path path = Paths.get(directory);
		
		path.register(getWatchService(), StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE);
		log.debug("{} 已注册监听", directory);
	}
}
