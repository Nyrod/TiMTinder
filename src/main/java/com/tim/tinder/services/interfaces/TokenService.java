package com.tim.tinder.services.interfaces;


import com.tim.tinder.entities.User;
import com.tim.tinder.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final AccountService accountService;
    private final UserRepository userRepository;

    @Autowired
    public TokenService(AccountService accountService, UserRepository userRepository) {
        this.accountService = accountService;
        this.userRepository = userRepository;
    }

    public Long getIdUserByToken(String token) {
        return accountService.getIdUserByToken(token);
    }

    public User getUserByToken(String token) {
        Long idUser = getIdUserByToken(token);
        if (idUser == null) {
            return null;
        }
        return userRepository.findById(idUser).get();
    }
}
