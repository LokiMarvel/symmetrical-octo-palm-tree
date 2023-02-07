package org.wines.authentications.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.wines.authentications.dao.ProfilePhotoDao;
import org.wines.authentications.service.ProfilePhotoService;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping(path = "/photo")
public class PhotoController {

    private final ProfilePhotoService photoService;

    @GetMapping
    public ResponseEntity<?> getPhoto() {
        return ResponseEntity.ok(photoService.getPhoto());
    }

    @SneakyThrows
    @PostMapping
    public ResponseEntity<?> addPhoto(@RequestParam(name = "file" ,required = true) MultipartFile multipartFile) {
        ProfilePhotoDao profilePhotoDao = ProfilePhotoDao.builder().profileImage(multipartFile.getBytes()).build();
        return ResponseEntity.ok(photoService.addPhoto(profilePhotoDao));
    }
}