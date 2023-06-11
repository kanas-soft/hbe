package com.kanas.hbe.security;

import com.kanas.hbe.security.filter.AuthenticationFilter;
import com.kanas.hbe.security.filter.AuthorizationFilter;
import com.kanas.hbe.service.impl.UserServiceImpl;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

    private final UserServiceImpl userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationConfiguration authenticationConfiguration;

    public AppSecurityConfig(UserServiceImpl userService,
            AuthenticationConfiguration authenticationConfiguration,
            BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().disable(); // NOSONAR
        http
                .csrf().disable(); // NOSONAR

        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // NOSONAR

        http
                .addFilter(new AuthenticationFilter(authenticationConfiguration.getAuthenticationManager()))
                .addFilterAfter(new AuthorizationFilter(), AuthenticationFilter.class);

        http
                .authorizeHttpRequests() // NOSONAR
                .requestMatchers("/login", "/api/v1/users/register/**").permitAll()
                .anyRequest()
                .authenticated();

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder)
                .and() // NOSONAR
                .build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NotNull CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("*");
            }
        };
    }
}