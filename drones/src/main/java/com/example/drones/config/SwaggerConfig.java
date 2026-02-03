package com.example.drones.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI infotmacionDelProyecto(){
        return new OpenAPI()
                .info(new Info()
                .title("API de Control de Drones") 
                .description("Esta API sirve para gestionar y controlar la posicion de drones y las ejecuciones de sus movimientos.") 
                .version("v1.0.0"));
    }
    
}
