package com.oauth.jwt.config;


import com.oauth.jwt.model.User;
import com.oauth.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.HashSet;

@Configuration
public class InitialConfig {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public void registerInitial(){
        User user = new User();
        user.setName("Willandher Goyo");
        user.setActivo(true);
        user.setEmail("willandherg@gmail.com");
        user.setPassword(passwordEncoder.encode("Will22"));
        user.setCreated(new Date());
        user.setUpdate(new Date());
        user.setPhoness(new HashSet<>());
        userRepository.save(user);
    }


}
