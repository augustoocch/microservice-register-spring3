
package com.intralink.user.micro.controller;

import com.intralink.user.micro.model.Users;
import com.intralink.user.micro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


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
