package com.tim.tinder.services.interfaces;


public interface SecurityService {

     String findLoggedInUsername();

     void autologin(String username, String password);
}
