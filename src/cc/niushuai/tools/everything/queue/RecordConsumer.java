package cc.niushuai.tools.everything.queue;

import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.niushuai.tools.everything.entity.FileEntity;
import cc.niushuai.tools.everything.util.DbUtil;
import cn.hutool.db.Entity;

public class RecordConsumer implements Runnable {
	
	private static final Logger log = LoggerFactory.getLogger(RecordConsumer.class);
	
    private final BlockingQueue<Entity> blockingQueue;

    public RecordConsumer(BlockingQueue<Entity> blockingQueue) {
		log.info("消费者准备处理数据...");
        this.blockingQueue = blockingQueue;
    }
	
	@Override
	public void run() {
		log.info("开始运行消费者存储数据...");
		while (true) {
			try {
				
				if (!blockingQueue.isEmpty()) {
					Entity record = blockingQueue.take();
					DbUtil.getDb().insert(record);
					log.info("save {} to h2", record.getStr(FileEntity.FIELD_ABSOLUTE_PATH));
				}
			} catch (Exception e) {
				log.error("save error {}", e.getMessage(), e);
			}
		}
	}

}
