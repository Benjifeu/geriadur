package com.example.geriadur.dto;

import com.example.geriadur.constants.GenderEnum;
import com.example.geriadur.constants.LanguageEnum;
import com.example.geriadur.constants.WordClassEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CreateWordStem {

    private String wordStemName;

    private LanguageEnum wordStemLanguage;

    private String phonetic;

    private GenderEnum gender;

    private WordClassEnum wordClass;

    private String referenceWordsEng;

    private String referenceWordsFr;

    private String descrFr;

    private String descrEng;

    private List<String> quotes;

    private List<String> sources;

    private Set<String> parentsWordStemStr;

}
