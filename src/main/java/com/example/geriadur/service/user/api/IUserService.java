package com.example.geriadur.service.user.api;

import com.example.geriadur.dto.CreateUser;
import com.example.geriadur.dto.ShowUser;
import com.example.geriadur.entity.user.UserAccount;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    void save(CreateUser registrationDto);
    void saveAll(List<CreateUser> createUsers);
    ResponseEntity<String> saveScore(int sessionScore, int sessionTheme);

    String getCurrentUserEmail();

    ShowUser getUserById(Long id);
}
