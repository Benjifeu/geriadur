package com.example.geriadur.service.game;

import com.example.geriadur.entity.ProperNoun;
import com.example.geriadur.entity.LiteralTranslation;
import com.example.geriadur.dto.*;
import com.example.geriadur.repositories.ProperNounRepository;
import com.example.geriadur.repositories.LiteralTranslationRepository;
import com.example.geriadur.service.game.api.ISessionGameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * the ISessionGameService class is responsible for the traitment and the
 * retrieving of data needed data in order to start the game session
 * based on a 15 step where each show a proper name and a selection a 5 literal
 * translantion among which lies the good one
 */
@Service
@Slf4j
public class SessionGameService implements ISessionGameService {

    @Autowired
    private ProperNounRepository properNounRepository;
    @Autowired
    private LiteralTranslationRepository literalTranslationRepository;

    /**
     * get15GameSessionStep() returns a random list of 15 questions with an question
     * (ProperNoun) and 5 choices of responses (litteral translations)
     */
    public List<GameSessionStep> get15GameSessionStep(int wordTheme) {
        List<ProperNoun> properNouns = get15RandomProperNoun(wordTheme);
        List<GameSessionStep> gameSessionSteps = new ArrayList<>();
        for (ProperNoun properNoun : properNouns) {
            List<PCelticRadical> radicals = new ArrayList<>();
            for (int i = 0; i < properNoun.getWordStemPc().size(); i++) {
                radicals.add(
                        new PCelticRadical(properNoun.getWordStemPc().get(i).getWordStemName(),
                                properNoun.getWordStemPc().get(i).getReferenceWordsFr()));
            }
            gameSessionSteps.add(new GameSessionStep(get5responseChoices(
                    properNoun.getLitTrans()),
                    new ProperName(
                            properNoun.getCurrentName(),
                            properNoun.getEtymoName(),
                            properNoun.getDescrFr(),
                            properNoun.getImage()),
                    radicals));
        }
        return gameSessionSteps;
    }

    /**
     * get15ProperNoun() returns a random list of 15 ProperNoun from the DB
     * according to the theme of words define by the user
     */
    public List<ProperNoun> get15RandomProperNoun(int wordTheme) {
        System.out.println(wordTheme);
        Set<ProperNoun> properNouns = properNounRepository.find15ProperNounsByWordTheme(wordTheme);
        List<ProperNoun> properNounList = new ArrayList<>(properNouns);
        return properNounList;
    }

    /**
     * get5responseChoices() returns a random list of 5 choices of literal
     * translation for a specific ProperNoun, the one in the arguments is the good
     * response which is
     */
    public List<ResponseChoice> get5responseChoices(LiteralTranslation goodLitTrans) {
        List<ResponseChoice> selectedLitTrans = new ArrayList<>();
        selectedLitTrans.add(new ResponseChoice(goodLitTrans.getLitTransFr(), true));
        List<LiteralTranslation> allLiteralTranslations = literalTranslationRepository
                .findAllByLitTransType(goodLitTrans.getLitTransType());
        Collections.shuffle(allLiteralTranslations);
        for (LiteralTranslation literalTrans : allLiteralTranslations) {
            if (!literalTrans.getLitTransFr().equals(goodLitTrans.getLitTransFr())) {
                selectedLitTrans.add(new ResponseChoice(literalTrans.getLitTransFr(), false));
            }
            if (selectedLitTrans.size() == 5)
                break;
        }
        Collections.shuffle(selectedLitTrans);
        return selectedLitTrans;
    }

}
