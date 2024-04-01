package com.example.geriadur.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProperNounsDTO {


    private String currentNoun;

    private String etymoNoun;

    private List<String> wordStemsPC;

    private String descrFr;

    private String descrEng;

    private Long wordTheme;

    private Long semanticField;


}
