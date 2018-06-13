package com.tim.tinder.services.interfaces;

import com.tim.tinder.model.MatchPojo;
import com.tim.tinder.model.UserPojo;

import java.util.List;

public interface MatchService {

    MatchPojo giveLike(String token, Long idUserTo);

    MatchPojo giveFavourite(String token, Long idMatch);

    List<MatchPojo> getAllMatches(String token);

    List<MatchPojo> getMatchesReceived(String token);

    List<MatchPojo> getMatchesGiven(String token);

    void deleteMatch(String token, Long idMatch);

    UserPojo getNextUser(String token, Long idCurrent, Double searchDistance);
}
