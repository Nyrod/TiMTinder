package com.tim.tinder.services;

import com.tim.tinder.config.CustomUserDetails;
import com.tim.tinder.entities.Match;
import com.tim.tinder.entities.User;
import com.tim.tinder.model.MatchPojo;
import com.tim.tinder.model.UserPojo;
import com.tim.tinder.parser.MatchToMatchPojo;
import com.tim.tinder.parser.UserToUserPojo;
import com.tim.tinder.repositories.MatchRepository;
import com.tim.tinder.repositories.UserRepository;
import com.tim.tinder.services.interfaces.InterestService;
import com.tim.tinder.services.interfaces.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {

    private final UserRepository userRepository;
    private final MatchRepository matchRepository;
    private final InterestService interestService;
    private final EntityManager entityManager;

    @Autowired
    public MatchServiceImpl(UserRepository userRepository, MatchRepository matchRepository, InterestService interestService, EntityManager entityManager) {
        this.userRepository = userRepository;
        this.matchRepository = matchRepository;
        this.interestService = interestService;
        this.entityManager = entityManager;
    }


    @Override
    public MatchPojo giveLike(Long idUserTo) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userFrom = userRepository.findByLogin(userDetails.getUsername());
        User userTo = userRepository.findOne(idUserTo);

        List<Match> matchesGiven = userTo.getMatchesGiven();
        for (Match match : matchesGiven) {
            if (match.getUserTo().getIdUser().equals(userFrom.getIdUser())) {
                match.setIsMatched(true);
                match.setLikeDate(new Date());
                matchRepository.save(match);
                return MatchToMatchPojo.matchToMatchPojo(match, false);
            }
        }
        Match match = new Match();
        match.setIsMatched(false);
        match.setUserFrom(userFrom);
        match.setUserTo(userTo);
        match.setFavouriteFrom(false);
        match.setFavouriteTo(false);
        match.setLikeDate(new Date());
        matchRepository.save(match);

        return MatchToMatchPojo.matchToMatchPojo(match, true);
    }

    @Override
    public MatchPojo giveFavourite(Long idMatch) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userFrom = userRepository.findByLogin(userDetails.getUsername());

        Match match = matchRepository.findOne(idMatch);
        if (match.getUserFrom().getIdUser().equals(userFrom.getIdUser())) {
            match.setFavouriteFrom(true);
            matchRepository.save(match);
            return MatchToMatchPojo.matchToMatchPojo(match, true);
        } else {
            match.setFavouriteTo(true);
            matchRepository.save(match);
            return MatchToMatchPojo.matchToMatchPojo(match, false);
        }

    }

    @Override
    public List<MatchPojo> getAllMatches() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userFrom = userRepository.findByLogin(userDetails.getUsername());

        List<MatchPojo> matches = new ArrayList<>();

        for (Match match : userFrom.getMatchesGiven()) {
            if (match.getIsMatched()) {
                matches.add(MatchToMatchPojo.matchToMatchPojo(match, true));
            }
        }

        for (Match match : userFrom.getMatchesReceived()) {
            if (match.getIsMatched()) {
                matches.add(MatchToMatchPojo.matchToMatchPojo(match, false));
            }
        }
        return matches;
    }

    @Override
    public List<MatchPojo> getMatchesGiven() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userFrom = userRepository.findByLogin(userDetails.getUsername());

        List<MatchPojo> matches = new ArrayList<>();

        for (Match match : userFrom.getMatchesGiven()) {
            if (!match.getIsMatched()) {
                matches.add(MatchToMatchPojo.matchToMatchPojo(match, true));
            }
        }
        return matches;
    }

    @Override
    public void deleteMatch(Long idMatch) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userFrom = userRepository.findByLogin(userDetails.getUsername());

        Match match = matchRepository.findOne(idMatch);
        if(match.getIsMatched()) {
            if (match.getUserFrom().getIdUser().equals(userFrom.getIdUser())) {
                match.setIsMatched(false);
                match.setUserFrom(match.getUserTo());
                match.setUserTo(null);
                match.setFavouriteFrom(match.getFavouriteTo());
                match.setFavouriteTo(false);

            } else {
                match.setIsMatched(false);
                match.setUserTo(null);
                match.setFavouriteTo(false);
            }
            matchRepository.save(match);
        } else {
            matchRepository.delete(idMatch);
        }
    }

    @Override
    public List<MatchPojo> getMatchesReceived() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userFrom = userRepository.findByLogin(userDetails.getUsername());

        List<MatchPojo> matches = new ArrayList<>();

        for (Match match : userFrom.getMatchesReceived()) {
            if (!match.getIsMatched()) {
                matches.add(MatchToMatchPojo.matchToMatchPojo(match, false));
            }
        }
        return matches;
    }

    @Override
    public UserPojo getNextUser(Long idCurrent, Double searchDistance) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByLogin(userDetails.getUsername());
        boolean found = false;
        List<User> userList = getTenUser(idCurrent);
        while(!found) {
            if(userList.size() == 0)
                found = true;
            for(User userCheck : userList) {
                if(!userCheck.getIdUser().equals(user.getIdUser())) {
                    if(distanceBetweenPoints(user.getLon(), userCheck.getLon(), user.getLat(), userCheck.getLat()) <= searchDistance) {
                        if(interestService.compareInterests(user.getInterests(), userCheck.getInterests()) >= user.getInterests().size()/3) {
                            return UserToUserPojo.userToUserPojo(user);
                        }
                    }
                }
            }
        }
        return new UserPojo();
    }

    private List<User> getTenUser(Long idCurrent) {
        return entityManager.createQuery("SELECT u from  WHERE u.idUser > " + idCurrent + " ORDER BY u.idUser asc ", User.class)
                .setMaxResults(10)
                .getResultList();
    }

    private double distanceBetweenPoints(double lon1, double lon2, double lat1, double lat2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = 0;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

}
