package com.simple_inventory_tracker.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


// This configuration class customizes Spring MVC's CORS settings
@Configuration
public class WebConfig implements WebMvcConfigurer {

  // This method allows you to configure CORS mappings for your application
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**") // Apply CORS settings to all endpoints
        .allowedOrigins("*") // Allow requests from any origin
        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH") // Allowed HTTP methods
        .allowedHeaders("*") // Allow all headers (e.g., Authorization, Content-Type)
        .allowCredentials(false) // Allow credentials (cookies, authorization headers, etc.)
        .maxAge(3600); // Cache preflight response for 1 hour
  }
}