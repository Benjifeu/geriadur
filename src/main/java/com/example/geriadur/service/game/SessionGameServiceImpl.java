package com.example.geriadur.service.game;

import com.example.geriadur.domain.EtymonName;
import com.example.geriadur.dto.LiteralTranslation;
import com.example.geriadur.domain.SemanticField;
import com.example.geriadur.repositories.EtymonNameRepository;
import com.example.geriadur.repositories.SemanticFieldRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
public class SessionGameServiceImpl implements SessionGameService {

    @Autowired
    private EtymonNameRepository etymonNameRepository;
    @Autowired
    private SemanticFieldRepository semanticFieldRepository;

    private Set<LiteralTranslation> sessionLiteralTranslations;
    private List<EtymonName> sessionEtymonNames;

    private int step;
    private int score;

    public SessionGameServiceImpl() {
        this.sessionEtymonNames = get15RandomEtymonName(1);
        this.sessionLiteralTranslations = getAllLiteralTranslationBySemField(1, 1);
    }


    @Override
    public EtymonName getNextEtymonName() {
        return sessionEtymonNames.get(step);
    }

    @Override
    public Set<LiteralTranslation> get4LiteralTranslationWithoutSpecified(LiteralTranslation currentLitTrans) {
        Set<LiteralTranslation> selectedLitTrans = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(sessionLiteralTranslations.size());
            int it = 0;
            for (LiteralTranslation litTrans : sessionLiteralTranslations) {
                if (it == randomIndex) {
                    if (!Objects.equals(litTrans.getResponse(), currentLitTrans.getResponse())) {
                        selectedLitTrans.add(litTrans);
                        break;
                    }
                    it++;
                }
            }
        }
        return selectedLitTrans;
    }


    @Override
    public Set<LiteralTranslation> getAllLiteralTranslationBySemField(long semanticFieldId, int language) {
        Set<EtymonName> etymonNames = getAllEtymon(semanticFieldId);
        Set<LiteralTranslation> literalTranslations = new HashSet<>();
        for (EtymonName etymonName : etymonNames)
            switch (language) {
                case 1:
                    literalTranslations.add(new LiteralTranslation(etymonName.getResponseFr()));
                case 2:
                    literalTranslations.add(new LiteralTranslation(etymonName.getResponseEng()));
            }
        return literalTranslations;
    }

    @Override
    public List<EtymonName> get15RandomEtymonName(long semanticFieldId) {
        Set<EtymonName> etymonNames = getAllEtymon(semanticFieldId);
        for (EtymonName element : etymonNames)
            if (etymonNames.size() > 15)
                etymonNames.remove(element);
        List<EtymonName> etymonNameList = new ArrayList<>(etymonNames);
        return etymonNameList;
    }

    public Set<EtymonName> getAllEtymon(long semanticFieldId) {
        Optional<SemanticField> semanticField = semanticFieldRepository.findById(semanticFieldId);
        if (semanticField.isPresent()) {
            return etymonNameRepository.findEtymonNamesBySemanticField(semanticField.get());
        } else throw new RuntimeException("Their is no semantic field with the id:" + semanticFieldId);
    }

}


