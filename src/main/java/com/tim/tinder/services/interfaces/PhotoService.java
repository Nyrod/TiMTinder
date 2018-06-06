package com.tim.tinder.services.interfaces;

public interface PhotoService {

    void addPhoto(Long userId, byte[] photo);
    void changeAvatar(Long userId, byte[] avatar);
    byte[] getPhoto(Long photoId);
}
