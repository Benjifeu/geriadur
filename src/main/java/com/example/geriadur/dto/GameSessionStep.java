package com.example.geriadur.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@Getter
@Setter
public class GameSessionStep implements IDto{
    List<ResponseChoice> proposedLiteralTranslationList;
    ProperName properName;
    List<PCelticRadical> celticRadicals;
}
