package com.example.ecommerce_backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors() // Enable CORS
            .and()
            .csrf().disable()
            .authorizeHttpRequests()
            // Permit all GET requests for listing endpoints
            .requestMatchers("/users", "/items", "/carts", "/orders").permitAll()
            // Permit POST /users, /users/login, and /items without authentication
            .requestMatchers("/users", "/users/login", "/items").permitAll()
            // Require authentication for POST /carts and /orders (token-based)
            .requestMatchers("/carts", "/orders/**").authenticated()
            .requestMatchers("/carts/**").authenticated()
            // Any other request requires authentication
            .anyRequest().authenticated()
            .and()
            // Use custom token-based authentication instead of basic auth
            .httpBasic().disable(); // Disable basic auth if using token-based

        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:3000"); // Allow frontend origin
        config.addAllowedMethod("*"); // Allow all methods (GET, POST, etc.)
        config.addAllowedHeader("*"); // Allow all headers
        config.setAllowCredentials(true); // Allow cookies/auth credentials if needed
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}