package com.tim.tinder.services;

import com.tim.tinder.entities.Photo;
import com.tim.tinder.repositories.PhotoRepository;
import com.tim.tinder.repositories.UserRepository;
import com.tim.tinder.services.interfaces.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final UserRepository userRepository;

    private final PhotoRepository photoRepository;

    @Autowired
    public PhotoServiceImpl(UserRepository userRepository, PhotoRepository photoRepository) {
        this.userRepository = userRepository;
        this.photoRepository = photoRepository;
    }


    @Override
    public void addPhotoToUser(MultipartFile file) {
//        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = userRepository.findByLogin(userDetails.getUsername());
//        Photo photo = getPhotoFromFile(file);
//        photoRepository.save(photo);
//        user.getPhotos().add(photo);
//        userRepository.save(user);
    }

    @Override
    public void changeUserAvatar( MultipartFile file) {
//        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = userRepository.findByLogin(userDetails.getUsername());
//        Photo photo = getPhotoFromFile(file);
//        photoRepository.save(photo);
//        user.setAvatar(photo);
//        userRepository.save(user);
    }

    @Override
    public byte[] getPhoto(Long idPhoto) {
        return new byte[0];
    }

    @Override
    public void deletePhoto(long idPhoto) {

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
