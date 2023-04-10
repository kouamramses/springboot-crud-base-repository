package com.example.basespringbootproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
//                        .allowedHeaders("Origin", "Content-Type", "Accept", "X-Requested-With",
//                                "Access-Control-Request-Method", "Access-Control-Request-Headers")
//                        .exposedHeaders("Access-Control-Allow-Origin, Access-Control-Allow-Credentials")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS", "HEAD")
                        .maxAge(3600);
            }
        };
    }
}
