package com.tim.tinder.services.interfaces;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {

    Long addPhotoToUser(MultipartFile file);

    Long changeUserAvatar(MultipartFile file);

    Resource getPhoto(Long idPhoto);

    void deletePhoto(Long idPhoto);
}
