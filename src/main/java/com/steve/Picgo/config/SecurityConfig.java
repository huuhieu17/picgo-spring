package com.steve.Picgo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // tắt CSRF cho API
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll() // cho phép register/login không cần auth
                        .anyRequest().authenticated() // các request khác cần JWT
                )
                .httpBasic(httpBasic -> httpBasic.disable()); // tắt httpBasic nếu dùng JWT

        return http.build();
    }
}
