package com.dpw.lyl.join.good.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
public class GoodJobPayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodJobPayServerApplication.class, args);
	}

}
