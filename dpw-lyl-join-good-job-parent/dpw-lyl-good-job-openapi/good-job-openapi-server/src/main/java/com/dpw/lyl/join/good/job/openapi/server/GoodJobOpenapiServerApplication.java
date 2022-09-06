package com.dpw.lyl.join.good.job.openapi.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
public class GoodJobOpenapiServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodJobOpenapiServerApplication.class, args);
	}

}
