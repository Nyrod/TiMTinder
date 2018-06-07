package com.tim.tinder.services.interfaces;

import com.tim.tinder.config.CustomUserDetails;
import com.tim.tinder.entities.User;

import java.util.Optional;

public interface UserCrudService {

    User save(CustomUserDetails user);

    Optional<User> find(Long id);

    Optional<User> findByUsername(String login);
}

