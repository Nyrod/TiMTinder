package com.tim.tinder.services;

import com.tim.tinder.entities.User;
import com.tim.tinder.repositories.UserRepository;
import com.tim.tinder.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void updateLocalization(double lon, double lat) {
        userRepository.updateLocalization(lon, lat);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);

    }

    @Override
    public Iterable<User> findAllUsers() {
       return userRepository.findAll();
    }

    @Override
    public Optional<User> getUser(long idUser) {
        return userRepository.findById(idUser);
    }
}
