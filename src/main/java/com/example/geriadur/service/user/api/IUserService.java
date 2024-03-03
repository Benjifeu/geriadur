package com.example.geriadur.service.user.api;

import com.example.geriadur.dto.CreateUser;
import com.example.geriadur.dto.ShowUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {
    void save(CreateUser registrationDto);

    ResponseEntity<String> saveScore(int sessionScore, int sessionTheme);

    String getCurrentUserEmail();

    ShowUser getUserById(Long id);
}
