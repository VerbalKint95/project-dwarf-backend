package com.maligno.projectdwarf.springboot.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("*") // Autoriser les requêtes de n'importe quelle origine
            .allowedMethods("GET", "POST", "PUT", "DELETE") // Autoriser certaines méthodes HTTP
            .allowedHeaders("*"); // Autoriser tous les en-têtes
    }
}
