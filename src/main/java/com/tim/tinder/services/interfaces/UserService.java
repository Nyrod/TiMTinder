package com.tim.tinder.services.interfaces;

import com.tim.tinder.entities.User;
import com.tim.tinder.model.UserPojo;

import java.util.List;

public interface UserService {

    void updateLocalization(String token, double lon, double lat);

    UserPojo updateUser(String token, UserPojo userPojo);

    UserPojo getUser(Long idUser);

    UserPojo getCurrentUser(String token);

    User getUserEntity(Long idUser);

    boolean isUserActive(String token);

    List<UserPojo> gerAllUser(String token);

    void deleteUser(String token, Long idUser);
}
