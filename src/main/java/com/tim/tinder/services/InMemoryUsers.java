package com.tim.tinder.services;

import com.tim.tinder.config.CustomUserDetails;
import com.tim.tinder.entities.User;
import com.tim.tinder.repositories.UserRepository;
import com.tim.tinder.services.interfaces.UserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
final class InMemoryUsers implements UserCrudService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InMemoryUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(final CustomUserDetails userDetails) {
        User user = new User();
        user.setLogin(userDetails.getUsername());
        user.setToken(passwordEncoder.encode(userDetails.getPassword()));

        userRepository.save(user);
        return user;
    }

    @Override
    public Optional<User> find(final Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(final String login) {
        return ofNullable(userRepository.findByLogin(login));
    }
}