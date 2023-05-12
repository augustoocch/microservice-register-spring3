package com.universityW3.service;

import com.universityW3.model.Users;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.universityW3.repository.UserRepository;
import reactor.core.publisher.Mono;


@Transactional
@Service("userService")
public class UserServiceImpl {

    @Autowired
    public UserRepository userRep;


    public Mono<Users> findByEmail(String email) {
        Mono<Users> users = userRep.findByEmail(email);
        return users;
    }

}