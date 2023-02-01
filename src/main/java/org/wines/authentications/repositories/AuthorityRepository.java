package org.wines.authentications.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wines.authentications.models.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,String> {
}
