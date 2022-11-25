
package com.universityW3.controller;

import com.universityW3.model.User;
import com.universityW3.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

@Slf4j
@RestController
public class httpController {

    @Autowired
    private UserRepo user;


    @PostMapping(value="/orden")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<User> loginRequest(
            @RequestBody User usuario) {
        if (user.findByEmail(usuario.getEmail()) != null) {
            if (usuario.getPassword().equals(user.findByEmail(usuario.getEmail()).getPassword())) {
                log.info("User finded properly, credentials Ok!! User: " + usuario.getEmail());
                return ResponseEntity.ok(usuario);
            } else {
                log.info("Credentials not accepted");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        else {
            log.info("Credentials not accepted!!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(value="/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<User> deleteUser(
            @RequestBody User usuario
    ) {
        if (user.findByEmail(usuario.getEmail()) == null) {
            log.info("Inexistent User: " + usuario.getEmail());
        } else {
            log.info("Deleting user: " + usuario.getEmail());
            user.deleteById(usuario.getIduser());
            log.info("User deleted: " + usuario);
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


}
