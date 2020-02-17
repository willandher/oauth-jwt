package com.oauth.jwt.controller;

import com.oauth.jwt.dto.UserDto;
import com.oauth.jwt.security.UserPrincipal;
import com.oauth.jwt.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
public class RegisterUserController {


    private RegisterUserService registerUserService;

    @Autowired
    public RegisterUserController( RegisterUserService registerUserService) {
        this.registerUserService = registerUserService;
    }
    @PostMapping("/register-user")
    @ResponseStatus(OK)
    public UserPrincipal register(@Valid @RequestBody UserDto userDto) {
        return registerUserService.registerUser(userDto);
    }
}
