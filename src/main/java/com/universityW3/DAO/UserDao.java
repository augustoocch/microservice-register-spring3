package com.universityW3.DAO;

import com.universityW3.model.User;
import jdk.jfr.DataAmount;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserDao {
    private User usuario;
    private Set<String> roles;
}
