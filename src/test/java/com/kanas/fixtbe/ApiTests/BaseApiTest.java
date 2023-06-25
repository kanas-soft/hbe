package com.kanas.fixtbe.ApiTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collection;
import java.util.Date;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@ActiveProfiles("test")
public abstract class BaseApiTest {

    static final String USERS_SIGNUP_URL = "/users/sign-up";
    static final String USERS_URL = "/users/";
    static final String ROLES_URL = "/roles/";
    static final String ROLES_URL_ADD = "/roles/add-role";
    static final String ROLE_NAME = "roleName";
    static final String CLAIMS = "authorities";
    static final Long EXPIRATION_TIME = 900000000L;
    static final String SECRET = "mySecretKey";
    static final String HEADER = "Authorization";
    static final String BEARER = "Bearer ";

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    WebApplicationContext context;

    @Autowired
    MockMvc mvc;

    void setUpWithSecurity() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity()).build();
    }

    protected void setUpWithNoSecurity() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    String generateToken(String username, Collection<? extends GrantedAuthority> authorities) {

        String token = Jwts.builder()
                .setSubject(username)
                .claim(CLAIMS, authorities)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET.getBytes())
                .compact();

        return token;
    }

    protected String toJson(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't convert object to JSON", e);
        }
    }
}
