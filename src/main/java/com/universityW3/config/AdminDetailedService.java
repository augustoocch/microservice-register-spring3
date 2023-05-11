package com.universityW3.config;

import com.universityW3.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;



public class AdminDetailedService {


    @Autowired
    private AdminRepository adminRepository;


    public UserDetailsService loadUserByUsername() throws UsernameNotFoundException {
        //Service to access Database
        return username -> adminRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


}

