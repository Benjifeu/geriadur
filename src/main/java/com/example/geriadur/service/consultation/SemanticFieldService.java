package com.example.geriadur.service.consultation;

import com.example.geriadur.domain.EtymonName;
import com.example.geriadur.domain.SemanticField;

import java.util.List;
import java.util.Set;

public interface SemanticFieldService {

    List<SemanticField> getAllSemanticField();
    void saveSematicField(SemanticField semanticField);
    SemanticField getSemanticFieldById(Long id);
    Set<EtymonName> getListOfEtymonsById(Long id);

}
