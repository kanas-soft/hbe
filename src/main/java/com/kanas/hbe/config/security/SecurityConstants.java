package com.kanas.hbe.config.security;

public final class SecurityConstants {

    public static final Long EXPIRATION_DATE = 900000000L;
    public static final String SECRET = "thisIsNotARealSecretKeythisIsNotARealSecretKeythisIsNotARealSecretKey";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER = "Authorization";
    public static final String AUTHORITIES = "authorities";
    public static final String AUTHORITY = "authority";

    private SecurityConstants() {

    }
}