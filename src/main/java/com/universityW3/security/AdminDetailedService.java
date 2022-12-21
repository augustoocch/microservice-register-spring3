package com.universityW3.security;

import com.universityW3.model.Admin;
import com.universityW3.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Set;
import java.util.stream.Collectors;


public class AdminDetailedService implements UserDetailsService {

        @Autowired
        private AdminService adminService;


        @Override
        public UserDetails loadUserByUsername(String mat) throws UsernameNotFoundException {
            //Service to access Database
            Admin newAdmin = this.adminService.findByMat(mat);

            Set<SimpleGrantedAuthority> roles = newAdmin.getRoles()
                    .stream()
                    .map(r -> new SimpleGrantedAuthority(r.getRole()))
                    .collect(Collectors.toSet());

            //org.springframework.security.core.userdetails.User;
            return new User(newAdmin.getMat(), newAdmin.getPassword(), roles);
        }
}

