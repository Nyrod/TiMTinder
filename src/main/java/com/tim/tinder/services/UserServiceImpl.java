package com.tim.tinder.services;

import com.tim.tinder.repositories.UserRepository;
import com.tim.tinder.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void updateLocalization(long idUser, double lon, double lat) {
        userRepository.updateLocalization(idUser, lon, lat);
    }
}
