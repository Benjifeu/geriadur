package com.example.geriadur.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUser {
    String firstName;
    String lastName;
    String email;
    int language;
    String role;
    String password;
}
