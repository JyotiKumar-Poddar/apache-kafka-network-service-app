package com.kafka.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Summary of the analyzed data.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SummaryData {

	// alarm and frequency
	Map<String, Long> alarmFrequencyData;

	// node with alarm
	Map<String, Integer> nodeAlarmData;

	// ERA015 alarm hourly
	Map<Integer, Integer> alarmData;
}
