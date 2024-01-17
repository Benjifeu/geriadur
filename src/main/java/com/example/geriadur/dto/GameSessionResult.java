package com.example.geriadur.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class GameSessionResult {
    int sessionScore;
    List<String> falseResponses;
}
