package com.example.geriadur.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WordstemFullDTO {

    private String wordStemName;

    private String wordStemLanguage;

    private String phonetic;

    private String gender;

    private String wordClass;
    private List<String> sources;

    private String engTranslation;

    private String frTranslation;

    private String descrFr;

    private String semanticField;

    private List<ProperNounsDTO> properNouns;
    private List<String> parents;

}
