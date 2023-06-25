package com.kanas.fixtbe.fixtures;

import com.kanas.fixtbe.domain.dto.RegistrationDto;
import com.kanas.fixtbe.domain.dto.UserDto;
import com.kanas.fixtbe.domain.entity.Role;
import com.kanas.fixtbe.domain.entity.User;
import com.kanas.fixtbe.domain.enumeration.UserRole;

import java.util.Set;

public final class UserFixtures {

    public static final String VALID_USERNAME = "test";
    public static final String VALID_EMAIL = "test@test.com";
    public static final String VALID_PASSWORD = "ToshoGosho^(@1035";
    public static final String VALID_FIRST_NAME = "Test";
    public static final String VALID_LAST_NAME = "Test";
    public static final String VALID_ADDRESS = "Test";
    public static final String VALID_PHONENUMBER = "0888999777";

    private UserFixtures() {
    }

    public static RegistrationDto createRegistrationDto() {
        return RegistrationDto.builder()
                .username(VALID_USERNAME)
                .email(VALID_EMAIL)
                .password(VALID_PASSWORD)
                .firstName(VALID_FIRST_NAME)
                .lastName(VALID_LAST_NAME)
                .address(VALID_ADDRESS)
                .phoneNumber(VALID_PHONENUMBER)
                .build();
    }

    public static RegistrationDto createExistingEmailRegistrationDto() {
        return RegistrationDto.builder()
                .username(VALID_USERNAME)
                .email("todorv1@gmail.com")
                .password(VALID_PASSWORD)
                .firstName(VALID_FIRST_NAME)
                .lastName(VALID_LAST_NAME)
                .address(VALID_ADDRESS)
                .phoneNumber(VALID_PHONENUMBER)
                .build();
    }

    public static RegistrationDto createExistingUsernameRegistrationDto() {
        return RegistrationDto.builder()
                .username("tosho")
                .email(VALID_EMAIL)
                .password(VALID_PASSWORD)
                .firstName(VALID_FIRST_NAME)
                .lastName(VALID_LAST_NAME)
                .address(VALID_ADDRESS)
                .phoneNumber(VALID_PHONENUMBER)
                .build();
    }

    public static UserDto createUserDto() {
        return UserDto.builder()
                .username(VALID_USERNAME)
                .email(VALID_EMAIL)
                .password(VALID_PASSWORD)
                .roles(Set.of(UserRole.ADMIN))
                .build();
    }

    public static User createUser() {

        Role role = new Role();
        role.setUserRole(UserRole.ADMIN);

        return User.builder()
                .username(VALID_USERNAME)
                .email(VALID_EMAIL)
                .password(VALID_PASSWORD)
                .roles(Set.of(role))
                .build();
    }
}
