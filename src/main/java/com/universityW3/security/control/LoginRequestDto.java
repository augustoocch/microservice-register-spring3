package com.universityW3.security.control;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class LoginRequestDto {
    private String username;
    private String password;
}
