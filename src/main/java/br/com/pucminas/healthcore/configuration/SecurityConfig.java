package br.com.pucminas.healthcore.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Desativa CSRF
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/**", "/v1/**","/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**",
                                "/swagger-resources/**", "/webjars/**")
                        .permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> httpBasic.disable());

        return http.build();
    }
}