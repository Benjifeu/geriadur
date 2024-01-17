package com.example.geriadur.dto;

import com.example.geriadur.domain.EtymonName;
import com.example.geriadur.domain.LiteralTranslation;
import lombok.AllArgsConstructor;

import java.util.List;
@AllArgsConstructor
public class GameSessionStep {
    List<ResponseChoice> proposedLiteralTranslationList;
    ProperName properName;
    List<PCelticRadical> pCelticRadicals;
}
