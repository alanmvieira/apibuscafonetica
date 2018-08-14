package com.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = { "api.controller", "com.repository"  })
public class Server implements WebMvcConfigurer {

	public static void main(String[] args) {

		SpringApplication.run(Server.class, args);

	}

}
