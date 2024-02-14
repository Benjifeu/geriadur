package com.example.geriadur.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class GameSessionResult {

    int sessionTheme;

    int sessionScore;
    
    /**
     * return the list of proper noons that have received a bad answer
     */
    List<String> badAnswer;
}
