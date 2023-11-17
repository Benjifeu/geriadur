package com.example.geriadur.service;

import com.example.geriadur.domain.Etymon;
import com.example.geriadur.domain.SemanticField;

import java.util.List;

public interface SemanticFieldService {

    List<SemanticField> getAllSemanticField();
    void saveSematicField(SemanticField semanticField);
    SemanticField getSemanticFieldById(Long id);
    List<Etymon> getListOfEtymonsById(Long id);

}
