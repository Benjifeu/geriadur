package com.example.geriadur.dto;

import com.example.geriadur.entity.LiteralTranslation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateProperNoun {


    private String currentName;

    private String etymoName;

    private List<String> wordStems;

    private String descrFr;

    private String descrEng;

    private Long wordTheme;

    private Long semanticField;

    private LiteralTranslation litTrans;

}
