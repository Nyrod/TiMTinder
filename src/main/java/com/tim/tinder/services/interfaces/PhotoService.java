package com.tim.tinder.services.interfaces;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {

    Long addPhotoToUser(String token, MultipartFile file);

    Long changeUserAvatar(String token, MultipartFile file);

    Resource getPhoto(Long idPhoto);

    void deletePhoto(String token, Long idPhoto);
}
