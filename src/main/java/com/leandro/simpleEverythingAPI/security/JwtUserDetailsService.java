package com.leandro.simpleEverythingAPI.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.leandro.simpleEverythingAPI.models.User;
import com.leandro.simpleEverythingAPI.repositories.IUserRepo;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepo profileRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User profile = profileRepo.findByUsername(username);

        if (profile == null) {
        	
        	// TODO: Implement search using email as well
        	
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(profile);
        }
    }
}
