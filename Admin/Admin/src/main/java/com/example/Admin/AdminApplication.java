package com.example.Admin;

import com.example.Admin.dto.BankHolidayResponse;
import com.example.Admin.entity.BankHolidayDays;
import com.example.Admin.repository.BankHolidayRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@SpringBootApplication

public class AdminApplication {

    public static void main(String[] args) {SpringApplication.run(AdminApplication.class, args);}



	@Bean
	public WebClient webClient() {
		return WebClient.builder().build();
	}


}













