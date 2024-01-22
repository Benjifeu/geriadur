package com.example.geriadur.dto;

import lombok.AllArgsConstructor;

import java.util.List;
@AllArgsConstructor
public class GameSessionStep {
    List<ResponseChoice> proposedLiteralTranslationList;
    ProperName properName;
    List<PCelticRadical> pCelticRadicals;
}
