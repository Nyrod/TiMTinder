package com.tim.tinder.services;


import com.tim.tinder.entities.Interest;
import com.tim.tinder.entities.User;
import com.tim.tinder.repositories.InterestRepository;
import com.tim.tinder.repositories.UserRepository;
import com.tim.tinder.services.interfaces.InterestService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InterestServiceImpl implements InterestService {

    InterestRepository interestRepository;
    UserRepository userRepository;

    @Autowired
    public InterestServiceImpl(InterestRepository interestRepository, UserRepository userRepository) {
        this.interestRepository = interestRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Interest> getUserInterests(long idUser) {
        Optional<User> byId = userRepository.findById(idUser);
        if(byId.isPresent()) {
            return byId.get().getInterests();
        }
        return new ArrayList<>();
    }

    @Override
    public void updateUserInterests(long idUser, List<Long> idInterests) {
        Iterable<Interest> allById = interestRepository.findAllById(idInterests);
        List<Interest> interestList = new ArrayList<>();
        allById.forEach(interestList::add);
        Optional<User> byId = userRepository.findById(idUser);
        if(byId.isPresent()) {
            User user = byId.get();
            user.setInterests(interestList);
            userRepository.save(user);
        }
    }

    @Override
    public void addInterest(String interest) {
        Interest interestNew = new Interest();
        interestNew.setName(interest);
        interestRepository.save(interestNew);
    }
}
