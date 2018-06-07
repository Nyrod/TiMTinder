package com.tim.tinder.config;

import com.tim.tinder.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private Collection<GrantedAuthority> authorities;
    private String password;
    private String login;

    public CustomUserDetails(User byUsername) {
        this.login = byUsername.getLogin();
        this.password = byUsername.getToken();
        GrantedAuthority auth = new SimpleGrantedAuthority(byUsername.getIsAdmin().toString());
        authorities = new ArrayList<>();
        authorities.add(auth);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
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
