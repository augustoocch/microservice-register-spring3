package com.intralink.user.micro.service;

import com.intralink.user.micro.model.Users;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {

        Optional<Users> findByEmail(String email);

        Users save(Users user);

}
