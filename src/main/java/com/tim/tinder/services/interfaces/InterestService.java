package com.tim.tinder.services.interfaces;

import com.tim.tinder.entities.Interest;

import java.util.List;

public interface InterestService {

    List<Interest> getUserInterests(long idUser);

    void updateUserInterests(long idUser, List<Long> idInterests);

    void addInterest(String interest);

}
