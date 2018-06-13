package com.tim.tinder.services;


import com.tim.tinder.entities.Token;
import com.tim.tinder.entities.User;
import com.tim.tinder.repositories.TokenRepository;
import com.tim.tinder.repositories.UserRepository;
import com.tim.tinder.services.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;

    @Autowired
    public AccountServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void register(String login, String password) {
        if (!checkIfLoginExist(login)) {
            User user = new User();
            user.setLogin(login);
            user.setPassword(passwordEncoder.encode(password));
            user.setIsAdmin(false);
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
                //String token = passwordEncoder.encode(new Date().toString() + password);
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
