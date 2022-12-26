package com.universityW3.DAO;

import com.universityW3.model.Users;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserDao {
    private String username;
    private Set<String> roles;
}