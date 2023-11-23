package com.minsait.Clasroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ClasroomApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClasroomApplication.class, args);
	}

}
