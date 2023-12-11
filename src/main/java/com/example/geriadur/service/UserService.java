package com.example.geriadur.service;

import com.example.geriadur.domain.User;
import com.example.geriadur.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public User save(UserRegistrationDto registrationDto);

}
