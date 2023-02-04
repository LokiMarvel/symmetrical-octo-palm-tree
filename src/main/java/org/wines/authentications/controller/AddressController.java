package org.wines.authentications.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wines.authentications.dao.AddressDao;
import org.wines.authentications.service.AddresService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path="address")
@RequiredArgsConstructor
public class AddressController {
    private final AddresService addresService;

    @SneakyThrows
    @PostMapping
    public ResponseEntity<?> addAddress(@RequestBody String address) {
        var addressDao = new ObjectMapper().readValue(address, AddressDao.class);
        boolean status = addresService.addAddres(addressDao);
        if (status)
            return ResponseEntity.ok(HttpStatus.OK);
        return ResponseEntity.ok(HttpStatus.PARTIAL_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> getAllAddress() {
        return ResponseEntity.ok(addresService.getAddress());
    }

}
