package com.tim.tinder.services.interfaces;

public interface PhotoService {

    void addPhotoToUser(Long idUser, byte[] photo);

    void changeUserAvatar(Long idUser, byte[] avatar);

    byte[] getPhoto(Long idPhoto);

    void deletePhoto(long idPhoto);
}
