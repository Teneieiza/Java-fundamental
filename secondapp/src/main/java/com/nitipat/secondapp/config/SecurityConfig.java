package com.nitipat.secondapp.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

@Slf4j
@Configuration
public class SecurityConfig {
    private static final String[] whiteList = {
            "/actuator/**",
            "/health",

            // OpenAPI 3.0 Specification
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",

            "/v1/auth/**"
    };

    private static final  String[] blackList = {
            "/actuator/restart"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        var username = "X-API-KEY";
        var password = "secret";
        var filter = new ApiKeyAuthFilter(username);
        filter.setAuthenticationManager(authentication -> {
            var principal = (String) authentication.getPrincipal();
            if (!password.equals(principal)){
                throw new BadCredentialsException("Invalid API Key");
            }
            authentication.setAuthenticated(true);
            return authentication;
        });

        http.securityMatcher("/**")
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilter(filter)
                .authorizeHttpRequests(request -> request
                        .requestMatchers(whiteList).permitAll()
                        .requestMatchers(blackList).denyAll()
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(request -> {
                    var c = new CorsConfiguration();
                    c.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
                    c.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    c.setAllowedHeaders(Arrays.asList("X-API-KEY", "Content-Type"));
                    c.setAllowCredentials(true);
                    return c;
                }));


        return http.build();
    }
}

