package com.dpw.lyl.join.good.job.iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

/**
  * @description:
  * @author: Administrator
  * @create: 2023/9/8 0008 16:43
  * @version: 1.0.1
 */
@EnableAsync
@EnableFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class GoodJobIotWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodJobIotWebApplication.class, args);
	}

}
