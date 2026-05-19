//package com.fooddonation.system.cofig;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.List;
//
//public class Config {
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//
//        CorsConfiguration config = new CorsConfiguration();
//
//        // React frontend URL
//        config.setAllowedOrigins(List.of("http://localhost:5173"));
//
//        // Allowed HTTP methods
//        config.setAllowedMethods(List.of(
//                "GET",
//                "POST",
//                "PUT",
//                "DELETE",
//                "OPTIONS"
//        ));
//
//        // Allowed headers
//        config.setAllowedHeaders(List.of("*"));
//
//        // Allow Authorization header (JWT)
//        config.setExposedHeaders(List.of("Authorization"));
//
//        // Allow credentials
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
