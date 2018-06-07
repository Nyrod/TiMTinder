package com.tim.tinder.services.interfaces;


public interface AccountService {

    void register(String login, String password);

    boolean checkIfLoginExist(String login);

    void logout();
}
