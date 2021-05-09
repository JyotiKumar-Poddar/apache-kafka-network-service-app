package com.kafka.app.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.app.model.MessageData;
import com.kafka.app.model.SummaryData;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SummaryDataUtilTest {

	@Test
	public void isSummaryCorrect() {
		List<MessageData> networkMessages = new ArrayList<>();
		SummaryData summaryData = SummaryDataUtil.getSummaryData(networkMessages);
		assertEquals(summaryData.getAlarmData().size(),24);
		assertTrue(summaryData.getAlarmFrequencyData().isEmpty());
		assertTrue(summaryData.getNodeAlarmData().isEmpty());
	}

	@Test
	public void isSummary_Alarm_ERA015_Correct() throws JsonProcessingException {

		String data = "[ {\n" +
					"        \"metadata\": \"{\\\"affectedNode\\\":\\\"LX0006\\\",\\\"affectedSite\\\":\\\"LX0006\\\",\\\"alarmCategory\\\":\\\"UNKNOWN\\\",\\\"alarmGroup\\\":\\\"003--936265541-SubNetwork=Osijek,MeContext=LX0006,ManagedElement=LX0006,ENodeBFunction=1,NbIotCell=bj_tkc-1469856\\\",\\\"alarmCSN\\\":\\\"1469856\\\",\\\"alarmID\\\":\\\"9175114\\\",\\\"alarmMO\\\":\\\"SubNetwork=Osijek,MeContext=LX0006,ManagedElement=LX0006,ENodeBFunction=1,NbIotCell=bj_tkc\\\",\\\"alarmNotificationType\\\":\\\"Major\\\",\\\"alarmLastSeqNo\\\":\\\"1469856\\\",\\\"alarmEventTime\\\":\\\"2020-01-22T11:33:32+02:00\\\",\\\"vnocAlarmID\\\":\\\"ERA015\\\"}\"\n" +
					"    },\n" +
					"    {\n" +
					"        \"metadata\": \"{\\\"affectedNode\\\":\\\"LX00013\\\",\\\"affectedSite\\\":\\\"LX00013\\\",\\\"alarmCategory\\\":\\\"FAULT\\\",\\\"alarmGroup\\\":\\\"003--936265541-SubNetwork=Osijek,MeContext=LX00013,ManagedElement=LX00013,ENodeBFunction=1,NbIotCell=severin_bj-1469856\\\",\\\"alarmCSN\\\":\\\"1469856\\\",\\\"alarmID\\\":\\\"9175114\\\",\\\"alarmMO\\\":\\\"SubNetwork=Osijek,MeContext=LX00013,ManagedElement=LX00013,ENodeBFunction=1,NbIotCell=severin_bj\\\",\\\"alarmNotificationType\\\":\\\"Major\\\",\\\"alarmLastSeqNo\\\":\\\"1469856\\\",\\\"alarmEventTime\\\":\\\"2020-01-24T09:21:03+02:00\\\",\\\"vnocAlarmID\\\":\\\"ERA015\\\"}\"\n" +
					"    },\n" +
					"    {\n" +
					"        \"metadata\": \"{\\\"affectedNode\\\":\\\"LX00011\\\",\\\"affectedEquipment\\\":\\\"RRU-B1B3-S1-2\\\",\\\"affectedSite\\\":\\\"LX00011\\\",\\\"alarmCategory\\\":\\\"FAULT\\\",\\\"alarmGroup\\\":\\\"003--1143982548-SubNetwork=Zagreb,MeContext=LX00011,ManagedElement=LX00011,Equipment=1,FieldReplaceableUnit=RRU-B1B3-S1-2-1460077\\\",\\\"alarmCSN\\\":\\\"1460077\\\",\\\"alarmID\\\":\\\"9175147\\\",\\\"alarmMO\\\":\\\"SubNetwork=Zagreb,MeContext=LX00011,ManagedElement=LX00011,Equipment=1,FieldReplaceableUnit=RRU-B1B3-S1-2\\\",\\\"alarmNotificationType\\\":\\\"Minor\\\",\\\"alarmLastSeqNo\\\":\\\"1460077\\\",\\\"alarmEventTime\\\":\\\"2020-01-24T09:21:03+02:00\\\",\\\"vnocAlarmID\\\":\\\"ERA005\\\"}\"\n" +
					"    },\n" +
					"    {\n" +
					"        \"metadata\": \"{\\\"affectedEquipment\\\":\\\"RRU-B1B3-S1-2\\\",\\\"alarmCategory\\\":\\\"FAULT\\\",\\\"alarmGroup\\\":\\\"003--1143982548-SubNetwork=Zagreb,MeContext=,ManagedElement=,Equipment=1,FieldReplaceableUnit=RRU-B1B3-S1-2-1460077\\\",\\\"alarmCSN\\\":\\\"1460077\\\",\\\"alarmID\\\":\\\"9175147\\\",\\\"alarmMO\\\":\\\"SubNetwork=Zagreb,MeContext=,ManagedElement=,Equipment=1,FieldReplaceableUnit=RRU-B1B3-S1-2\\\",\\\"alarmNotificationType\\\":\\\"Minor\\\",\\\"alarmLastSeqNo\\\":\\\"1460077\\\",\\\"alarmEventTime\\\":\\\"2020-01-24T09:21:51+02:00\\\",\\\"vnocAlarmID\\\":\\\"ERA005\\\"}\"\n" +
					"    },\n" +
					"    {\n" +
					"        \"metadata\": \"{\\\"affectedNode\\\":\\\"LX0006\\\",\\\"affectedEquipment\\\":\\\"RRU-B8-S2\\\",\\\"affectedSite\\\":\\\"LX0006\\\",\\\"alarmCategory\\\":\\\"UNKNOWN\\\",\\\"alarmGroup\\\":\\\"003--1143978760-SubNetwork=Rijeka,MeContext=LX0006,ManagedElement=LX0006,Equipment=1,FieldReplaceableUnit=RRU-B8-S2,RfPort=A-1460123\\\",\\\"alarmCSN\\\":\\\"1460123\\\",\\\"alarmID\\\":\\\"0\\\",\\\"alarmMO\\\":\\\"SubNetwork=Rijeka,MeContext=LX0006,ManagedElement=LX0006,Equipment=1,FieldReplaceableUnit=RRU-B8-S2,RfPort=A\\\",\\\"alarmNotificationType\\\":\\\"Minor\\\",\\\"alarmLastSeqNo\\\":\\\"1460123\\\",\\\"alarmEventTime\\\":\\\"2020-01-24T09:22:15+02:00\\\",\\\"vnocAlarmID\\\":\\\"ERA021\\\"}\"\n" +
					"    },\n" +
					"    {\n" +
					"        \"metadata\": \"{\\\"affectedNode\\\":\\\"LX00016\\\",\\\"affectedSite\\\":\\\"LX00016\\\",\\\"alarmCategory\\\":\\\"FAULT\\\",\\\"alarmGroup\\\":\\\"003--936265541-SubNetwork=Osijek,MeContext=LX00016,ManagedElement=LX00016,ENodeBFunction=1,NbIotCell=sandrovac-1469856\\\",\\\"alarmCSN\\\":\\\"1469856\\\",\\\"alarmID\\\":\\\"9175114\\\",\\\"alarmMO\\\":\\\"SubNetwork=Osijek,MeContext=LX00016,ManagedElement=LX00016,ENodeBFunction=1,NbIotCell=sandrovac\\\",\\\"alarmNotificationType\\\":\\\"Major\\\",\\\"alarmLastSeqNo\\\":\\\"1469856\\\",\\\"alarmEventTime\\\":\\\"2020-01-24T09:23:03+02:00\\\",\\\"vnocAlarmID\\\":\\\"ERA015\\\"}\"\n" +
					"    } ]";
		ObjectMapper mapper = new ObjectMapper();
		List<MessageData> networkMessages = mapper.readValue(data, new TypeReference<List<MessageData>>() {
		});
		SummaryData summaryData = SummaryDataUtil.getSummaryData(networkMessages);
		Map<Integer, Integer> alarmData = summaryData.getAlarmData();
		assertFalse(alarmData.isEmpty());
		assertFalse(summaryData.getAlarmFrequencyData().isEmpty());
		Map<String, Integer> nodeAlarmData = summaryData.getNodeAlarmData();
		assertFalse(nodeAlarmData.isEmpty());
		assertEquals(alarmData.get(9),2);
		assertEquals(alarmData.get(11),1);

	}

}