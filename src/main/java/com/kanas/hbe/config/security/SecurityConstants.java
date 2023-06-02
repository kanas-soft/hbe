package com.kanas.hbe.config.security;

import org.springframework.beans.factory.annotation.Value;

public class SecurityConstants {

    public static Long EXPIRATION_DATE = 900000000L;
    @Value("${kanas.hbe.jwt.secret}")
    public static String SECRET;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER = "Authorization";
    public static final String AUTHORITIES = "authorities";
    public static final String AUTHORITY = "authority";

    private SecurityConstants() {

    }
}
