package com.xiaoming.star;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(
		exclude= { DataSourceAutoConfiguration.class},
		scanBasePackages = "com.xiaoming.star"
)
public class StarApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(StarApplication.class, args);
	}


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(StarApplication.class);
	}

}
