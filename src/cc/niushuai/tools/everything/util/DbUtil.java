package cc.niushuai.tools.everything.util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import cc.niushuai.tools.everything.queue.RecordConsumer;
import cc.niushuai.tools.everything.queue.RecordProducer;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;

public class DbUtil {
	
	private static Db db = null;
	
	public static final String DEFAULT_SCHEMA_NAME = "t_file_his";
	
	private static final BlockingQueue<Entity> blockingQueue = new LinkedBlockingQueue<Entity>();
	private static RecordProducer recordProducer = null;
	private static RecordConsumer recordConsumer = null;
	
	public static RecordProducer getRecordProducer() {
		return recordProducer;
	}
	
	public static RecordConsumer getRecordConsumer() {
		return recordConsumer;
	}
	
	public static Db getDb() {
		return db;
	}
	
	
	static {
		db = Db.use();
		
		recordProducer = new RecordProducer(blockingQueue);
		recordConsumer = new RecordConsumer(blockingQueue);
		
		CommonUtil.deleteDefaultSchema();
		CommonUtil.createDefaultSchema();
	}
}
