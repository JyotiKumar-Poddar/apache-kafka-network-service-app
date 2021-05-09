package com.kafka.app.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.app.model.MessageData;
import com.kafka.app.model.Metadata;
import com.kafka.app.model.SummaryData;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;

import java.time.temporal.ChronoField;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

/**
 * Util calls to analyze the data
 */
@Slf4j
public class SummaryDataUtil {

	public static SummaryData getSummaryData(List<MessageData> networkMessages) {
		ObjectMapper mapper = new ObjectMapper();
		List<Metadata> metadataList = networkMessages.stream().map(v -> {
			try {
				return mapper.readValue(v.getMetadata(), Metadata.class);
			} catch (JsonProcessingException e) {
				log.warn("Error in parsing",e);
				return null;
			}
		}).filter(Objects::nonNull).collect(Collectors.toList());

		Map<String, Long> alarmFrequencyData = metadataList
					.stream()
					.collect(groupingBy(Metadata::getVnocAlarmID, Collectors.counting()));

		log.info("alarmFrequencyData count  {} ", alarmFrequencyData.size());  // sort based on frequency

		Map<String, Integer> nodeAlarmCount = new HashMap<>();

		metadataList.stream()
					.filter(v -> Strings.isNotBlank(v.getAffectedNode())).forEach(v -> {
			if (nodeAlarmCount.containsKey(v.getAffectedNode())) {
				int count = nodeAlarmCount.get(v.getAffectedNode()) + 1;
				nodeAlarmCount.put(v.getAffectedNode(), count);
			} else {
				nodeAlarmCount.put(v.getAffectedNode(), 1);
			}
		});
		Map<String, Integer> alarmData = nodeAlarmCount
					.entrySet()
					.stream()
					.sorted(comparingByValue())
					.collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
		log.info("nodes by alarms count  {} ", alarmData.size());

		Map<Integer, Integer> hourlyData = metadataList
					.stream()
					.filter(v -> "ERA015".equalsIgnoreCase(v.getVnocAlarmID()))
					.collect(groupingBy(x -> x.getAlarmEventTime().get(ChronoField.HOUR_OF_DAY)))
					.entrySet()
					.stream()
					.collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().size()));

		IntStream.rangeClosed(0, 23).forEach(v -> {
			if (!hourlyData.containsKey(v)) {
				hourlyData.put(v, 0);
			}
		});

		log.info("Hourly by data by count  {} ", hourlyData.size());
		SummaryData summaryData = new SummaryData();
		summaryData.setAlarmFrequencyData(alarmFrequencyData);
		summaryData.setNodeAlarmData(alarmData);
		summaryData.setAlarmData(hourlyData);
		return summaryData;
	}
}
