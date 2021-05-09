package com.kafka.app;

import com.kafka.app.producer.NetworkDataProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.EnableKafka;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
@SpringBootApplication
@EnableKafka
public class ApacheKafkaAppApplication {


	private final NetworkDataProducer networkDataProducer;

	@Autowired
	public ApacheKafkaAppApplication(NetworkDataProducer networkDataProducer) {
		this.networkDataProducer = networkDataProducer;
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(ApacheKafkaAppApplication.class)
					.bannerMode(Banner.Mode.OFF)
					.build()
					.run(args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void init() {
		try {
			byte[] mapData = Files.readAllBytes(Paths.get((System.getProperty("user.dir") + "/data/packages.json")));
			String data = new String(mapData, StandardCharsets.UTF_8);
			networkDataProducer.sendMessage(data);

		} catch (IOException e) {
			log.error("Error ", e);
		}
	}
}
