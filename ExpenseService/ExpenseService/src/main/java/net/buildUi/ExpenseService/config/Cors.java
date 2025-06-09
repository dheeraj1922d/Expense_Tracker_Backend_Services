package net.buildUi.ExpenseService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class Cors {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Allow credentials (cookies, headers)
        config.setAllowCredentials(true);

        // Allow only specific origins for better security
        config.setAllowedOrigins(Arrays.asList("*")); // For development, replace with production URL in prod
        // config.addAllowedOrigin("http://your-production-url.com"); // Add production URLs here
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // Register CORS configuration for all routes
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
