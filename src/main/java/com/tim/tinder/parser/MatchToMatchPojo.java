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
            if(match.getUserTo().getAvatar() != null) matchPojo.setAvatar(match.getUserTo().getAvatar().getIdPhoto());
            matchPojo.setName(match.getUserTo().getName());
            matchPojo.setSurname(match.getUserTo().getSurname());
            matchPojo.setIsFavourite(match.getFavouriteFrom());
        } else {
            matchPojo.setIdUser(match.getUserFrom().getIdUser());
            if(match.getUserFrom().getAvatar() != null) matchPojo.setAvatar(match.getUserFrom().getAvatar().getIdPhoto());
            matchPojo.setName(match.getUserFrom().getName());
            matchPojo.setSurname(match.getUserFrom().getSurname());
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
