package com.tim.tinder.services;

import com.tim.tinder.TinderException;
import com.tim.tinder.entities.Photo;
import com.tim.tinder.entities.User;
import com.tim.tinder.repositories.PhotoRepository;
import com.tim.tinder.repositories.UserRepository;
import com.tim.tinder.services.interfaces.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addPhotoToUser(Long idUser, byte[] photo) {
        User user = null;
        try {
           user = userRepository.findById(idUser).orElseThrow(() -> new TinderException("Nie znaleziono użytkownika o id " + idUser));
        } catch (TinderException e) {
            e.printStackTrace();
        }
        user.getPhotos().add(new Photo(photo));
    }

    @Override
    public void changeUserAvatar(Long idUser, byte[] avatar) {

    }

    @Override
    public byte[] getPhoto(Long idPhoto) {
        return new byte[0];
    }

    @Override
    public void deletePhoto(long idPhoto) {

    }
}
