package com.sevyh.sevyhchatmediaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.sevyh.sevyhchatmediaservice")
public class SevyhchatmediaserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SevyhchatmediaserviceApplication.class, args);
	}

}
