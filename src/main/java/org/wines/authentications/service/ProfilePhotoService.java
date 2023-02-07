package org.wines.authentications.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.wines.authentications.dao.ProfilePhotoDao;
import org.wines.authentications.models.ProfilePhoto;
import org.wines.authentications.models.User;
import org.wines.authentications.repositories.ProfilePhotoRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProfilePhotoService {

    private final ProfilePhotoRepository photoRepository;
    private final UserService userService;

    public boolean addPhoto(ProfilePhotoDao profilePhotoDao) {
        ModelMapper modelMapper = new ModelMapper();
        ProfilePhoto profilePhoto = modelMapper.map(profilePhotoDao,ProfilePhoto.class);
        try {
            Optional<User> user = userService.getUserByEmail(userService.getEmail());
            user.ifPresent(profilePhoto::setEmail);
            photoRepository.save(profilePhoto);
            return true;
        } catch (Exception exception) {
            log.error(exception.getLocalizedMessage());
            return false;
        }
    }

    public ProfilePhoto getPhoto() {
        Optional<User> user = userService.getUserByEmail(userService.getEmail());
        return user.map(User::getPhotos).orElse(null);
    }
}
