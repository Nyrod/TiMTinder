package com.tim.tinder;

import com.tim.tinder.config.CustomUserDetails;
import com.tim.tinder.repositories.UserRepository;
import com.tim.tinder.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TinderApplication{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder;


    public static void main(String[] args) {
        SpringApplication.run(TinderApplication.class, args);
    }

    @Autowired
    public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repository, UserServiceImpl userService) throws Exception {

        builder.userDetailsService(userDetailsService(repository)).passwordEncoder(passwordEncoder);
    }

    private UserDetailsService userDetailsService(final UserRepository repository) {
        return login -> new CustomUserDetails(repository.findByLogin(login));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}