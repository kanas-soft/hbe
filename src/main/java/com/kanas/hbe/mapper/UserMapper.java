package com.kanas.hbe.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import com.kanas.hbe.domain.dto.RegistrationDto;
import com.kanas.hbe.domain.dto.UserDto;
import com.kanas.hbe.domain.entity.Role;
import com.kanas.hbe.domain.entity.User;
import com.kanas.hbe.domain.enumeration.UserRole;

public final class UserMapper {

    private UserMapper() {

    }

    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());

        Set<UserRole> roles = user.getRoles().stream().map(Role::getUserRole).collect(Collectors.toSet());

        userDto.setRoles(roles);
        return userDto;
    }

    public static User toEntity(RegistrationDto registrationDto) {
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(registrationDto.getPassword());
        user.setEmail(registrationDto.getEmail());
        user.setEnabled(true);
        return user;
    }

    public static User toEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        Set<Role> roles = userDto.getRoles().stream().map(role -> {
            Role r = new Role();
            r.setUserRole(role);
            return r;
        }).collect(Collectors.toSet());

        user.setRoles(roles);
        return user;
    }

}