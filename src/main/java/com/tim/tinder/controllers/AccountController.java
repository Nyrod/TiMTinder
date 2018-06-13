package com.tim.tinder.controllers;

import com.tim.tinder.model.Register;
import com.tim.tinder.model.Response;
import com.tim.tinder.services.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rest")
@CrossOrigin("http://localhost:4200")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "/checkIfLoginExist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> checkIfLoginExist(@RequestParam("login") String login) {
        Boolean ifLoginExist = accountService.checkIfLoginExist(login);
        return new ResponseEntity<>(ifLoginExist, HttpStatus.OK);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> logout() {
        accountService.logout();
        return new ResponseEntity<>(new Response("Logout"), HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> register(@RequestBody Register register) {
        accountService.register(register.getLogin(), register.getPassword());
        return new ResponseEntity<>(new Response("Registered"), HttpStatus.OK);
    }

}
