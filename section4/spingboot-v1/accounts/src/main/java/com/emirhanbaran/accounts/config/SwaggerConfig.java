package com.emirhanbaran.accounts.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Account Microservice")
                        .version("1.0")
                        .description("This is a representation ofEazyBank account microservice REST API")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")
                        )
                        .contact(new Contact()
                                .email("emir.baran255@gmail.com")
                                .name("Emirhan Baran")
                                .url("https://www.linkedin.com/in/emirhanbaran0/")
                        )
                ).externalDocs(new ExternalDocumentation()
                        .description("Eazy Bank account microservice REST API Documentation")
                        .url("https://www.linkedin.com/in/emirhanbaran0/")
                );
    }
}
