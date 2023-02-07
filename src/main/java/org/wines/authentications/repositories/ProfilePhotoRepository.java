package org.wines.authentications.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wines.authentications.models.ProfilePhoto;

@Repository
public interface ProfilePhotoRepository extends JpaRepository<ProfilePhoto,Long> {
}