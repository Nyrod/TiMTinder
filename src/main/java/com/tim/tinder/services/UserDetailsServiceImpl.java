package com.tim.tinder.services;

import com.tim.tinder.entities.Role;
import com.tim.tinder.entities.User;
import com.tim.tinder.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);

        Role role = new Role();
        role.setName("pizda");
        Set<Role> set = new HashSet<Role>();
        set.add(role);
        user.setRoles(set);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role1 : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role1.getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getToken(), grantedAuthorities);
    }

}
