package com.tim.tinder.services;

import com.tim.tinder.entities.Role;
import com.tim.tinder.entities.User;
import com.tim.tinder.repositories.RoleRepository;
import com.tim.tinder.repositories.UserRepository;
import com.tim.tinder.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void updateLocalization(long idUser, double lon, double lat) {
        userRepository.updateLocalization(idUser, lon, lat);
    }

    @Override
    public void save(String login, String password) {
        User user = new User();
        user.setLogin(login);
        user.setToken(bCryptPasswordEncoder.encode(password));
        Set<Role> roles = new HashSet<>();
        roleRepository.findAll().forEach(roles::add);
        user.setRoles(roles);
        userRepository.save(user);
    }

}
