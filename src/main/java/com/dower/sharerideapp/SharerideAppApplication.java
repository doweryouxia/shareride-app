package com.dower.sharerideapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dower.sharerideapp.domain.repository")
public class SharerideAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SharerideAppApplication.class, args);
	}
}
