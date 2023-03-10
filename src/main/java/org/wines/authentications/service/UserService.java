package org.wines.authentications.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.wines.authentications.dao.UserDao;
import org.wines.authentications.jwt.JWT;
import org.wines.authentications.models.Authority;
import org.wines.authentications.models.Role;
import org.wines.authentications.models.User;
import org.wines.authentications.repositories.AuthorityRepository;
import org.wines.authentications.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWT jwt;
    private final AuthenticationManager authenticationManager;

    public String createUser(UserDao userDao) {
        ModelMapper mapper = new ModelMapper();
        User user = mapper.map(userDao,User.class);
        user.setPassword(encodePassword(user.getPassword()));
        //TODO need to delete because these values are default in database
        user.setEnabled(true);
        user.setAccountExpired(true);// TODO need to change from false to true in place of default database
        user.setCredentialsExpired(true);//TODO need to change from false to true in place of default database
        user.setAccountLocked(true);// TODO need to change from false to true in place of default database
//        user.setAuthorities(List.of(Authority.builder().email(user).users_role("ROLE_USER").build()));
        //TODO upto here
        try {
            // need to check for userEmail and userName before creation
            //TODO
            Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
            if(byEmail.isPresent()) {
                return "User has already registered with the given mail address.";
            } else {
                userRepository.save(user);
                authorityRepository.save(Authority.builder().email(user).users_role("ROLE_USER").build());
                return getJwtToken(userDao);
            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return exception.toString();
        }
    }

    public String getJwtToken(UserDao userDao) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDao.getEmail(),userDao.getPassword()));
        User user = userRepository.findByEmail(userDao.getEmail()).orElseThrow(() -> new RuntimeException("Error TODO"));
        return jwt.generateToken(user);
    }

    public List<UserDao> getAllUsers() {
        var users = userRepository.findAll();
        ModelMapper mapper = new ModelMapper();
        return mapper.map(users,new TypeToken<List<UserDao>>(){}.getType());
    }

    public Optional<User> getUserByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail);
    }

    public String getEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.toString();
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
