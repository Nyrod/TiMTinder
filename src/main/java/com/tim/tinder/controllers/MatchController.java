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
    public ResponseEntity<MatchPojo> giveLike(@RequestHeader("access_token")String token, @RequestParam("idUserTo")Long idUserTo) {
        return new ResponseEntity<>(matchService.giveLike(token, idUserTo), HttpStatus.OK);
    }

    @PostMapping("/giveFavourite")
    public ResponseEntity<MatchPojo> giveFavourite(@RequestHeader("access_token")String token, @RequestParam("idMatch")Long idMatch) {
        return new ResponseEntity<>(matchService.giveFavourite(token, idMatch), HttpStatus.OK);
    }

    @GetMapping("/getAllMatches")
    public ResponseEntity<List<MatchPojo>> getAllMatches(@RequestHeader("access_token")String token) {
        return new ResponseEntity<>(matchService.getAllMatches(token), HttpStatus.OK);
    }

    @GetMapping("/getMatchesGiven")
    public ResponseEntity<List<MatchPojo>> getMatchesGiven(@RequestHeader("access_token")String token) {
        return new ResponseEntity<>(matchService.getMatchesGiven(token), HttpStatus.OK);
    }

    @GetMapping("/getMatchesReceived")
    public ResponseEntity<List<MatchPojo>> getMatchesReceived(@RequestHeader("access_token")String token) {
        return new ResponseEntity<>(matchService.getMatchesReceived(token), HttpStatus.OK);
    }

    @PostMapping("/deleteMatch")
    public ResponseEntity<Response> deleteMatch(@RequestHeader("access_token")String token, @RequestParam("idMatch")Long idMatch) {
        matchService.deleteMatch(token, idMatch);
        return new ResponseEntity<>(new Response("Usunięto match o id " + idMatch), HttpStatus.OK);
    }

    @GetMapping("/getNext")
    public ResponseEntity<UserPojo> getNext(@RequestHeader("access_token")String token, @RequestParam("idCurrent")Long idCurrent, @RequestParam("searchDistance")Double searchDistance) {
        return new ResponseEntity<>(matchService.getNextUser(token, idCurrent, searchDistance), HttpStatus.OK);
    }
}
