package com.example.geriadur.service.user;

import com.example.geriadur.constants.UserRoleEnum;
import com.example.geriadur.domain.user.User;
import com.example.geriadur.dto.UserRegistrationDto;
import com.example.geriadur.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        String role;
        switch (registrationDto.getRole()) {
            case ("U"):
                role = UserRoleEnum.ROLE_USER.toString();
                break;
            case ("A"):
                role = UserRoleEnum.ROLE_USER+","+UserRoleEnum.ROLE_ADMIN;
                break;
            default:
                throw new IllegalArgumentException("Please choose an existing role");
        }
        User user = new User(
                registrationDto.getFirstName(),
                registrationDto.getLastName(),
                registrationDto.getEmail(),
                registrationDto.getLanguage(),
                BCrypt.hashpw(registrationDto.getPassword(), BCrypt.gensalt()),
                role
        );
        return userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            throw new UsernameNotFoundException("Please enter an email");
        }
        Optional<User> opUser = userRepository.findByEmail(username);
        if (opUser.isPresent()) {
            User user = opUser.get();
            GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Arrays.asList(authority));
        } else throw new RuntimeException("Their is no account with the email:" + username);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(List<String> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
    }

}
