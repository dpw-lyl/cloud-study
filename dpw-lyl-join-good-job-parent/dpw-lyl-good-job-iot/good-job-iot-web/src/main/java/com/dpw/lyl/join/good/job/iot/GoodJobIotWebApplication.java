package com.dpw.lyl.join.good.job.iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class GoodJobIotWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodJobIotWebApplication.class, args);
	}

}
