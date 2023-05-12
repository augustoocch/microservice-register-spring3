
package com.universityW3.controller;

import com.universityW3.DAO.UserDao;
import com.universityW3.model.Roles;
import com.universityW3.model.Users;
import com.universityW3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.stream.Collectors;


@RestController
public class UserController {

    @Autowired
    private UserService userServ;


    @GetMapping(value = "/find-user/{user}")
    public Mono<ResponseEntity<Users>> findByMail(@PathVariable(name ="user", required = true) String email) {
        return userServ.findByEmail(email)
                .map(i -> ResponseEntity.ok().body(i))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }




}
