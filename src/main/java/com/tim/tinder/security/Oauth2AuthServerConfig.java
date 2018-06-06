package com.tim.tinder.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@Configuration
@EnableAuthorizationServer
public class Oauth2AuthServerConfig extends AuthorizationServerConfigurerAdapter {

//    @Autowired
//    AuthenticationManager authenticationManager;

//    private AuthenticationManager authenticationManager;
//    @Bean
//    public AuthenticationManager customAuthenticationManager() throws Exception {
//        return authenticationManager();
//    }
}
