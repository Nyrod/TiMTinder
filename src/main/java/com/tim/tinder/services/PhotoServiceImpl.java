package com.tim.tinder.services;

import com.tim.tinder.entities.Photo;
import com.tim.tinder.entities.User;
import com.tim.tinder.repositories.PhotoRepository;
import com.tim.tinder.repositories.UserRepository;
import com.tim.tinder.services.interfaces.PhotoService;
import com.tim.tinder.services.interfaces.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final UserRepository userRepository;

    private final PhotoRepository photoRepository;
    private final TokenService tokenService;

    @Autowired
    public PhotoServiceImpl(UserRepository userRepository, PhotoRepository photoRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.photoRepository = photoRepository;
        this.tokenService = tokenService;
    }


    @Override
    public Long addPhotoToUser(String token, MultipartFile file) {
        User user = tokenService.getUserByToken(token);
        Photo photo = getPhotoFromFile(file);
        photo = photoRepository.save(photo);
        user.getPhotos().add(photo);
        userRepository.save(user);
        return photo.getIdPhoto();
    }

    @Override
    public Long changeUserAvatar(String token, MultipartFile file) {
        User user = tokenService.getUserByToken(token);
        if (user.getAvatar() != null) {
            Photo photo = user.getAvatar();
            user.setAvatar(null);
            photoRepository.delete(photo);
        }
        Photo photo = getPhotoFromFile(file);
        photo = photoRepository.save(photo);
        user.setAvatar(photo);
        userRepository.save(user);
        return photo.getIdPhoto();
    }

    @Override
    public Resource getPhoto(Long idPhoto) {
        return new ByteArrayResource(photoRepository.findById(idPhoto).get().getPhoto());
    }

    @Override
    public void deletePhoto(String token, Long idPhoto) {
        User user = tokenService.getUserByToken(token);
        Photo photo = photoRepository.findById(idPhoto).get();
        user.getPhotos().remove(photo);
        photoRepository.delete(photo);
    }

    private Photo getPhotoFromFile(MultipartFile file) {
        Photo photo = new Photo();

        byte[] picInBytes = new byte[0];
        try {
            picInBytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        photo.setPhoto(picInBytes);
        return photo;
    }
}
