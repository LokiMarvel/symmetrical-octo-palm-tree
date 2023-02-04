package org.wines.authentications.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.wines.authentications.dao.UserDao;
import org.wines.authentications.service.UserService;

@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @SneakyThrows
    public ResponseEntity<?> createUser(@RequestBody String userDetails) {
        UserDao userDao = new ObjectMapper().readValue(userDetails,UserDao.class);
        String status = userService.createUser(userDao);
        return ResponseEntity.ok(status);
    }
    @PostMapping(path = "/token")
    public ResponseEntity<?> getJwtToken(@RequestBody String userDetails) throws JsonProcessingException {
        UserDao userDao = new ObjectMapper().readValue(userDetails,UserDao.class);
        return ResponseEntity.ok(userService.getJwtToken(userDao));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_TEST','ROLE_GUEST','ROLE_ADMIN')")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
