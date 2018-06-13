package com.tim.tinder.services.interfaces;

import com.tim.tinder.entities.Interest;

import java.util.List;

public interface InterestService {

    //List<Interest> getUserInterests(Long idUser);

    void updateUserInterests(String token, List<Long> idInterests);

    void addInterest(String token, String interest);

    int compareInterests(List<Interest> interests1, List<Interest> interests2);

}
