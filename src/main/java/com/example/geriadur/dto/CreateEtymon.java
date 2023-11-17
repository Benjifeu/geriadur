package com.example.geriadur.dto;

import java.util.Set;

//entry for the registation of a new etymon in the DB
public class CreateEtymon {
    String phonetic;
    char gender;
    String description;
    String wordClass;
    String semanticLanguage;


    Set<String> englishWords;
    Set<String> frenchWords;
    String breton;
    String interdialectalBreton;
    String welsh;
    String cornish;
    String irish;
    String gaulish;
    String protoCeltic;
    String indoEuropean;


}
