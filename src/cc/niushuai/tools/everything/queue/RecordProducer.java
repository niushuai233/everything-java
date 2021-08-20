package cc.niushuai.tools.everything.queue;

import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.niushuai.tools.everything.entity.FileEntity;
import cn.hutool.db.Entity;

public class RecordProducer {

	private static Logger log = LoggerFactory.getLogger(RecordProducer.class);

	private final BlockingQueue<Entity> blockingQueue;

	public RecordProducer(BlockingQueue<Entity> blockingQueue) {
		log.info("生产者准备接收数据...");
		this.blockingQueue = blockingQueue;
	}

	public void sendMessage(Entity record) throws Exception {
		blockingQueue.put(record);
		log.info("put {} to queue", record.getStr(FileEntity.FIELD_ABSOLUTE_PATH));
	}
}
