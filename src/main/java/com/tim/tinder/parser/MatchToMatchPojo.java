package com.tim.tinder.parser;


import com.tim.tinder.entities.Match;
import com.tim.tinder.model.MatchPojo;

import java.util.ArrayList;
import java.util.List;

public class MatchToMatchPojo {

    public static MatchPojo matchToMatchPojo(Match match, boolean isUserFrom) {
        MatchPojo matchPojo = new MatchPojo();
        matchPojo.setIdMatch(match.getIdMatch());
        matchPojo.setLikeDate(match.getLikeDate());
        if (isUserFrom) {
            matchPojo.setIdUser(match.getUserTo().getIdUser());
            matchPojo.setIsFavourite(match.getFavouriteFrom());
        } else {
            matchPojo.setIdUser(match.getUserFrom().getIdUser());
            matchPojo.setIsFavourite(match.getFavouriteTo());
        }

        return matchPojo;
    }

    public static List<MatchPojo> matchToMatchPojo(List<Match> matches, boolean isUserFrom) {
        List<MatchPojo> matchPojos = new ArrayList<>();
        for(Match match : matches) {
            matchPojos.add(matchToMatchPojo(match, isUserFrom));
        }
        return matchPojos;
    }
}
