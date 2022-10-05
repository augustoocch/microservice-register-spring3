package com.universityW3.repository;

import com.universityW3.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.universityW3.model.UserRepo;


@Service("userService")
public class UserServiceImpl implements UserServInterface{
    
    @Autowired
    public UserRepo userRep;

    @Override
    public List<User> findAll() {

        return userRep.findAll();
    }

    @Override
    public void save(User usr) {
    }

    @Override
    public User findById(Long id) {
        
        return null;
    }
    
     @Override
    public User findByEmail(User user) {
        
        return userRep.findByEmail(user.getEmail());
    }

    @Override
    public void delete(long id) {
    }
    
 
}