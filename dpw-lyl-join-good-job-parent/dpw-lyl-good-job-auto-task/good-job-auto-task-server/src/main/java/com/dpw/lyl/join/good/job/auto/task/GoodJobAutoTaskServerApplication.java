package com.dpw.lyl.join.good.job.auto.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class GoodJobAutoTaskServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodJobAutoTaskServerApplication.class, args);
		log.info("GoodJobAutoTaskServerApplication服务启动成功");

	}

}
