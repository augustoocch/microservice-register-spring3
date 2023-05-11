package com.universityW3.config;

import com.universityW3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailedService {


        @Autowired
        private UserRepository userRepository;


        public UserDetailsService loadUserByUsername() throws UsernameNotFoundException {
            //Service to access Database
            return username -> userRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        }


}
