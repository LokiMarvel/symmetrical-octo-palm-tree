package org.wines.authentications.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wines.authentications.dao.TelephoneDao;
import org.wines.authentications.service.TelephoneService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/telephone")
@RequiredArgsConstructor
public class TelephoneController {

    private final TelephoneService telephoneService;

    @SneakyThrows
    @PostMapping
    public ResponseEntity<?> addTelephone(@RequestBody String telephone_data) {
        TelephoneDao telephoneDao = new ObjectMapper().readValue(telephone_data, TelephoneDao.class);
        boolean status = telephoneService.addTelephoneNumber(telephoneDao);
        if(status)
            return ResponseEntity.ok(HttpStatus.OK);
        return ResponseEntity.ok(false);
    }
    
    @GetMapping
    public ResponseEntity<List<TelephoneDao>> getAllTelephoneNumbers() {
        return ResponseEntity.ok(telephoneService.getAllTelephoneNumbers());
    } 
    
}
