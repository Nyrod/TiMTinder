package com.tim.tinder.services.interfaces;

import com.tim.tinder.entities.User;
import com.tim.tinder.model.UserPojo;

public interface UserService {

    void updateLocalization(String token, double lon, double lat);

    UserPojo updateUser(String token, UserPojo userPojo);

    UserPojo getUser(Long idUser);

    UserPojo getCurrentUser(String token);

    User getUserEntity(Long idUser);
}
