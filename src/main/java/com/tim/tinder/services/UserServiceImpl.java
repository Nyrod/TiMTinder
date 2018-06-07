package com.tim.tinder.services;

import com.tim.tinder.config.CustomUserDetails;
import com.tim.tinder.entities.User;
import com.tim.tinder.model.UserPojo;
import com.tim.tinder.repositories.UserRepository;
import com.tim.tinder.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void updateLocalization(double lon, double lat) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByLogin(userDetails.getUsername());
        userRepository.updateLocalization(user.getIdUser(), lon, lat);
    }

    @Override
    public UserPojo updateUser(UserPojo userPojo) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByLogin(userDetails.getUsername());
        if (userPojo.getIdUser().equals(user.getIdUser())) {
            updateUserFields(userPojo, user);
            userRepository.save(user);
        } else if (user.getIsAdmin()) {
            user = userRepository.findOne(userPojo.getIdUser());
            updateUserFields(userPojo, user);
            userRepository.save(user);
        }
        return userPojo;
    }

    private void updateUserFields(UserPojo userPojo, User user) {
        user.setName(userPojo.getName());
        user.setSurname(userPojo.getSurname());
        user.setPhone(userPojo.getPhone());
        user.setBirthday(userPojo.getBirthday());
        user.setDescription(userPojo.getDescription());
        user.setIsAdmin(userPojo.getIsAdmin());
        user.setSex(userPojo.getSex());
        user.setLookingFor(userPojo.getLookingFor());
    }
}
