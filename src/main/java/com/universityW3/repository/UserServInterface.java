package com.universityW3.repository;

import com.universityW3.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;


public interface UserServInterface {
    
    List <User> findAll();

    void save(User usr);

    public User findById(Long id);
    
    public User findByEmail(User user);

    void delete(long id);
}