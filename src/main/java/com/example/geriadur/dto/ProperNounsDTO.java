package com.example.geriadur.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProperNounsDTO {

    private String currentName;

    private String etymoName;

    private List<String> wordStemsPC;

    private String descrFr;

    private String descrEng;

    private String shortDescrFr;
    
    private String shortDescrEng;

    private Long wordTheme;

    private String litTransFr;
    
    private String litTransEng;
    private int litTransType;
    private String place;

    private String country;

    private String period;
    private int year;
}
