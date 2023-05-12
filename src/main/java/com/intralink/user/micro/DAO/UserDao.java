package com.intralink.user.micro.DAO;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserDao {
    private String username;
    private Set<String> roles;
}