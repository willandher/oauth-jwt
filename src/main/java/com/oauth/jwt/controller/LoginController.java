package com.oauth.jwt.controller;

import com.oauth.jwt.dto.LoginRequest;
import com.oauth.jwt.dto.UserDto;
import com.oauth.jwt.security.UserPrincipal;
import com.oauth.jwt.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    @ResponseStatus(OK)
    public UserPrincipal login(@Valid @RequestBody LoginRequest loginRequest) {
        return loginService.authenticateUser(loginRequest);
    }

    @PostMapping("/register-user")
    @ResponseStatus(OK)
    public UserPrincipal register(@Valid @RequestBody UserDto userDto) {
        return loginService.registerUser(userDto);
    }
}
