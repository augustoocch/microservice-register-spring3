package com.universityW3.security.control;


import lombok.*;

@Data
public class LoginResponseDto {
    private String jwt;
    private String type = "Bearer";

    public LoginResponseDto(String jwt) {
        super();
        this.jwt = jwt;
    }
}
