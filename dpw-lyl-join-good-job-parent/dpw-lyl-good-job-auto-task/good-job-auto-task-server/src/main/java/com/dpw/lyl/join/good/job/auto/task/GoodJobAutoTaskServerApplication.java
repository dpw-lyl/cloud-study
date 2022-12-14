package com.dpw.lyl.join.good.job.auto.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class GoodJobAutoTaskServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodJobAutoTaskServerApplication.class, args);
	}

}
