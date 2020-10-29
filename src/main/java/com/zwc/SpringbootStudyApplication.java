package com.zwc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootStudyApplication {

	private final static Logger logger = LoggerFactory.getLogger(SpringbootStudyApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringbootStudyApplication.class, args);
		logger.info("================项目启动成功================");
	}

}
