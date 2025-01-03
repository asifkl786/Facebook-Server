package com.facebook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	  @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	  
	  
	  @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            // Disable CSRF (only for development/testing; enable in production)
	            .csrf(csrf -> csrf.disable())
	            
	            // Define URL-based authorization rules
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/api/users/register", "/api/users/login","/api/posts/getPost/{id}",
	                		"/api/users/getProfile/{userId}","/api/users/update/{id}","/api/posts/getAllPost",
	                		"/api/posts/update/{id}","/api/posts/delete/{id}","/api/posts/search",
	                		"/api/notifications","/api/notifications/{id}",
	                		"/api/friendships/requests","/api/friendships/{userId}","/api/friendships/remove/{id}",
	                		"/api/friendships/request","/api/friendships/accept/{id}","/api/friendships/rejected/{id}",
	                		"/api/users/delete/{id}","/api/users/search","/api/posts/create").permitAll() // Public endpoints
	                .anyRequest().authenticated() // Protect all other endpoints
	            );

	        return http.build();
	    }
}
