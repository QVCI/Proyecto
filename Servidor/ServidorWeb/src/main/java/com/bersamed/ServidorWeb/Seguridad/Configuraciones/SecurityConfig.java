package com.bersamed.ServidorWeb.Seguridad.Configuraciones;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.http.HttpServletResponse;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desactiva CSRF 
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login/trabajadores").permitAll() // Permite login sin token
                .requestMatchers("/login/clientes").permitAll()
                .anyRequest().authenticated()    // Resto de endpoints protegidos
            )
              .exceptionHandling(exception -> exception
                .authenticationEntryPoint((request, response, authException) -> {
                
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json");
                    response.getWriter().write("{\"error\":\"Acceso no autorizado\"}");
                })
            );

        return http.build();
    }
}
