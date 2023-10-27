package com.joo.everyletter_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class EveryLetterBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(EveryLetterBackApplication.class, args);
	}

}
