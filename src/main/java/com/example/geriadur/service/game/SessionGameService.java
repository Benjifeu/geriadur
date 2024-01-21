package com.example.geriadur.service.game;

import com.example.geriadur.domain.EtymonName;
import com.example.geriadur.domain.LiteralTranslation;
import com.example.geriadur.dto.*;
import com.example.geriadur.repositories.EtymonNameRepository;
import com.example.geriadur.repositories.LiteralTranslationRepository;
import com.example.geriadur.service.game.api.ISessionGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

    /** the ISessionGameService class is responsible for the traitment and the retrieving of data needed data in order to start the game session
    based on a 15 step where each show a proper name and a selection a 5 literal translantion among which lies the good one*/
@Service
public class SessionGameService implements ISessionGameService {


    @Autowired
    private EtymonNameRepository etymonNameRepository;
    @Autowired
    private LiteralTranslationRepository literalTranslationRepository;

    /** get15GameSessionStep() returns a random list of 15 questions with an question (EtymonName) and 5 choices of responses (litteral translations)*/
    public List<GameSessionStep> get15GameSessionStep(int wordTheme) {
        List<EtymonName> etymonNames = get15RandomEtymonName(wordTheme);
        List<GameSessionStep> gameSessionSteps = new ArrayList<>();
        for (EtymonName etymonName : etymonNames) {
            List<PCelticRadical> radicals = new ArrayList<>();
            for (int i = 0; i < etymonName.getWordStemPc().size(); i++) {

                radicals.add(
                        new PCelticRadical(etymonName.getWordStemPc().get(i).getWordStemName(),
                                etymonName.getWordStemPc().get(i).getReferenceWordsFr()));
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

    /** get15EtymonName() returns a random list of 15 EtymonName from the DB according to the theme of words define by the user*/
    public List<EtymonName> get15RandomEtymonName(int wordTheme) {
        Set<EtymonName> etymonNames = etymonNameRepository.find15EtymonNamesByWordTheme(wordTheme);
        List<EtymonName> etymonNameList = new ArrayList<>(etymonNames);
        return etymonNameList;
    }

    /** get5responseChoices() returns a random list of 5 choices of literal translation for a specific EtymonName, the one in the arguments is the good response which is */
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


