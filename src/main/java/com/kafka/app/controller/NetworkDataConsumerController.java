package com.kafka.app.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.app.constant.KafkaConfigConstant;
import com.kafka.app.model.MessageData;
import com.kafka.app.model.SummaryData;
import com.kafka.app.producer.NetworkDataProducer;
import com.kafka.app.util.SummaryDataUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller to get message from kafka topic.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Slf4j
public class NetworkDataConsumerController {

	private SummaryData summaryData = new SummaryData();

	private final NetworkDataProducer networkDataProducer;

	@Autowired
	public NetworkDataConsumerController(NetworkDataProducer networkDataProducer) {
		this.networkDataProducer = networkDataProducer;
	}
	@KafkaListener(groupId = KafkaConfigConstant.GROUP_ID,
				topics = KafkaConfigConstant.TOPIC_NAME,
				containerFactory = KafkaConfigConstant.KAFKA_LISTENER_CONTAINER_FACTORY)
	public void receivedMessage(ConsumerRecord<String, String> message) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		List<MessageData> networkMessages = mapper.readValue(message.value(), new TypeReference<List<MessageData>>() {
		});
		summaryData = SummaryDataUtil.getSummaryData(networkMessages);
	}

	@GetMapping("/consume/message")
	public SummaryData received() {
		return summaryData;
	}
	@GetMapping("/produce/message")  // for testing
	public String postMessage() {
		String data = "[ {\n" +
					"        \"metadata\": \"{\\\"affectedNode\\\":\\\"LX00010\\\",\\\"affectedSite\\\":\\\"LX00010\\\",\\\"alarmCategory\\\":\\\"UNKNOWN\\\",\\\"alarmGroup\\\":\\\"003--936265541-SubNetwork=Osijek,MeContext=LX00010,ManagedElement=LX00010,ENodeBFunction=1,NbIotCell=predavac-1469856\\\",\\\"alarmCSN\\\":\\\"1469856\\\",\\\"alarmID\\\":\\\"9175114\\\",\\\"alarmMO\\\":\\\"SubNetwork=Osijek,MeContext=LX00010,ManagedElement=LX00010,ENodeBFunction=1,NbIotCell=predavac\\\",\\\"alarmNotificationType\\\":\\\"Major\\\",\\\"alarmLastSeqNo\\\":\\\"1469856\\\",\\\"alarmEventTime\\\":\\\"2020-01-23T11:08:57+02:00\\\",\\\"vnocAlarmID\\\":\\\"ERA015\\\"}\"\n" +
					"    }]";
		networkDataProducer.sendMessage(data);
		return "done";
	}
}
