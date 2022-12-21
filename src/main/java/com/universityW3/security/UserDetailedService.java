package com.universityW3.security;

import com.universityW3.model.Users;
import com.universityW3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailedService implements UserDetailsService {

    @Autowired
    private UserService usersService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Service to access Database
        Users user = this.usersService.findByEmail(username);

        Set<SimpleGrantedAuthority> roles = user.getRoles()
                .stream()
                .map(r -> new SimpleGrantedAuthority(r.getRole()))
                .collect(Collectors.toSet());

        //org.springframework.security.core.userdetails.User;
        return new User(user.getEmail(), user.getPassword(), roles);
    }
}
