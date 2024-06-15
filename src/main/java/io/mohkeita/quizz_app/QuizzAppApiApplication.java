package io.mohkeita.quizz_app;

import io.mohkeita.quizz_app.model.Role;
import io.mohkeita.quizz_app.model.RoleName;
import io.mohkeita.quizz_app.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class QuizzAppApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizzAppApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(RoleRepository roleRepository) {
		return args -> {
			if (roleRepository.findByName(RoleName.USER).isEmpty()) {
				roleRepository.save(
						Role.builder()
								.name(RoleName.USER)
								.build()
				);
			}
		};
	};

}
