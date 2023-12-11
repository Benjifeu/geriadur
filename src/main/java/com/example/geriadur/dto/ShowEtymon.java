package com.example.geriadur.dto;

import com.example.geriadur.constants.GenderEnum;
import com.example.geriadur.constants.LanguageEnum;
import com.example.geriadur.constants.WordClassEnum;
import com.example.geriadur.domain.ReferenceWord;
import com.example.geriadur.domain.SemanticField;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Getter
@Setter
//entry for the registation of a new etymon in the DB
public class ShowEtymon {

    private Long etymonId;

    private String etymonName;

    private LanguageEnum etymonLanguage;

    private String phonetic;

    private GenderEnum gender;

    private WordClassEnum wordClass;

    private List<String> semanticFields = new ArrayList<>();

    private String referenceWords;


}
