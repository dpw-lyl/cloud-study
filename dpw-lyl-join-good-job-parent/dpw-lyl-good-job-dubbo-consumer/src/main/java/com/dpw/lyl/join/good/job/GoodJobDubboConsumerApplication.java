package com.dpw.lyl.join.good.job;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author Administrator
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableDubbo
public class GoodJobDubboConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodJobDubboConsumerApplication.class, args);
	}

}
