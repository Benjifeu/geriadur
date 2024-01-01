package com.example.geriadur.service.game;

import com.example.geriadur.domain.EtymonName;
import com.example.geriadur.domain.LiteralTranslation;
import com.example.geriadur.domain.consultation.Lexeme;
import com.example.geriadur.dto.GameSessionStep;
import com.example.geriadur.dto.PCelticRadical;
import com.example.geriadur.dto.ProperName;
import com.example.geriadur.dto.ResponseChoice;
import com.example.geriadur.repositories.EtymonNameRepository;
import com.example.geriadur.repositories.LiteralTranslationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class SessionGameServiceImpl implements SessionGameService {


    @Autowired
    private EtymonNameRepository etymonNameRepository;
    @Autowired
    private LiteralTranslationRepository literalTranslationRepository;

    public List<GameSessionStep> get15GameSessionStep(int wordTheme) {
        List<EtymonName> etymonNames = get15RandomEtymonName(wordTheme);
        List<GameSessionStep> gameSessionSteps = new ArrayList<>();
        for (EtymonName etymonName : etymonNames) {
            List<PCelticRadical> radicals = new ArrayList<>();
            for (int i = 0; i < etymonName.getLexemePc().size(); i++) {

                radicals.add(
                        new PCelticRadical(etymonName.getLexemePc().get(i).getLexemeName(),
                                etymonName.getLexemePc().get(i).getReferenceWordsFr()));
            }
            gameSessionSteps.add(new GameSessionStep(get5responseChoices(
                    etymonName.getLitTrans()),
                    new ProperName(
                            etymonName.getCurrentName(),
                            etymonName.getEtymoName(),
                            etymonName.getDescrFr())
                    , radicals));
        }
        return gameSessionSteps;
    }

    public List<EtymonName> get15RandomEtymonName(int wordTheme) {
        Set<EtymonName> etymonNames = etymonNameRepository.findEtymonNamesByWordTheme(wordTheme);
        List<EtymonName> etymonNameList = new ArrayList<>(etymonNames);
        return etymonNameList;
    }

    public List<ResponseChoice> get5responseChoices(LiteralTranslation goodLitTrans) {
        List<ResponseChoice> selectedLitTrans = new ArrayList<>();
        selectedLitTrans.add(new ResponseChoice(goodLitTrans.getLitTransFr(), true));
        List<LiteralTranslation> allLiteralTranslations = literalTranslationRepository.findAllByLitTransType(goodLitTrans.getLitTransType());
        for (LiteralTranslation literalTrans : allLiteralTranslations
        ) {
            if (!literalTrans.getLitTransFr().equals(goodLitTrans.getLitTransFr())) {
                selectedLitTrans.add(new ResponseChoice(literalTrans.getLitTransFr(), false));
            }
            if (selectedLitTrans.size() == 5) break;
        }
        Collections.shuffle(selectedLitTrans);
        return selectedLitTrans;
    }

}


