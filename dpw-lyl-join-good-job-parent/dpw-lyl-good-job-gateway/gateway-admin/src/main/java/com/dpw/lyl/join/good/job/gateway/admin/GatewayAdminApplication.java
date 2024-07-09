package com.dpw.lyl.join.good.job.gateway.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class GatewayAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayAdminApplication.class, args);
	}

}
