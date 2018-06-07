package com.tim.tinder.controllers;

import com.tim.tinder.config.CustomUserDetails;
import com.tim.tinder.services.interfaces.UserAuthenticationService;
import com.tim.tinder.services.interfaces.UserCrudService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Controller
@RequestMapping("/public/users")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
public class AccountController {

//    private AccountService accountService;
//
//    @Autowired
//    public AccountController(AccountService accountService) {
//        this.accountService = accountService;
//    }
//
//    @RequestMapping(path = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public String getHello() {
//        return "helloWorld";
//    }
//
//    @RequestMapping(path = "/private", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public String getPrivate() {
//        return "private";
//    }
//
//    @RequestMapping(value = "/rest/checkIfLoginExist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Boolean> checkIfLoginExist(@RequestParam("login") String login) {
//        Boolean ifLoginExist = accountService.checkIfLoginExist(login);
//        return new ResponseEntity<>(ifLoginExist, HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/rest/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Response> logout() {
//        accountService.logout();
//        return new ResponseEntity<>(new Response("Logout"), HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "/rest/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Response> register(@RequestParam("login") String login, @RequestParam("password") String password) {
//        accountService.register(login, password);
//        return new ResponseEntity<>(new Response("Registered"), HttpStatus.OK);
//    }

    @NonNull
    UserAuthenticationService authentication;
    @NonNull
    UserCrudService users;

    @PostMapping("/register")
    ResponseEntity<String> register(@RequestParam("username") final String username, @RequestParam("password") final String password) {
        users.save(
                CustomUserDetails
                        .builder()
                        .id(username)
                        .username(username)
                        .password(password)
                        .build()
                );
        System.out.println("jestem");
        return new ResponseEntity<>(login(username, password), HttpStatus.OK);
    }

    @PostMapping("/login")
    String login(@RequestParam("username") final String username, @RequestParam("password") final String password) {
        System.out.println("dasdas");
        return authentication.login(username, password);
    }
}
