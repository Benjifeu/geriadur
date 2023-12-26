package com.example.geriadur.service.game;

import com.example.geriadur.domain.EtymonName;
import com.example.geriadur.domain.LiteralTranslation;
import com.example.geriadur.dto.GameSessionStep;
import com.example.geriadur.repositories.EtymonNameRepository;
import com.example.geriadur.repositories.LiteralTranslationRepository;
import com.example.geriadur.repositories.SemanticFieldRepository;
import com.example.geriadur.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
public class SessionGameServiceImpl implements SessionGameService {


    @Autowired
    private EtymonNameRepository etymonNameRepository;
    @Autowired
    private LiteralTranslationRepository literalTranslationRepository;

    public List<GameSessionStep> get15GameSessionStep(long semanticFieldId) {
        //if (semanticField.isPresent()) {
        List<EtymonName> etymonNames = get15RandomEtymonName(semanticFieldId);
        List<GameSessionStep> gameSessionSteps = new ArrayList<>();
        for (EtymonName etymonName : etymonNames)
            gameSessionSteps.add(new GameSessionStep(get5responseChoices(etymonName.getLitTrans()), etymonName.getEtymonName()));
        return gameSessionSteps;
        //} else throw new RuntimeException("Their is no semantic field with the id:" + semanticFieldId);
    }

    public List<EtymonName> get15RandomEtymonName(@Value("1") long semanticFieldId) {
        Set<EtymonName> etymonNames = etymonNameRepository.findEtymonNamesBySemanticField(semanticFieldId);
        for (EtymonName element : etymonNames)
            if (etymonNames.size() > 15)
                etymonNames.remove(element);
        List<EtymonName> etymonNameList = new ArrayList<>(etymonNames);
        return etymonNameList;
    }

    public List<LiteralTranslation> getAllLiteralTranslationByType(int type) {
        return literalTranslationRepository.findAllByLitTransType(type);
    }



    public List<String> get5responseChoices(LiteralTranslation goodLitTrans) {
        List<String> selectedLitTrans = new ArrayList<>();
        selectedLitTrans.add(goodLitTrans.getLitTransFr());
        List<LiteralTranslation> allLiteralTranslations = literalTranslationRepository.findAllByLitTransType(goodLitTrans.getLitTransType());
        for (int i = 0; i < 4; i++) {
            selectedLitTrans.add(allLiteralTranslations.get(i).getLitTransFr());
            /*int randomIndex = ThreadLocalRandom.current().nextInt(allLiteralTranslations.size());
            int it = 0;
            for (LiteralTranslation litTrans : allLiteralTranslations) {
                if (it == randomIndex) {
                    if (litTrans.getLitTransId() != goodLitTrans.getLitTransId()) {
                        selectedLitTrans.add(litTrans.getLitTransFr());
                        break;
                    }
                    it++;
                }
            }*/

        }
        return selectedLitTrans;
    }
    /*
        public boolean verifyResponse(LiteralTranslation response) {
            if (Objects.equals(response.getResponse(), get15RandomEtymonName.get(response.getStep()).getResponseFr())) {
                getScore++;
                return true;
            }
            return false;
        }

            public EtymonName getNextEtymonName() {
        return get15RandomEtymonName.get(getStep);
    }
    */

}


