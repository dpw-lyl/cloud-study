package com.dpw.lyl.join.good.job.sso;

import com.dpw.lyl.join.good.job.foundation.security.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableRyFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class GoodJobSsoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodJobSsoApplication.class, args);
	}

}
