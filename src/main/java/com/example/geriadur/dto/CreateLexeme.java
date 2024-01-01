package com.example.geriadur.dto;

import com.example.geriadur.constants.GenderEnum;
import com.example.geriadur.constants.LanguageEnum;
import com.example.geriadur.constants.WordClassEnum;
import com.example.geriadur.domain.EtymonName;
import com.example.geriadur.domain.consultation.Quote;
import com.example.geriadur.domain.consultation.Source;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CreateLexeme {

    private String lexemeName;

    private LanguageEnum lexemeLanguage;

    private String phonetic;

    private GenderEnum gender;

    private WordClassEnum wordClass;

    private String referenceWordsEng;

    private String referenceWordsFr;

    private String descrFr;

    private String descrEng;

    private List<String> quotes;

    private List<String> sources;

    private Set<CreateLexeme> parents = new HashSet<>();

    private Set<CreateLexeme> children = new HashSet<>();

}
