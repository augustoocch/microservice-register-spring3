package com.intralink.user.micro.security.control;

import com.intralink.user.micro.model.Roles;
import com.intralink.user.micro.model.Users;
import com.intralink.user.micro.repository.UserRepository;
import com.intralink.user.micro.security.JwtProvider;
import com.intralink.user.micro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    UserService userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AuthenticationManager authManager;

    public AuthenticationResponse register(RegisterRequest req) {

        Set<Roles> roleSet = new HashSet<>();
        Roles role = new Roles(1, "USER");
        roleSet.add(role);

        var user = Users.builder()
                .name(req.getName())
                .surname(req.getSurname())
                .email(req.getEmail())
                .roles(roleSet)
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
