package com.tim.tinder.controllers;

import com.tim.tinder.model.Id;
import com.tim.tinder.model.Response;
import com.tim.tinder.services.interfaces.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/rest/photo")
@CrossOrigin("http://localhost:4200")
public class PhotoController {

    private PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @PostMapping("/addPhoto")
    public ResponseEntity<Long> addPhoto(@RequestBody MultipartFile photo) {
       return new ResponseEntity<>(photoService.addPhotoToUser(photo), HttpStatus.OK);
    }

    @PostMapping("/changeAvatar")
    public ResponseEntity<Long> changeAvatar(@RequestBody MultipartFile photo) {
        return new ResponseEntity<>(photoService.changeUserAvatar(photo), HttpStatus.OK);
    }

    @GetMapping("/getPhoto")
    public ResponseEntity<Resource> getPhoto(@RequestParam("idPhoto") Long idPhoto) {
        return new ResponseEntity<>(photoService.getPhoto(idPhoto), HttpStatus.OK);
    }

    @PostMapping("/deletePhoto")
    public ResponseEntity<Response> deletePhoto(@RequestBody Id idPhoto) {
        photoService.deletePhoto(idPhoto.getId());
        return new ResponseEntity<>(new Response("Usunieto zdjecie o id " + idPhoto.getId()), HttpStatus.OK);
    }
}
