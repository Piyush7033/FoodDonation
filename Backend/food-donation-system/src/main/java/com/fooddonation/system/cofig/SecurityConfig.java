package com.fooddonation.system.cofig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    // PASSWORD ENCODER
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // CORS CONFIGURATION
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of("http://localhost:5173"));
        config.setAllowedMethods(List.of(
                "GET",
                "POST",
                "PUT",
                "DELETE",
                "OPTIONS"
        ));

        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);

        return source;
    }

    // SECURITY FILTER
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {

        http

                // ENABLE CORS
                .cors(Customizer.withDefaults())

                // DISABLE CSRF
                .csrf(csrf -> csrf.disable())

                // AUTHORIZATION
                .authorizeHttpRequests(auth -> auth

                        // ALLOW OPTIONS REQUESTS
                        .requestMatchers(HttpMethod.OPTIONS, "/**")
                        .permitAll()

                        // PUBLIC AUTH APIs
                        .requestMatchers("/api/auth/**")
                        .permitAll()

                        // PUBLIC FOOD VIEW API
                        .requestMatchers(HttpMethod.GET,
                                "/api/food/available")
                        .permitAll()

                        // DONATION APIs
                        .requestMatchers(HttpMethod.GET,
                                "/api/donations/**")
                        .permitAll()

                        .requestMatchers(HttpMethod.POST,
                                "/api/donations/**")
                        .permitAll()

                        // ADMIN APIs
                        .requestMatchers("/api/admin/**")
                        .hasRole("ADMIN")

                        // FOOD APIs
                        .requestMatchers(HttpMethod.POST,
                                "/api/food/**")
                        .hasRole("DONOR")

                        .requestMatchers(HttpMethod.PUT,
                                "/api/food/**")
                        .hasRole("DONOR")

                        .requestMatchers(HttpMethod.DELETE,
                                "/api/food/**")
                        .hasRole("DONOR")

                        // OTHER FOOD GET APIs
                        .requestMatchers(HttpMethod.GET,
                                "/api/food/**")
                        .hasAnyRole("DONOR", "RECEIVER")

                        // REQUEST APIs
                        .requestMatchers("/api/request/**")
                        .hasAnyRole("RECEIVER", "DONOR")

                        // EVERYTHING ELSE
                        .anyRequest()
                        .authenticated()
                )

                // BASIC AUTH
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}