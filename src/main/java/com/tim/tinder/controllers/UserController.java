package com.tim.tinder.controllers;

import com.tim.tinder.model.Response;
import com.tim.tinder.model.UserPojo;
import com.tim.tinder.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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
    public ResponseEntity<UserPojo> getCurrentUser(@AuthenticationPrincipal User user) {
        System.out.println("USER=   " + user);

        System.out.println("getCredentials()=  " + SecurityContextHolder.getContext().getAuthentication().getCredentials());
        System.out.println("getDetails()=  " + SecurityContextHolder.getContext().getAuthentication().getDetails());
        System.out.println("getName()=  " + SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println("getPrincipal()=  " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        System.out.println("isAuthenticated()=  " + SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
        System.out.println("getPrincipal().getClass=  " + SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass());

        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

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
