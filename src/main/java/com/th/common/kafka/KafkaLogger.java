package com.th.common.kafka;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaLogger {

	private Logger qosTimebaseLogger = LoggerFactory.getLogger("qos.timebase");
	
	public void info(String message) {
		
		String time = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
		String fullFormatMessage = time + message;
		
		// Send message to Kafka (message)
		KafkaSender.send(fullFormatMessage);
		
		// Log 쌓기
		qosTimebaseLogger.info(message);
	}
	
	public void dedug(String message) {
		qosTimebaseLogger.debug(message);
	}
	
	
}
