package com.college.database.security;

import com.college.database.util.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
    @EnableMethodSecurity //for this  @PreAuthorize("hasRole('ADMIN')")
    @EnableWebSecurity //same
    public class Security {
        @Autowired
        private JwtAuthenticationFilter jwtFilter;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

            http.csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/users/login", "/users/register").permitAll()
                            .anyRequest().authenticated()
                    )
                    .addFilterBefore(jwtFilter,
                            UsernamePasswordAuthenticationFilter.class);

            return http.build();
        }
    }

