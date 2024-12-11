package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/pets/**", "/households/**")) // Disable CSRF for specific endpoints
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/pets/**", "/households/**").permitAll()
                        .requestMatchers("/users/**").hasRole("ADMIN")
                        .requestMatchers("/graphql").authenticated()
                        .anyRequest().authenticated()
                )
                .httpBasic(basic -> basic.disable()) // Replace with modern configuration
                .securityContext(security -> security.securityContextRepository(null)); // Custom security context repository

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var admin = User.withUsername("admin")
                .password("{noop}password")
                .roles("ADMIN")
                .build();
        var user = User.withUsername("user")
                .password("{noop}password")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }
}