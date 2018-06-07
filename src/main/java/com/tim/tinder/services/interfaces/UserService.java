package com.tim.tinder.services.interfaces;

import com.tim.tinder.model.UserPojo;

public interface UserService {

    void updateLocalization(double lon, double lat);

    UserPojo updateUser(UserPojo userPojo);

}
