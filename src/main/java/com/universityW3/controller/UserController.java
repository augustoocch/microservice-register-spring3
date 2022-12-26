
package com.universityW3.controller;

import com.universityW3.DAO.UserDao;
import com.universityW3.model.Roles;
import com.universityW3.model.Users;
import com.universityW3.security.MainSecurity;
import com.universityW3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
public class UserController {

    @Autowired
    private UserService userServ;


    @GetMapping(value = "/find-user/{user}")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<Users> findByMail(
            @PathVariable(name ="user", required = true) String email) {
        if (userServ.findByEmail(email) != null) {
            Users usuario = new Users();
            usuario.setEmail(email);
            return new ResponseEntity("User found with email: " + usuario.getEmail(), HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity("Username not found with email: " + email, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping(value="/validate-user")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<Users> loginRequest(
            @RequestBody Users usuario) {
        if (userServ.findByEmail(usuario.getEmail()) != null) {
            if (usuario.getPassword().equals(userServ.findByEmail(usuario.getEmail()).getPassword())) {
                return new ResponseEntity(usuario.getEmail(), HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity(usuario.getEmail(), HttpStatus.BAD_REQUEST);
            }
        }
        else {
            return new ResponseEntity(usuario.getEmail(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value="/new-user")
    @Consumes(MediaType.APPLICATION_JSON)
    public  ResponseEntity<Users> newUser(@RequestBody Users usuario) {
        if(userServ.findByEmail(usuario.getEmail()) != null) {
            return new ResponseEntity("User: " + usuario.getEmail() +" already exist", HttpStatus.BAD_REQUEST);
        } else {
            UserDao userDao = null;
            Set<Roles> rolesSet= usuario.getRoles();

            Set<String> rolesStrs = usuario.getRoles()
                    .stream()
                    .map(r -> "ROLE_"+r.getRole())
                    .collect(Collectors.toSet());
            MainSecurity main = new MainSecurity();
            String passwEncoded = main.passowrdEncrypt(usuario.getPassword());

            usuario.setPassword(passwEncoded);
            usuario.setRoles(rolesSet);
            userServ.save(usuario);
            return new ResponseEntity("User created succesfully with email: " + usuario.getEmail(), HttpStatus.ACCEPTED);
        }
    }

    @PatchMapping(value = "/update-user")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<Users> updateUser (@RequestBody Users usuario) {
        if(userServ.findByEmail(usuario.getEmail()) != null) {
            Users newUsers = userServ.findByEmail(usuario.getEmail());
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if(encoder.matches(usuario.getPassword(), newUsers.getPassword()) || newUsers.getSurname() != usuario.getSurname()) {
                return new ResponseEntity("Wrong autenthication for user: " + usuario.getEmail(), HttpStatus.BAD_REQUEST);
            }
            UserDao userDao = null;
            Set<Roles> rolesSet= usuario.getRoles();

            Set<String> rolesStrs = usuario.getRoles()
                    .stream()
                    .map(r -> "ROLE_"+r.getRole())
                    .collect(Collectors.toSet());
            MainSecurity main = new MainSecurity();
            String passwEncoded = main.passowrdEncrypt(usuario.getPassword());
            usuario.setPassword(passwEncoded);
            usuario.setRoles(rolesSet);

            userServ.updateUser(usuario);
            return new ResponseEntity("User updated succesfully with email: " + usuario.getEmail(), HttpStatus.ACCEPTED);
        } if (userServ.findByEmail(usuario.getEmail()) == null) {
            return new ResponseEntity("User doesn't exist", HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity("User couldn't be updated", HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping(value="/delete-user")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<Users> deleteUser(@RequestBody Users usuario) {
        if (userServ.findByEmail(usuario.getEmail()) == null) {
            return new ResponseEntity("User: " + usuario.getEmail() + " doesn't exist.", HttpStatus.BAD_REQUEST);
        } else {
            Users newUsers = userServ.findByEmail(usuario.getEmail());
            if(!newUsers.getSurname().equals(usuario.getSurname()) || !newUsers.getName().equals(usuario.getName())) {
                return new ResponseEntity("Wrong authentication for user: " + usuario.getEmail(), HttpStatus.BAD_REQUEST);
            }
            userServ.deleteUser(usuario.getIduser());
            return new ResponseEntity("User: " + usuario.getEmail() + " deleted properly.", HttpStatus.ACCEPTED);
        }
    }


}
