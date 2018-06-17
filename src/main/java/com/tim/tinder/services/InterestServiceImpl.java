package com.tim.tinder.services;


import com.tim.tinder.entities.Interest;
import com.tim.tinder.entities.User;
import com.tim.tinder.repositories.InterestRepository;
import com.tim.tinder.repositories.UserRepository;
import com.tim.tinder.services.interfaces.InterestService;
import com.tim.tinder.services.interfaces.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InterestServiceImpl implements InterestService {

    InterestRepository interestRepository;
    UserRepository userRepository;
    TokenService tokenService;

    @Autowired
    public InterestServiceImpl(InterestRepository interestRepository, UserRepository userRepository, TokenService tokenService) {
        this.interestRepository = interestRepository;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @Override
    public void updateUserInterests(String token, List<Long> idInterests) {
        User user = tokenService.getUserByToken(token);
        Iterable<Interest> allById = interestRepository.findAllById(idInterests);
        List<Interest> interestList = new ArrayList<>();
        allById.forEach(interestList::add);

        user.setInterests(interestList);
        userRepository.save(user);
    }

    @Override
    public void addInterest(String token, String interest) {
        User user = tokenService.getUserByToken(token);
        if(user.getIsAdmin()) {
            Interest interestNew = new Interest();
            interestNew.setName(interest);
            interestRepository.save(interestNew);
        }
    }

    @Override
    public List<Interest> getAllInterest(String token) {
        List<Interest> target = new ArrayList<>();
        interestRepository.findAll().forEach(target::add);
        return target;
    }

    @Override
    public int compareInterests(List<Interest> interests1, List<Interest> interests2) {
        int mutualInterest = 0;
        interests1.stream().forEach(System.out::println);
        interests2.stream().forEach(System.out::println);
        if(interests1.size() > interests2.size()) {
            for(Interest interest : interests1) {
                if(interests2.contains(interest)) {
                    mutualInterest++;
                }
            }
        } else {
            for(Interest interest : interests2) {
                if(interests1.contains(interest)) {
                    mutualInterest++;
                }
            }
        }

        return mutualInterest;
    }
}
