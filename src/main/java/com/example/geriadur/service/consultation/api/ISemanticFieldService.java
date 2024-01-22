package com.example.geriadur.service.consultation.api;

import com.example.geriadur.domain.SemanticField;
import com.example.geriadur.domain.consultation.WordStem;

import java.util.List;
import java.util.Set;

public interface ISemanticFieldService {

    List<SemanticField> getAllSemanticField();
    void saveSematicField(SemanticField semanticField);
    SemanticField getSemanticFieldById(Long id);
    Set<WordStem> getListOfEtymonsBySemField(Long id);

}
