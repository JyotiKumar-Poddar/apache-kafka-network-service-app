package com.kafka.app.producer;


import com.kafka.app.constant.KafkaConfigConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * Sending message to kafka topic
 */
@Service
@Slf4j
public class NetworkDataProducer {

	private final KafkaTemplate<String, String> kafkaTemplate;

	public NetworkDataProducer(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}


	public void sendMessage(String message) {

		ListenableFuture<SendResult<String, String>> resultListenableFuture = kafkaTemplate.send(KafkaConfigConstant.TOPIC_NAME, message);
		resultListenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onSuccess(SendResult<String, String> result) {
				log.info("Sent message with offset=[{}]", result.getRecordMetadata().offset());
			}

			@Override
			public void onFailure(Throwable ex) {
				log.error("Unable to resultListenableFuture message=[{}] due to : ", ex.getMessage());
			}
		});

	}
}
