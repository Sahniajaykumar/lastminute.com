package com.lastminute.com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

    @Bean
    PasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }
   @Bean
    public SecurityFilterChain securityConfig(HttpSecurity http) throws Exception {
           http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/v1/auth/register").permitAll()
                                .anyRequest().authenticated()
                );
        return http.build();

    }
}
