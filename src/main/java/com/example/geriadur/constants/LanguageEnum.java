package com.example.geriadur.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum LanguageEnum {
    LB("Br.", "Breton"),
    LOB("Br.", "Breton"),
    LBQ("Bri.", "Brittonique"),
    LC("Corn.", "Cornique"),
    LE("Eng.", "Anglais"),
    LF("Fr.", "Français"),
    LG("Gaul.", "Gaulois"),
    LGER("Ger.", "Germanique"),
    LIE("P.I.E.", "Proto-indo-européen"),
    LI("Ga.", "Irlandais"),
    LOI("Ga.", "vieil Irlandais"),
    LS("S.Ga.", "Gaélique écossais"),
    LPC("Pr.Cel.", "Proto-celte"),
    LW("Cy.", "Gallois"),
    LLT("La.", "Latin");
    private String eng;
    private String fr;

    LanguageEnum(String eng, String fr) {
        this.eng = eng;
        this.fr = fr;
    }


    public List<String> getAllEng() {
        List<String> languages = new ArrayList<>();
        for (LanguageEnum lang : LanguageEnum.values()) {
            languages.add(lang.eng);
        }
        return languages;
    }

    public String getFr() {
        return fr;
    }
}
