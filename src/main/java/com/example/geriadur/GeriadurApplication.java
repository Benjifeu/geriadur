package com.example.geriadur;

import com.example.geriadur.repositories.SemanticFieldRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableJpaRepositories(basePackageClasses = SemanticFieldRepository.class)

public class GeriadurApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeriadurApplication.class, args);
	}

}