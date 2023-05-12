package com.universityW3.repository;

import com.universityW3.model.Users;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;


@Repository
public interface UserRepository extends ReactiveCrudRepository<Users, Long> {


}
