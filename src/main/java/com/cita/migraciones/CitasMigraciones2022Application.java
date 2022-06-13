package com.cita.migraciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class CitasMigraciones2022Application {

	public static void main(String[] args) {
		SpringApplication.run(CitasMigraciones2022Application.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurer() {
	        @Override
	        public void addCorsMappings(CorsRegistry registry) {
	            registry.addMapping("/api/**").allowedOrigins("*").allowedMethods("GET", "POST","PUT", "DELETE");
	        }
	    };
	}

}
