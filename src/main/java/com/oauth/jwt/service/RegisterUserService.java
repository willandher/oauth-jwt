package com.oauth.jwt.service;

import com.oauth.jwt.dto.UserDto;
import com.oauth.jwt.exception.ConflictException;
import com.oauth.jwt.model.User;
import com.oauth.jwt.repository.UserRepository;
import com.oauth.jwt.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RegisterUserService {

    private static final Logger log = LoggerFactory.getLogger(RegisterUserService.class);
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserPrincipal registerUser(UserDto userDto) {

        if(userRepository.existsByEmail(userDto.getEmail())) {
            throw new ConflictException("correo: " + userDto.getEmail() + " ya existe");
        }

        User user = new User(userDto.getName(), userDto.getEmail(), userDto.getPassword(), userDto.getPhones(),new Date(), new Date(),null,true);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return UserPrincipal.create(userRepository.save(user));
    }
}
