package com.dpw.lyl.join.good.job.iot.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class GoodJobIotWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodJobIotWebApplication.class, args);
	}

}
