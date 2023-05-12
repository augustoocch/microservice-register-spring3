package com.universityW3.repository;

import com.universityW3.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepoNR extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail(String email);

}
