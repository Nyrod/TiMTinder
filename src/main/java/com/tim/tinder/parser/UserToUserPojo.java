package com.tim.tinder.parser;

import com.tim.tinder.entities.User;
import com.tim.tinder.model.UserPojo;

import java.util.ArrayList;
import java.util.List;

public class UserToUserPojo {

    public static UserPojo userToUserPojo(User user) {
        UserPojo userPojo = new UserPojo();
        userPojo.setIdUser(user.getIdUser());
        userPojo.setName(user.getName());
        userPojo.setName(user.getName());
        userPojo.setSurname(user.getSurname());
        userPojo.setPhone(user.getPhone());
        userPojo.setBirthday(user.getBirthday());
        userPojo.setDescription(user.getDescription());
        userPojo.setIsAdmin(user.getIsAdmin());
        userPojo.setSex(user.getSex());
        userPojo.setLookingFor(user.getLookingFor());
        List<Long> ids = new ArrayList<>();
        user.getPhotos().forEach(a -> ids.add(a.getIdPhoto()));
        userPojo.setPhotos(ids);
        if(user.getAvatar() != null) {
            userPojo.setAvatar(user.getAvatar().getIdPhoto());
        }

        return userPojo;
    }
}
