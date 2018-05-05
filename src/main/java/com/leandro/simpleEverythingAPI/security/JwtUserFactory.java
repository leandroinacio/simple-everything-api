package com.leandro.simpleEverythingAPI.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.leandro.simpleEverythingAPI.models.User;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User profile) {
        return new JwtUser(
        		profile.getId(),
        		profile.getUsername(),
        		profile.getFirstname(),
        		profile.getLastname(),
        		profile.getEmail(),
        		profile.getPassword(),
                mapToGrantedAuthorities(profile.getAuthorities()),
                profile.getEnabled(),
                profile.getLastPasswordResetDate()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toList());
    }
}