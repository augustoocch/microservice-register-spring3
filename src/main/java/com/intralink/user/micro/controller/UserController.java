
package com.intralink.user.micro.controller;

import com.intralink.user.micro.model.Users;
import com.intralink.user.micro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Optional;


@RestController
public class UserController {

    @Autowired
    private UserService userServ;


    @GetMapping(value = "/find-user/{user}")
    public Optional<ResponseEntity<Users>> findByMail(@PathVariable(name ="user", required = true) String email) {
        return userServ.findByEmail(email)
                .map(i -> ResponseEntity.ok().body(i));
    }

    @PostMapping(value = "/save-user")
    public ResponseEntity<Users> saveUser(Users user) {
        try {
            Users userSaved = userServ.save(user);
            return ResponseEntity.ok().body(userSaved);
        } catch (Throwable ex) {
            ex.printStackTrace();
            return ResponseEntity.status(400).build();
        }
    }




}
