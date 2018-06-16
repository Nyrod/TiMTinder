package com.tim.tinder.controllers;

import com.tim.tinder.model.Response;
import com.tim.tinder.model.UserPojo;
import com.tim.tinder.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rest/user")
@CrossOrigin("http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUser")
    public ResponseEntity<UserPojo> getUser(@RequestParam("idUser")Long idUser) {
        return new ResponseEntity<>(userService.getUser(idUser), HttpStatus.OK);
    }

    @GetMapping("/userActive")
    public ResponseEntity<Boolean> currentUserActive(@RequestHeader("access_token")String token) {
        return new ResponseEntity<>(userService.isUserActive(token), HttpStatus.OK);
    }

    @GetMapping("/getCurrentUser")
    public ResponseEntity<UserPojo> getCurrentUser(@RequestHeader("access_token")String token) {
        return new ResponseEntity<>(userService.getCurrentUser(token), HttpStatus.OK);
    }

    @PostMapping("/updateUser")
    public ResponseEntity<UserPojo> updateUser(@RequestHeader("access_token")String token, @RequestBody UserPojo user) {
        return new ResponseEntity<>(userService.updateUser(token, user), HttpStatus.OK);
    }

    @PostMapping("/updateLocalization")
    public ResponseEntity<Response> updateLocalization(@RequestHeader("access_token")String token, @RequestParam("lon")Double lon, @RequestParam("lat")Double lat) {
        userService.updateLocalization(token, lon, lat);
        return new ResponseEntity<>(new Response("Zaktualizowano lokalizację"), HttpStatus.OK);
    }
}
