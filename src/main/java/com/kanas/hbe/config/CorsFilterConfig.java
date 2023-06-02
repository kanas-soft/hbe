package com.kanas.hbe.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilterConfig implements Filter {

    private final List<String> allowedOrigins = Arrays.asList("http://localhost:4200",
            "http://localhost:4200/",
            "http://localhost:4201",
            "http://localhost:4201/",
            "*");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        String origin = req.getHeader("Origin");
        res.setHeader("Access-Control-Allow-Origin", allowedOrigins.contains(origin) ? origin : "*");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PATCH, PUT, OPTIONS");
        res.setHeader("Access-Control-Allow-Headers", "*");
        res.setHeader("Access-Control-Expose-Headers", "Authorization");
        // Just REPLY OK if request method is OPTIONS for CORS (pre-flight)
        if (req.getMethod().equals("OPTIONS")) {
            res.setHeader("Access-Control-Max-Age", "86400");
            res.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        chain.doFilter(request, response);
    }
}