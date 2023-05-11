package com.universityW3.security.control;

import com.universityW3.model.Users;
import com.universityW3.repository.UserRepository;
import com.universityW3.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AuthenticationManager authManager;

    public AuthenticationResponse register(RegisterRequest req) {
        var user = Users.builder()
                .name(req.getName())
                .surname(req.getSurname())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .country(req.getCountry())
                .city(req.getCity())
                .build();
        userRepository.save(user);
        var jwtToken = jwtProvider.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


    public AuthenticationResponse authenticate(RegisterRequest req) {
        //If the user is correct, will recover it from DB
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getEmail(),
                        req.getPassword()
                )
        );
        var user = userRepository.findByEmail(req.getEmail())
                .orElseThrow();
        var jwtToken = jwtProvider.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
