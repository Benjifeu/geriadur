package com.example.geriadur.service.user;

import com.example.geriadur.dto.UserRegistrationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void save(UserRegistrationDto registrationDto);
    ResponseEntity saveScore(int sessionScore);


}
