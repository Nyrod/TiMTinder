package com.tim.tinder.services.interfaces;

import com.tim.tinder.config.CustomUserDetails;
import com.tim.tinder.entities.User;

import java.util.Optional;

public interface UserAuthenticationService {

    String login(String username, String password);

    Optional<User> findByToken(String token);

    void logout(CustomUserDetails user);
}
