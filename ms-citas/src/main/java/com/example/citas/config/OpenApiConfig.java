package com.example.citas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI citasApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Microservicio de Citas")
                        .version("v1")
                        .description("API REST para agendar, listar, consultar disponibilidad y cancelar citas"));
    }
}