package com.example.Home.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply to all API endpoints
                .allowedOrigins("http://localhost:5173", "http://localhost:5174", "http://localhost:5175") // Allow both React origins
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allowed HTTP methods
                .allowedHeaders("*"); // Allow all headers

        // Allow CORS for static resources like images
        registry.addMapping("/static/**")
                .allowedOrigins("http://localhost:5173", "http://localhost:5174", "http://localhost:5175")
                .allowedMethods("GET");
    }


}
