package com.universityW3.service;

import com.universityW3.model.Users;
import reactor.core.publisher.Mono;
import java.util.List;

public interface UserService {

        public void deleteUser(long id);

        public Users updateUser(Users users);

        public void save(Users users);

        public List<Users> findAll();
        Mono<Users> findByEmail(String email);

}
