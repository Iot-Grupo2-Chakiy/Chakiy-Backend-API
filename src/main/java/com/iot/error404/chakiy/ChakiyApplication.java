package com.iot.error404.chakiy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ChakiyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChakiyApplication.class, args);
	}

}
