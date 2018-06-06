package com.tim.tinder.services;

import com.tim.tinder.services.interfaces.PhotoService;
import org.springframework.stereotype.Service;

@Service
public class PhotoServiceImpl implements PhotoService {
    @Override
    public void addPhoto(Long userId, byte[] photo) {

    }

    @Override
    public void changeAvatar(Long userId, byte[] avatar) {

    }

    @Override
    public byte[] getPhoto(Long photoId) {
        return new byte[0];
    }
}
