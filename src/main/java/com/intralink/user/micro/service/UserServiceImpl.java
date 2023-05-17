package com.intralink.user.micro.service;

import com.intralink.user.micro.model.Users;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.intralink.user.micro.repository.UserRepository;

import java.util.Optional;


@Transactional
@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    public UserRepository userRepository;

    public Optional<Users> findByEmail(String email) {

        Optional<Users> user= userRepository.findAll()
                .stream()
                .filter(usr -> usr.getEmail().equals(email))
                .findFirst();

        return user;
    }

    @Override
    public Users save(Users user) throws Exception {
        if (this.findByEmail(user.getEmail()).isPresent()) {
            throw new Exception("User already exist");
        }else {
            return userRepository.save(user);
        }
    }


}