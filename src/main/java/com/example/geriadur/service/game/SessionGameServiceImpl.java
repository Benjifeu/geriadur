package com.example.geriadur.service.game;

import com.example.geriadur.domain.EtymonName;
import com.example.geriadur.dto.LiteralTranslation;
import com.example.geriadur.domain.SemanticField;
import com.example.geriadur.repositories.EtymonNameRepository;
import com.example.geriadur.repositories.SemanticFieldRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
public class SessionGameServiceImpl implements SessionGameService {

    @Autowired
    private Set<LiteralTranslation> getAllLiteralTranslationBySemField;
    @Autowired
    private List<EtymonName> get15RandomEtymonName;
    @Autowired
    private int getScore;

    public boolean verifyResponse(int step, LiteralTranslation response) {
        if (Objects.equals(response.getResponse(), get15RandomEtymonName.get(step).getResponseFr())) {
            getScore += 1;
            return true;
        }
        return false;
    }
    public int getScore() {
        return getScore;
    }

    public List<EtymonName> get15RandomEtymonName() {
        return get15RandomEtymonName;
    }

    public Set<LiteralTranslation> get5responseChoices(int gameStep) {
        String response = get15RandomEtymonName.get(gameStep).getResponseFr();
        Set<LiteralTranslation> selectedLitTrans = new HashSet<>();
        LiteralTranslation goodResponse = new LiteralTranslation();
        goodResponse.setResponse(response);
        selectedLitTrans.add(goodResponse);
        for (int i = 0; i < 4; i++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(getAllLiteralTranslationBySemField.size());
            int it = 0;
            for (LiteralTranslation litTrans : getAllLiteralTranslationBySemField) {
                if (it == randomIndex) {
                    if (!Objects.equals(litTrans.getResponse(), response)) {
                        selectedLitTrans.add(litTrans);
                        break;
                    }
                    it++;
                }
            }

        }
        return selectedLitTrans;
    }


}


