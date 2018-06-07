package com.tim.tinder.services.interfaces;

import com.tim.tinder.entities.User;

import java.util.Optional;

public interface UserService {

    void updateLocalization(long idUser, double lon, double lat);

    void updateUser(User user);

    Iterable<User> findAllUsers();

    Optional<User> getUser(long idUser);
}
