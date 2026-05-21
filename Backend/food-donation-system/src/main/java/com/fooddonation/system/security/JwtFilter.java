//package com.fooddonation.system.security;
//
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.Collections;
//
//@Component
//public class JwtFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Override
//    protected void doFilterInternal(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain filterChain
//    ) throws ServletException, IOException {
//
//        // GET AUTH HEADER
//        String authHeader = request.getHeader("Authorization");
//
//        String token = null;
//        String username = null;
//
//        // CHECK BEARER TOKEN
//        if (authHeader != null &&
//                authHeader.startsWith("Bearer ")) {
//
//            token = authHeader.substring(7);
//
//            try {
//                username = jwtUtil.extractUsername(token);
//            } catch (Exception e) {
//
//                System.out.println("Invalid JWT Token");
//            }
//        }
//
//        // IF USER FOUND
//        if (username != null &&
//                SecurityContextHolder.getContext()
//                        .getAuthentication() == null) {
//
//            UserDetails userDetails =
//                    User.withUsername(username)
//                            .password("")
//                            .authorities(Collections.emptyList())
//                            .build();
//
//            UsernamePasswordAuthenticationToken authToken =
//                    new UsernamePasswordAuthenticationToken(
//                            userDetails,
//                            null,
//                            userDetails.getAuthorities()
//                    );
//
//            authToken.setDetails(
//                    new WebAuthenticationDetailsSource()
//                            .buildDetails(request)
//            );
//
//            SecurityContextHolder.getContext()
//                    .setAuthentication(authToken);
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}