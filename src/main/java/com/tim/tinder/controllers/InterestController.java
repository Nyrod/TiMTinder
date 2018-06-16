package com.tim.tinder.controllers;

import com.tim.tinder.entities.Interest;
import com.tim.tinder.model.Response;
import com.tim.tinder.services.interfaces.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rest/interest")
@CrossOrigin("http://localhost:4200")
public class InterestController {

    private final InterestService interestService;

    @Autowired
    public InterestController(InterestService interestService) {
        this.interestService = interestService;
    }

    @PostMapping("/addInterest")
    public ResponseEntity<Response> addInterest(@RequestHeader("access_token")String token, @RequestParam("interest")String name) {
        interestService.addInterest(token, name);
        return new ResponseEntity<>(new Response("Dodano"), HttpStatus.OK);
    }

    @PostMapping("/updateInterest")
    public ResponseEntity<Response> updateInterest(@RequestHeader("access_token")String token, @RequestBody List<Long> interestIDs) {
        interestService.updateUserInterests(token, interestIDs);
        return new ResponseEntity<>(new Response("Dodano"), HttpStatus.OK);
    }

    @GetMapping("/getAllInterest")
    public ResponseEntity<List<Interest>> getAllInterest(@RequestHeader("access_token")String token) {
        return new ResponseEntity<>(interestService.getAllInterest(token), HttpStatus.OK);
    }
}
