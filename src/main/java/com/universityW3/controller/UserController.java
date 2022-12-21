
package com.universityW3.controller;

import com.universityW3.model.Users;
import com.universityW3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

@RestController
public class UserController {

    @Autowired
    private UserService userServ;


    @PostMapping(value="/validate-user")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<String> loginRequest(
            @RequestBody Users usuario) {
        if (userServ.findByEmail(usuario.getEmail()) != null) {
            if (usuario.getPassword().equals(userServ.findByEmail(usuario.getEmail()).getPassword())) {
                return new ResponseEntity<String>("User finded properly, credentials Ok!! User: " + usuario.getEmail(), HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<String>("Credentials not accepted!!", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity<String>("Credentials not accepted!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value="/new-user")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<String> newUser(@RequestBody Users usuario) {
        if(userServ.findByEmail(usuario.getEmail()) != null) {
            return new ResponseEntity<String>("User already exists!!", HttpStatus.BAD_REQUEST);
        } else {
            userServ.save(usuario);
            return new ResponseEntity<String>("User: " + usuario.getEmail() + " saved properly: ", HttpStatus.ACCEPTED);
        }
    }

    @PatchMapping(value = "/update-user")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<String> updateUser (@RequestBody Users usuario) {
        if(userServ.findByEmail(usuario.getEmail()) != null) {
            Users newUsers = userServ.findByEmail(usuario.getEmail());
            if(newUsers.getIduser() != usuario.getIduser()) {
                return new ResponseEntity<String>("Id is incorrect!!", HttpStatus.BAD_REQUEST);
            }
            userServ.updateUser(usuario);
            return new ResponseEntity<String>("User: " + usuario.getEmail() +" updated properly", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<String>("Inexistent user, update is impossible", HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping(value="/delete-user")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<String> deleteUser(
            @RequestBody Users usuario
    ) {
        if (userServ.findByEmail(usuario.getEmail()) == null) {
            return new ResponseEntity<String>("Inexistent user", HttpStatus.BAD_REQUEST);
        } else {
            Users newUsers = userServ.findByEmail(usuario.getEmail());
            if(newUsers.getIduser() != usuario.getIduser()) {
                return new ResponseEntity<String>("Id is incorrect!!", HttpStatus.BAD_REQUEST);
            }
            userServ.deleteUser(usuario.getIduser());
            return new ResponseEntity<>("User deleted properly: " + usuario.getEmail(), HttpStatus.ACCEPTED);
        }
    }


}
