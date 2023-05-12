package com.universityW3.service;

import com.universityW3.model.Users;

import com.universityW3.repository.UserRepoNR;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.universityW3.repository.UserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Transactional
@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    public UserRepository userRep;

    @Autowired
    public UserRepoNR userRepNr;


    public Mono<Users> findByEmail(String email) {
        Flux<Users> users = userRep.findAll();
        Mono<Users> userFiltered = users
                .filter(i -> i.getEmail().equals(email))
                .next();

        return userFiltered;
    }


}