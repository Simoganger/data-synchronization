package com.sprintgether.datasynchro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DataSynchroApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataSynchroApplication.class, args);
	}

}
