package com.kanas.hbe.user.domain.dto;

import lombok.Data;

@Data
public class UserDto {

    private String username;

    private String email;

    private String password;

    private boolean enabled;

    // private Contact contact;

    // private VerificationToken verificationToken;

    // private List<AuthToken> authenticationTokens = new ArrayList<>();

    // private Set<Role> roles = new HashSet<>();
}
