package com.intralink.user.micro.security.control;

import com.intralink.user.micro.model.Match;
import com.intralink.user.micro.model.Roles;
import com.intralink.user.micro.model.Users;
import com.intralink.user.micro.repository.MatchRepository;
import com.intralink.user.micro.security.JwtProvider;
import com.intralink.user.micro.service.MatchService;
import com.intralink.user.micro.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    @Autowired
    UserService userService;

    @Autowired
    MatchRepository matchRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authManager;

    public AuthenticationResponse register(RegisterRequest req) throws Exception {

        Set<Roles> roleSet = new HashSet<>();
        Roles role = new Roles(1, "USER");
        roleSet.add(role);

        Match match = new Match();
        var user = Users.builder()
                .name(req.getName())
                .surname(req.getSurname())
                .email(req.getEmail())
                .roles(roleSet)
                .password(passwordEncoder.encode(req.getPassword()))
                .country(req.getCountry())
                .city(req.getCity())
                .build();

        log.info("Saving user: {}", LocalDateTime.now());
        var usr = userService.save(user);
        //Creating table of matches
        match.setUser(usr);
        match.setIdMail(user.getEmail());
        matchRepository.save(match);
        var jwtToken = jwtProvider.generateToken(usr);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }


    public AuthenticationResponse authenticate(AuthenticationRequest req) {
        //If the user is correct, will recover it from DB
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getEmail(),
                        req.getPassword()
                )
        );
        var user = userService.findByEmail(req.getEmail())
                .orElseThrow();
        var jwtToken = jwtProvider.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}
