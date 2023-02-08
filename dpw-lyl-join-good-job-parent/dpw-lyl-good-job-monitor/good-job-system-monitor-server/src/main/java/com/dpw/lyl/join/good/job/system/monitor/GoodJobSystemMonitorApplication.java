package com.dpw.lyl.join.good.job.system.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class GoodJobSystemMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodJobSystemMonitorApplication.class, args);
	}

}
