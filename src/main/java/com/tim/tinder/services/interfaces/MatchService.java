package com.tim.tinder.services.interfaces;

public interface MatchService {

    void giveLike(long userFrom, long userTo);

    void giveFavourite(long userFrom, long userTo);
}
