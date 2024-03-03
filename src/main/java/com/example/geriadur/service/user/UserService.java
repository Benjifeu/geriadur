package com.example.geriadur.service.user;

import com.example.geriadur.constants.UserRoleEnum;
import com.example.geriadur.dto.CreateWordStem;
import com.example.geriadur.dto.ShowUser;
import com.example.geriadur.entity.consultation.Author;
import com.example.geriadur.entity.user.UserAccount;
import com.example.geriadur.dto.CreateUser;
import com.example.geriadur.repositories.UserRepository;
import com.example.geriadur.service.user.api.IUserService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


/**
 * The UserService, .
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
/*
    public UserService(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }*/

    @Override
    public void save(CreateUser registrationDto) {
        String role;
        switch (registrationDto.getRole()) {
            case ("U"):
                role = UserRoleEnum.ROLE_USER.toString();
                break;
            case ("A"):
                role = UserRoleEnum.ROLE_USER + "," + UserRoleEnum.ROLE_ADMIN;
                break;
            default:
                throw new IllegalArgumentException("Please choose an existing role");
        }
        UserAccount userAccount = new UserAccount(
                registrationDto.getFirstName(),
                registrationDto.getLastName(),
                registrationDto.getEmail(),
                registrationDto.getLanguage(),
                BCrypt.hashpw(registrationDto.getPassword(), BCrypt.gensalt()),
                role,
                new Date()
        );
        userRepository.save(userAccount);
    }

    public String getCurrentUserEmail() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            return authentication.getName();
        }
        return null;
    }

    @Override
    public ShowUser getUserById(Long id) {
       UserAccount userAccount= userRepository.findById(id).get();
       ShowUser showUser = new ShowUser();
       showUser.setUserName(userAccount.getFirstName());
       showUser.setMonthAndYearJoined(userAccount.getRegistrationDate().toString());
       showUser.setScoreByTheme(Arrays.asList(userAccount.getScorePlaces(),userAccount.getScoreHfigures(),userAccount.getScoreMfigures(),userAccount.getScorePlaces(),userAccount.getScoreObjects()));
        return showUser;
    }

    public ResponseEntity<String> saveScore(int sessionScore, int sessionTheme) {
        UserAccount userAccount =
                userRepository.findByEmail(getCurrentUserEmail()).get();
        int finalScore;
        switch (sessionTheme) {
            case 1:
                finalScore = userAccount.getScorePlaces() + sessionScore;
                userAccount.setScorePlaces(finalScore);
                break;
            case 2:
                finalScore = userAccount.getScoreHfigures() + sessionScore;
                userAccount.setScoreHfigures(finalScore);
                break;
            case 3:
                finalScore = userAccount.getScoreMfigures() + sessionScore;
                userAccount.setScoreMfigures(finalScore);
                break;
            case 4:
                finalScore = userAccount.getScoreTribes() + sessionScore;
                userAccount.setScoreTribes(finalScore);
                break;
            case 5:
                finalScore = userAccount.getScoreObjects() + sessionScore;
                userAccount.setScoreObjects(finalScore);
                break;
        }
        userRepository.save(userAccount);
        return ResponseEntity.ok().body("Score of the user " + userAccount.getFirstName() + " has been updated");
    }

    public UserAccount getAccountInfo() {
        return userRepository.findByEmail(
                        getCurrentUserEmail()
                )
                .get();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (email == null) {
            throw new UsernameNotFoundException("Please enter an email");
        }
        Optional<UserAccount> opUser = userRepository.findByEmail(email);
        if (opUser.isPresent()) {
            UserAccount userAccount = opUser.get();
            GrantedAuthority authority = new SimpleGrantedAuthority(userAccount.getRole());
            return new org.springframework.security.core.userdetails.User(userAccount.getEmail(), userAccount.getPassword(), Arrays.asList(authority));
        } else throw new RuntimeException("Their is no account with the email:" + email);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(List<String> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
    }

}
