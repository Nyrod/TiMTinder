package com.tim.tinder.services;

import com.tim.tinder.entities.User;
import com.tim.tinder.model.UserPojo;
import com.tim.tinder.parser.UserToUserPojo;
import com.tim.tinder.repositories.UserRepository;
import com.tim.tinder.services.interfaces.TokenService;
import com.tim.tinder.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @Override
    public void updateLocalization(String token, double lon, double lat) {
        User user = tokenService.getUserByToken(token);
        userRepository.updateLocalization(user.getIdUser(), lon, lat);
    }

    @Override
    public UserPojo updateUser(String token, UserPojo userPojo) {
        User user = tokenService.getUserByToken(token);
        System.out.println(userPojo);
        if (userPojo.getIdUser().equals(user.getIdUser())) {
            updateUserFields(userPojo, user);
            userRepository.save(user);
        } else if (user.getIsAdmin()) {
            user = userRepository.findById(userPojo.getIdUser()).get();
            updateUserFields(userPojo, user);
            userRepository.save(user);
        }
        return userPojo;
    }

    @Override
    public UserPojo getUser(Long idUser) {
        User user = userRepository.findById(idUser).get();
        if(user == null) {
            return new UserPojo();
        }
        return UserToUserPojo.userToUserPojo(user);
    }

    @Override
    public UserPojo getCurrentUser(String token) {
        User user = tokenService.getUserByToken(token);
        if (user == null) {
            return new UserPojo();
        }
        System.out.println(UserToUserPojo.userToUserPojo(user));
        return UserToUserPojo.userToUserPojo(user);
    }

    @Override
    public User getUserEntity(Long idUser) {
        return userRepository.findById(idUser).get();
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
