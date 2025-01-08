package tn.micro.service.cloud.config;

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
                // Allow cross-origin requests from Angular (http://localhost:4200)
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:4200")  // Ensure this is your Angular app's URL
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Include the methods you're using
                        .allowedHeaders("*"); // Allow all headers
            }
        };
    }
}
