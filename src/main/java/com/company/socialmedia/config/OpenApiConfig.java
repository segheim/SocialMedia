package com.company.socialmedia.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI myOpenAPI() {

        Contact contact = new Contact();
        contact.setEmail("zheni11seg@gmail.com");
        contact.setName("Savitski");

        Info info = new Info()
                .title("Social Media")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to web site Social Media").termsOfService("https://localhost:8080/api/v1");
        return new OpenAPI().info(info);
    }
}
