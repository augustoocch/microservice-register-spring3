package com.universityW3.service;

import com.universityW3.model.Users;
import com.universityW3.repository.UserRepository;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.Optional;

public interface UserService {

        Mono<Users> findByEmail(String email);

}
