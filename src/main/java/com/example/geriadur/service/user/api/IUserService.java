package com.example.geriadur.service.user.api;

import com.example.geriadur.dto.CreateUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {
    void save(CreateUser registrationDto);
    ResponseEntity<String> saveScore(int sessionScore);
String getCurrentUserEmail();

}
