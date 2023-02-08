package com.dpw.lyl.join.good.job.system;

import com.dpw.lyl.join.good.job.foundation.security.annotation.EnableCustomConfig;
import com.dpw.lyl.join.good.job.foundation.security.annotation.EnableRyFeignClients;
import com.dpw.lyl.join.good.job.foundation.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringBootApplication
public class GoodJobSystemServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodJobSystemServerApplication.class, args);
	}

}
