package com.kanas.hbe.domain.dto;

import com.kanas.hbe.validation.annotation.ValidEmail;
import com.kanas.hbe.validation.annotation.ValidPassword;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationDto {

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    @ValidEmail
    private String email;

    @NotNull
    @NotEmpty
    @ValidPassword
    private String password;

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private String address;

    @NotNull
    @NotEmpty
    private String phoneNumber;

}
