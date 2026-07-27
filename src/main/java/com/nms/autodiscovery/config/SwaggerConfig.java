package com.nms.autodiscovery.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Auto Discovery Service API")
                        .description("Network Management System Auto Discovery APIs")
                        .version("1.0")
                        .contact(new Contact()
                                .name("NMS Team")
                                .email("support@nms.com")));
    }
}