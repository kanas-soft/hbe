package com.kanas.hbe.domain.dto;

import java.util.HashSet;
import java.util.Set;

import com.kanas.hbe.domain.enumeration.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserDto extends BaseDto {

    private static final long serialVersionUID = 4L;
    private String username;
    private String email;
    private String password;
    private boolean enabled;
    private Set<UserRole> roles = new HashSet<>();
}