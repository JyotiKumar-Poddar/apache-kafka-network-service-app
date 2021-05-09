package com.kafka.app.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Representing each network event
 */
@Data
@NoArgsConstructor
public class Metadata {
	private String affectedNode;
	private String affectedEquipment;
	private String affectedSite;
	private String alarmCategory;
	private String alarmGroup;
	private String alarmCSN;
	private String alarmID;
	private String alarmMO;
	private String alarmNotificationType;
	private String alarmLastSeqNo;
	@JsonDeserialize(using = CustomDateTimeDeserializer.class)
	private LocalDateTime alarmEventTime;
	private String vnocAlarmID;


}
