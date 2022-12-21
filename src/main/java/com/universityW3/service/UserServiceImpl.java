package com.universityW3.service;

import com.universityW3.model.Users;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.universityW3.repository.UserRepository;

import javax.transaction.Transactional;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService{
    
    @Autowired
    public UserRepository userRep;

    @Override
    public List<Users> findAll() {
        return this.userRep.findAll();
    }

    @Override
    public void save(Users usr) {
        this.userRep.save(usr);
    }

    @Override
    public void deleteUser(long id) {
        this.userRep.delete(userRep.findById(id).get());
    }

    @Override
    public Users updateUser(Users users) {
        return this.userRep.save(users);
    }

    @Override
    public Users findByEmail(String email) {
        return userRep.findByEmail(email);
    }
}