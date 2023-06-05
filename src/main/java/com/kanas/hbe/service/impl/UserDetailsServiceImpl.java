package com.kanas.hbe.service.impl;

import com.kanas.hbe.domain.entity.Role;
import com.kanas.hbe.domain.entity.User;
import com.kanas.hbe.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isEmpty()) {
            log.debug("No user found with username: " + username);
            throw new UsernameNotFoundException("No user found with username: " + username);
        }

        User user = optionalUser.get();

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword().toLowerCase(), user.isEnabled(), user.isAccountNonExpired(),
                user.isCredentialsNonExpired(), user.isAccountNonLocked(), getAuthorities(user.getRoles()));
    }

    private List<GrantedAuthority> getAuthorities(Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
        }
        return authorities;
    }
}
