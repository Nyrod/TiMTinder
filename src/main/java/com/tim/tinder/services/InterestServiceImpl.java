package com.tim.tinder.services;


import com.tim.tinder.config.CustomUserDetails;
import com.tim.tinder.entities.Interest;
import com.tim.tinder.entities.User;
import com.tim.tinder.repositories.InterestRepository;
import com.tim.tinder.repositories.UserRepository;
import com.tim.tinder.services.interfaces.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InterestServiceImpl implements InterestService {

    InterestRepository interestRepository;
    UserRepository userRepository;

    @Autowired
    public InterestServiceImpl(InterestRepository interestRepository, UserRepository userRepository) {
        this.interestRepository = interestRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Interest> getUserInterests(Long idUser) {
        User byId = userRepository.findOne(idUser);
        if(byId != null) {
            return byId.getInterests();
        }
        return new ArrayList<>();
    }

    @Override
    public void updateUserInterests(List<Long> idInterests) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByLogin(userDetails.getUsername());

        Iterable<Interest> allById = interestRepository.findAll(idInterests);
        List<Interest> interestList = new ArrayList<>();
        allById.forEach(interestList::add);

        user.setInterests(interestList);
        userRepository.save(user);
    }

    @Override
    public void addInterest(String interest) {
        Interest interestNew = new Interest();
        interestNew.setName(interest);
        interestRepository.save(interestNew);
    }

    @Override
    public int compareInterests(List<Interest> interests1, List<Interest> interests2) {
        int mutualInterest = 0;
        for(Interest interest : interests1) {
            if(interests2.contains(interest)) {
                mutualInterest++;
            }
        }
        return mutualInterest;
    }
}
