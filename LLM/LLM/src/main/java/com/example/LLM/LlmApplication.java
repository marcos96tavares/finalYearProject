package com.example.LLM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LlmApplication {

	public static void main(String[] args) {
		SpringApplication.run(LlmApplication.class, args);
	}

}
