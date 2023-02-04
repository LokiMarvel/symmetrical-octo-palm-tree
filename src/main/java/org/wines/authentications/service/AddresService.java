package org.wines.authentications.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.wines.authentications.dao.AddressDao;
import org.wines.authentications.models.Address;
import org.wines.authentications.repositories.AddressRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AddresService {
    private final AddressRepository addressRepository;
    private final UserService userService;

    public boolean addAddres(AddressDao addressDao) {
        var mapper = new ModelMapper();
        var address = mapper.map(addressDao,Address.class);
        try {
            var user = userService.getUserByEmail(userService.getEmail());
            user.ifPresent(address::setEmail);
            addressRepository.save(address);
            return true;
        } catch (Exception exception) {
            log.error(exception.getLocalizedMessage());
            return false;
        }
    }

    public List<AddressDao> getAddress() {
        var mapper = new ModelMapper();
        return mapper.map(addressRepository.findAll(),new TypeToken<List<AddressDao>>(){}.getType());
    }
}
