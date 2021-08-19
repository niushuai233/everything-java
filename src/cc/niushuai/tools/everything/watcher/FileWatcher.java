package cc.niushuai.tools.everything.watcher;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileWatcher {
	
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
		log.info("{} 已注册监听", directory);
	}
}
