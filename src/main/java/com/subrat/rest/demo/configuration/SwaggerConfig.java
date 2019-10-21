package com.subrat.rest.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
					.apiInfo(getApiInfo())
					.select()
					.apis(RequestHandlerSelectors.basePackage("com.subrat.rest.demo"))
					.paths(PathSelectors.ant("/users/**"))
					.build();
	}
	
	//Swagger Metadata - http://localhost:8080/v2/api-docs
	//Swagger UI Url - http://localhost:8080/swagger-ui.html
	
	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder()
					.title("Spring Boot Rest App For User Management Service")
					.description("This page lists all apis of User Management")
					.version("2.0")
					.contact(new Contact("John Doe", "https://johndoetest.com", "johndoe@email.com"))
					.license("Licence 2.0")
					.licenseUrl("https://johndoetest.com/license.html")
					.build();
	}
}
