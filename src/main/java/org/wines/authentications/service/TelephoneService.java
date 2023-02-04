package org.wines.authentications.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.wines.authentications.dao.TelephoneDao;
import org.wines.authentications.models.Telephone;
import org.wines.authentications.repositories.TelephoneRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class TelephoneService {

    private final TelephoneRepository telephoneRepository;
    private final UserService userService;

   /* public boolean addTelephoneNumber(TelephoneDao telephoneDao) {
        ModelMapper mapper = new ModelMapper();
        Telephone telephone = mapper.map(telephoneDao,Telephone.class);
        try{
            telephone.setEmail(userService.getUserEmail());
        }catch (Exception exception) {

        }
    }*/
}
