package org.wines.authentications.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.wines.authentications.dao.TelephoneDao;
import org.wines.authentications.models.Telephone;
import org.wines.authentications.models.User;
import org.wines.authentications.repositories.TelephoneRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TelephoneService {

    private final TelephoneRepository telephoneRepository;
    private final UserService userService;

   public boolean addTelephoneNumber(TelephoneDao telephoneDao) {
        ModelMapper mapper = new ModelMapper();
        Telephone telephone = mapper.map(telephoneDao,Telephone.class);
        try{
            Optional<User> user = userService.getUserByEmail(userService.getEmail());
            if(user.isPresent()) {
                telephone.setEmail(user.get());
                telephoneRepository.save(telephone);
                return true;
            }
            return true;
        } catch (Exception exception) {
            log.info(exception.getMessage());
            return false;
        }
    }

    public List<TelephoneDao> getAllTelephoneNumbers() {
        ModelMapper mapper = new ModelMapper();
        List<Telephone> allTelephoneNumbers = telephoneRepository.findAll();
        return mapper.map(allTelephoneNumbers, new TypeToken<List<TelephoneDao>>(){}.getType());
    }
}
