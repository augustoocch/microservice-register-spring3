package com.universityW3.security.control;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class LoginRequestDto {
    private String name;
    private String surname;
    private String password;
    private String email;
    private String country;
    private String city;
}
