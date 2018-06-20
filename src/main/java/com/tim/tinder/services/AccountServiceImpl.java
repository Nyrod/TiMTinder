package com.tim.tinder.services;


import com.tim.tinder.entities.Interest;
import com.tim.tinder.entities.Match;
import com.tim.tinder.entities.Token;
import com.tim.tinder.entities.User;
import com.tim.tinder.repositories.InterestRepository;
import com.tim.tinder.repositories.MatchRepository;
import com.tim.tinder.repositories.TokenRepository;
import com.tim.tinder.repositories.UserRepository;
import com.tim.tinder.services.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final MatchRepository matchRepository;
    private final InterestRepository interestRepository;


    @Autowired
    public AccountServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenRepository tokenRepository, MatchRepository matchRepository, InterestRepository interestRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
        this.matchRepository = matchRepository;
        this.interestRepository = interestRepository;
    }

    @Override
    public void register(String login, String password) {
        Interest interest = new Interest();
        interest.setName("Ksizka");
        interestRepository.save(interest);
        Interest interest2 = new Interest();
        interest2.setName("Sport");
        interestRepository.save(interest2);
        Interest interest3 = new Interest();
        interest3.setName("Rower");
        interestRepository.save(interest3);


        User user2 = new User();
        user2.setLogin("Lewy");
        user2.setPassword(passwordEncoder.encode("123"));
        user2.setName("Robert");
        user2.setSurname("Lewandowski");
        user2.setSex("M");
        user2.setLookingFor("B");
        user2.setDescription("SZCZELAM KARNE");
        user2.setIsAdmin(false);
        user2.setBirthday(new GregorianCalendar(1980, 8, 15).getTime());
        user2.setPhone("248 598 127");
        user2.setLat(52.171828);
        user2.setLon(20.7964463);
        user2.setInterests(new ArrayList<>());
        user2.getInterests().add(interest2);
        userRepository.save(user2);

        User user3 = new User();
        user3.setLogin("Milik");
        user3.setPassword(passwordEncoder.encode("123"));
        user3.setName("Arkadiusz");
        user3.setSurname("Milik");
        user3.setSex("M");
        user3.setLookingFor("F");
        user3.setDescription("PUDLUJE");
        user3.setIsAdmin(false);
        user3.setBirthday(new GregorianCalendar(1950, 8, 15).getTime());
        user3.setPhone("248 598 127");
        user3.setInterests(new ArrayList<>());
        user3.getInterests().add(interestRepository.findById(2L).get());
        user3.setLat(52.171828);
        user3.setLon(20.7964463);
        userRepository.save(user3);

        User user4 = new User();
        user4.setLogin("Glik");
        user4.setPassword(passwordEncoder.encode("123"));
        user4.setName("Kamil");
        user4.setSurname("Glik");
        user4.setSex("M");
        user4.setLookingFor("F");
        user4.setDescription("JESTEM PIZADa");
        user4.setIsAdmin(false);
        user4.setBirthday(new GregorianCalendar(1990, 8, 15).getTime());
        user4.setPhone("248 598 127");
        user4.setLat(52.171828);
        user4.setLon(20.7964463);
        userRepository.save(user4);

        Match match = new Match();
        match.setUserTo(user2);
        match.setUserFrom(user3);
        match.setIsMatched(false);
        match.setLikeDate(new Date());
        match.setFavouriteTo(false);
        match.setFavouriteFrom(false);
        matchRepository.save(match);

        Match match2 = new Match();
        match2.setUserTo(user2);
        match2.setUserFrom(user4);
        match2.setIsMatched(false);
        match2.setLikeDate(new Date());
        match2.setFavouriteTo(false);
        match2.setFavouriteFrom(false);
        matchRepository.save(match2);

        Match match4 = new Match();
        match4.setUserTo(user3);
        match4.setUserFrom(user2);
        match4.setIsMatched(false);
        match4.setLikeDate(new Date());
        match4.setFavouriteTo(false);
        match4.setFavouriteFrom(false);
        matchRepository.save(match4);

        Match match5 = new Match();
        match5.setUserTo(user4);
        match5.setUserFrom(user2);
        match5.setIsMatched(false);
        match5.setLikeDate(new Date());
        match5.setFavouriteTo(false);
        match5.setFavouriteFrom(false);
        matchRepository.save(match5);

        Match match3 = new Match();
        match3.setUserTo(user3);
        match3.setUserFrom(user2);
        match3.setIsMatched(true);
        match3.setLikeDate(new Date());
        match3.setFavouriteTo(false);
        match3.setFavouriteFrom(true);
        matchRepository.save(match3);

        if (!checkIfLoginExist(login)) {
            User user = new User();
            user.setLogin(login);
            user.setPassword(passwordEncoder.encode(password));
            user.setIsAdmin(false);
            if(login.equals("admin"))
                user.setIsAdmin(true);
            userRepository.save(user);
        }
    }

    @Override
    public boolean checkIfLoginExist(String login) {
        return userRepository.findByLogin(login) != null;
    }

    @Override
    public void logout(String token) {
        Token byToken = tokenRepository.findByToken(token);
        if (byToken != null) {
            tokenRepository.delete(byToken);
        }
    }

    @Override
    public String login(String login, String password) {
        User user = userRepository.findByLogin(login);

        if (user == null) {
            return null;
        } else {
            Token byToken = tokenRepository.findByIdUser(user.getIdUser());
            if (byToken != null) {
                return byToken.getToken();
            }
            if (passwordEncoder.matches(password, user.getPassword())) {
                String token = passwordEncoder.encode(System.currentTimeMillis() + password);
                Token tokenEntity = new Token();
                tokenEntity.setIdUser(user.getIdUser());
                tokenEntity.setToken(token);
                tokenRepository.save(tokenEntity);
                return token;
            } else {
                return null;
            }
        }
    }

    @Override
    public Long getIdUserByToken(String token) {
        Token tokenEntity = tokenRepository.findByToken(token);
        if (tokenEntity != null) {
            return tokenEntity.getIdUser();
        } else {
            return null;
        }
    }
}
