//package com.fooddonation.system.cofig;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.List;
//
//@Configuration
//public class Config {
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//
//        CorsConfiguration config = new CorsConfiguration();
//
//        // React frontend URL
//        config.setAllowedOrigins(List.of("http://localhost:5174"));
//
//        // HTTP methods
//        config.setAllowedMethods(List.of(
//                "GET",
//                "POST",
//                "PUT",
//                "DELETE",
//                "OPTIONS"
//        ));
//
//        // Headers
//        config.setAllowedHeaders(List.of("*"));
//
//        // JWT header exposure
//        config.setExposedHeaders(List.of("Authorization"));
//
//        // Allow cookies / JWT
//        config.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source =
//                new UrlBasedCorsConfigurationSource();
//
//        source.registerCorsConfiguration("/**", config);
//
//        return source;
//    }
//}