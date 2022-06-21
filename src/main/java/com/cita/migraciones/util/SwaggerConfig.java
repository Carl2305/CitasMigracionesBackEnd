package com.cita.migraciones.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
    public Docket docs() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

	private ApiInfo apiInfo() {
	    return new ApiInfo(
	            "Citas Migraciones 2022",
	            "API for Citas Migraciones 2022",
	            "1.0",
	            "Terms of service",
	            new springfox.documentation.service.Contact("Max", "http://citasmigraciones.azurewebsites.net", "@cibertec.edu.pe"),
	            "Apache License Version 2.0",
	            "https://www.apache.org/licenses/LICENSE-2.0",
	            Collections.emptyList());
	}
}
