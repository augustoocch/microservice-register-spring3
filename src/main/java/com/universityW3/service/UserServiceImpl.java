package com.universityW3.service;

import com.universityW3.model.User;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.universityW3.repository.UserRepo;

import javax.transaction.Transactional;

//(porque usa conexion con el repositorio y pega informacion es transactional)
@Transactional
@Service("userService")
public class UserServiceImpl {
    
    @Autowired
    public UserRepo userRep;

    public List<User> findAll() {
        return this.userRep.findAll();
    }

    public void save(User usr) {
    }

    public Optional<User> findById(User user) {
        return userRep.findById(user.getIduser());
    }

    public User findByEmail(User user) {
        return userRep.findByEmail(user.getEmail());
    }

    public void deleteUs(long id) {
        this.userRep.delete(userRep.findById(id).get());
    }
    
 
}