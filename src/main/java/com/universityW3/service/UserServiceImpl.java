package com.universityW3.service;

import com.universityW3.model.Admin;
import com.universityW3.model.Users;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.universityW3.repository.UserRepository;



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

        List<Users> allAdmins = userRep.findAll();
        try {
            List<Users> listAndFind = allAdmins
                    .stream()
                    .filter(a -> ((a.getEmail()).equals(email)))
                    .collect(Collectors.toList());
            if (listAndFind.size() == 0) {
                return null;
            }
            return listAndFind.get(0);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }
}