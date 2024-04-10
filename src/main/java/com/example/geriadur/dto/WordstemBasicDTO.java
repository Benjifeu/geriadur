package com.example.geriadur.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WordstemBasicDTO {

    private String wordStemName;

    private String wordStemLanguage;

    private String phonetic;

    private String gender;

    private String wordClass;

    private String engTranslation;

    private String frTranslation;

    private String semanticField;

}
