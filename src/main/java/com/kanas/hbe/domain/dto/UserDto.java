package com.kanas.hbe.domain.dto;

import com.kanas.hbe.domain.enumeration.UserRole;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserDto extends BaseDto {

    private String username;
    private String email;
    private String password;
    private boolean enabled;
    private Set<UserRole> roles = new HashSet<>();
}