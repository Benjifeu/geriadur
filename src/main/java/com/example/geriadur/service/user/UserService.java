package com.example.geriadur.service.user;

import com.example.geriadur.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public void save(UserRegistrationDto registrationDto);


}
