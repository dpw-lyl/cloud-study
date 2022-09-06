package com.dpw.lyl.join.good.job.report.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
public class GoodJobReportWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodJobReportWebApplication.class, args);
	}

}
