package com.tim.tinder.controllers;

import com.tim.tinder.model.MatchPojo;
import com.tim.tinder.model.Response;
import com.tim.tinder.model.UserPojo;
import com.tim.tinder.services.interfaces.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rest/match")
@CrossOrigin("http://localhost:4200")
public class MatchController {

    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping("/giveLike")
    public ResponseEntity<MatchPojo> giveLike(@RequestParam("idUserTo")Long idUserTo) {
        return new ResponseEntity<>(matchService.giveLike(idUserTo), HttpStatus.OK);
    }

    @PostMapping("/giveFavourite")
    public ResponseEntity<MatchPojo> giveFavourite(@RequestParam("idMatch")Long idMatch) {
        return new ResponseEntity<>(matchService.giveFavourite(idMatch), HttpStatus.OK);
    }

    @GetMapping("/getAllMatches")
    public ResponseEntity<List<MatchPojo>> getAllMatches() {
        return new ResponseEntity<>(matchService.getAllMatches(), HttpStatus.OK);
    }

    @GetMapping("/getMatchesGiven")
    public ResponseEntity<List<MatchPojo>> getMatchesGiven() {
        return new ResponseEntity<>(matchService.getMatchesGiven(), HttpStatus.OK);
    }

    @GetMapping("/getMatchesReceived")
    public ResponseEntity<List<MatchPojo>> getMatchesReceived() {
        return new ResponseEntity<>(matchService.getMatchesReceived(), HttpStatus.OK);
    }

    @PostMapping("/deleteMatch")
    public ResponseEntity<Response> deleteMatch(@RequestParam("idMatch")Long idMatch) {
        matchService.deleteMatch(idMatch);
        return new ResponseEntity<>(new Response("Usunięto match o id " + idMatch), HttpStatus.OK);
    }

    @GetMapping("/getNext")
    public ResponseEntity<UserPojo> getNext(@RequestParam("idCurrent")Long idCurrent, @RequestParam("searchDistance")Double searchDistance) {
        return new ResponseEntity<>(matchService.getNextUser(idCurrent, searchDistance), HttpStatus.OK);
    }
}
