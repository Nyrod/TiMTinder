package com.tim.tinder.security;

import com.tim.tinder.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;


public class CustomUserDetails implements UserDetails {

    private Collection<GrantedAuthority> authorities;
    private String login;
    private String token;

    public CustomUserDetails(User byUsername) {
        this.login = byUsername.getLogin();
        this.token = byUsername.getToken();
        GrantedAuthority auth = new SimpleGrantedAuthority(byUsername.getIsAdmin().toString()); // boolean toString dziala?
        authorities = new ArrayList<>();
        authorities.add(auth);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return token;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
