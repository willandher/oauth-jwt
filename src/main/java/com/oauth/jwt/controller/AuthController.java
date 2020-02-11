package com.oauth.jwt.controller;

import com.oauth.jwt.dto.LoginRequest;
import com.oauth.jwt.dto.UserDto;
import com.oauth.jwt.model.User;
import com.oauth.jwt.security.UserPrincipal;
import com.oauth.jwt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @ResponseStatus(OK)
    public UserPrincipal login(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    @PostMapping("/register-user")
    @ResponseStatus(OK)
    public User register(@Valid @RequestBody UserDto userDto) {
        return authService.registerUser(userDto);
    }
}
