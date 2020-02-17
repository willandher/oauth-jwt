package com.oauth.jwt.service;

import com.oauth.jwt.dto.LoginRequest;
import com.oauth.jwt.model.User;
import com.oauth.jwt.repository.UserRepository;
import com.oauth.jwt.security.JwtTokenProvider;
import com.oauth.jwt.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginService {

    private static final Logger log = LoggerFactory.getLogger(LoginService.class);
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private JwtTokenProvider tokenProvider;

    @Autowired
    public LoginService(AuthenticationManager authenticationManager, UserRepository userRepository, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
    }

    public UserPrincipal authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        userPrincipal.setToken(jwt);
        log.info("User with [email: {}] has logged in", userPrincipal.getEmail());

        User user = userRepository.findByEmail(userPrincipal.getEmail()).get();
        user.setJwt(jwt).setUpdate(new Date()).setLastLogin(new Date());
        userRepository.save(user);

        return userPrincipal;
    }


}
