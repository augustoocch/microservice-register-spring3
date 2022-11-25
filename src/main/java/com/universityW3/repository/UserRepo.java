package com.universityW3.repository;

import javax.persistence.Id;

import com.universityW3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    public User findByEmail(String email);
}
