package com.tim.tinder.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {

    void addPhotoToUser(MultipartFile file);

    void changeUserAvatar(MultipartFile file);

    byte[] getPhoto(Long idPhoto);

    void deletePhoto(long idPhoto);
}
