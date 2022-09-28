package com.makeitup.springsecurityjwtexample;

import com.makeitup.springsecurityjwtexample.entity.User;
import com.makeitup.springsecurityjwtexample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication

public class SpringSecurityJwtExampleApplication {

	@Autowired
	private UserRepository repository;

	@PostConstruct
	public void initUsers() {
		List<User> users = Stream.of(
				new User(101, "apo", "password", "apo@gmail.com"),
				new User(102, "user1", "pwd1", "user1@gmail.com"),
				new User(103, "user2", "pwd2", "user2@gmail.com"),
				new User(104, "user3", "pwd3", "user3@gmail.com")
		).collect(Collectors.toList());

		users.forEach(u -> {
			if (!repository.existsById(u.getId())) {
				repository.save(u);
			}
		});
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtExampleApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedHeaders("*").allowedOrigins("http://localhost:4200").allowedMethods("*")
						.allowCredentials(true);
			}
		};
	}

}
