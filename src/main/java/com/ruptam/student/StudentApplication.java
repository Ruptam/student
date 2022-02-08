package com.ruptam.student;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class StudentApplication {

	@Value("${address.application.url}")
	private String addressApplicationUrl;

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}

	@Bean
	public WebClient webClient() {
		WebClient webClient = WebClient.builder().baseUrl(addressApplicationUrl).build();
		return webClient;
	}

}
