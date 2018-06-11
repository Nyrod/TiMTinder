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

    @GetMapping("/getUser")
    public ResponseEntity<UserPojo> getUser(@RequestParam("idUser")Long idUser) {
        return new ResponseEntity<>(userService.getUser(idUser), HttpStatus.OK);
    }

    @GetMapping("/getCurrentUser")
    public ResponseEntity<UserPojo> getUser() {
        return new ResponseEntity<>(userService.getCurrentUser(), HttpStatus.OK);
    }

    @PostMapping("/updateUser")
    public ResponseEntity<UserPojo> updateUser(@RequestParam("user")UserPojo user) {
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }

    @PostMapping("/updateLocalization")
    public ResponseEntity<Response> updateLocalization(@RequestParam("lon")Double lon, @RequestParam("lat")Double lat) {
        userService.updateLocalization(lon, lat);
        return new ResponseEntity<>(new Response("Zaktualizowano lokalizację"), HttpStatus.OK);
    }
}
