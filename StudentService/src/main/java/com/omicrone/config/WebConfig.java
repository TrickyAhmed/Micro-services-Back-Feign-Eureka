package com.omicrone.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")  // Apply to all /api/ paths
                        .allowedOrigins("http://localhost:4200")  // Allow Angular app
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allow these methods
                        .allowedHeaders("*")  // Allow all headers
                        .allowCredentials(true);  // Allow credentials (optional)
            }
        };
    }
}
