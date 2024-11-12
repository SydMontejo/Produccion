package com.api.apirest.configuraciones;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        //.allowedOrigins("http://localhost:4200")
                        .allowedOrigins("https://especificas.ingsistemascunori.org")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
                        .allowCredentials(true);
            }
        };
    }
}