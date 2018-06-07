package com.tim.tinder.services.interfaces;

public interface UserService {

    void updateLocalization(long idUser, double lon, double lat);
    void save(String login, String password);
}
