package com.tim.tinder.services.interfaces;

import com.tim.tinder.model.MatchPojo;
import com.tim.tinder.model.UserPojo;

import java.util.List;

public interface MatchService {

    MatchPojo giveLike(Long idUserTo);

    MatchPojo giveFavourite(Long idMatch);

    List<MatchPojo> getAllMatches();

    List<MatchPojo> getMatchesReceived();

    List<MatchPojo> getMatchesGiven();

    void deleteMatch(Long idMatch);

    UserPojo getNextUser(Long idCurrent, Double searchDistance);
}
