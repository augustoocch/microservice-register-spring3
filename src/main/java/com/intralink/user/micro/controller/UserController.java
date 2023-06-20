
package com.intralink.user.micro.controller;

import com.intralink.user.micro.Exceptions.NotFoundUserEx;
import com.intralink.user.micro.model.Match;
import com.intralink.user.micro.model.Users;
import com.intralink.user.micro.service.MatchService;
import com.intralink.user.micro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userServ;

    @Autowired
    private MatchService matchService;


    @GetMapping(value = "/find-user/{user}")
    public Optional<ResponseEntity<Users>> findByMail(@PathVariable(name ="user", required = true) String email) {
        return userServ.findByEmail(email)
                .map(i -> ResponseEntity.ok().body(i));
    }

    @PostMapping(value = "/save-user")
    public ResponseEntity<Users> saveUser(Users user) {
        try {
            Users userSaved = userServ.save(user);
            Match matchTable = new Match(user.getIduser());
            matchService.save(matchTable);
            return ResponseEntity.ok().body(userSaved);
        } catch (Throwable ex) {
            ex.printStackTrace();
            return ResponseEntity.status(400).build();
        }
    }

    @PutMapping(value = "/edit-user")
    public ResponseEntity<Users> editUser (Users user) throws NotFoundUserEx, Exception {
            return userServ.update(user);
    }




}
