package com.cy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableTransactionManagement
@EnableCaching
@EnableAsync //告诉springBoot启动异步配置
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		/*
		 * int pro=Runtime.getRuntime().availableProcessors();
		 * System.out.println("processors="+pro);
		 */
		SpringApplication.run(Application.class, args);
	}

}
