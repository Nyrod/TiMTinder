package com.tim.tinder.services.interfaces;


import com.tim.tinder.entities.Interest;

import java.util.List;

public interface InterestService {

    void getUserInterests(long idUser);

    void updateUserInterests(long idUser, List<Long> idsInterests);

    void addInterest(Interest interest);

}
