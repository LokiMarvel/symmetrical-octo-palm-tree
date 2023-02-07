package org.wines.authentications.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wines.authentications.models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
}
