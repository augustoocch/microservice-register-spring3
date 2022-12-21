package com.universityW3.service;

import com.universityW3.model.Users;

import java.util.List;

public interface UserService {

        public void deleteUser(long id);

        public Users findByEmail(String email);

        public Users updateUser(Users users);

        public void save(Users users);

        public List<Users> findAll();

}
