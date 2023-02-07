package org.wines.authentications.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wines.authentications.models.Telephone;

@Repository
public interface TelephoneRepository extends JpaRepository<Telephone,Long> {
}
