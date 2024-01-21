package com.example.geriadur.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CreateAuthor {
    String authorName;
    String biography;
    LocalDate dateOfBirth;
    LocalDate dateOfDeath;
    String nationality;

}
