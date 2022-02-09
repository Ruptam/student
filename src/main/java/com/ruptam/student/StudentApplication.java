package com.ruptam.student;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
// import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableFeignClients("com.ruptam.student.feignclients")
@EnableEurekaClient
public class StudentApplication {

	// @Value("${address.application.url}")
	// private String addressApplicationUrl;

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}

	// @Bean
	// public WebClient webClient() {
	// 	WebClient webClient = WebClient.builder().baseUrl(addressApplicationUrl).build();
	// 	return webClient;
	// }

}
