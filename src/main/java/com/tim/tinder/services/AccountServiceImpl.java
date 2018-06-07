package com.tim.tinder.services;


import com.tim.tinder.entities.User;
import com.tim.tinder.repositories.UserRepository;
import com.tim.tinder.services.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final TokenStore tokenStore;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountServiceImpl(UserRepository userRepository, @Qualifier("getTokenStore")TokenStore tokenStore, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenStore = tokenStore;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void register(String login, String password) {
        User user = new User();
        user.setLogin(login);
        user.setToken(passwordEncoder.encode(password));
        user.setIsAdmin(false);
        userRepository.save(user);
        System.out.println(user.getToken());
    }

    @Override
    public boolean checkIfLoginExist(String login) {
        return userRepository.findByLogin(login) != null;
    }

    @Override
    public void logout() {
//        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        tokenStore.removeAccessToken(tokenStore.readAccessToken(userDetails.getPassword()));
    }
}
