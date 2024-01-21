package com.example.geriadur.service.consultation;

import com.example.geriadur.domain.EtymonName;
import com.example.geriadur.domain.SemanticField;
import com.example.geriadur.domain.consultation.WordStem;
import com.example.geriadur.domain.consultation.Quote;
import com.example.geriadur.dto.CreateEtymo;
import com.example.geriadur.dto.CreateWordStem;
import com.example.geriadur.dto.StatisticDTO;
import com.example.geriadur.repositories.EtymonNameRepository;
import com.example.geriadur.repositories.WordStemRepository;
import com.example.geriadur.service.consultation.api.IWordStemService;
import com.example.geriadur.repositories.LiteralTranslationRepository;
import com.example.geriadur.repositories.QuoteRepository;
import com.example.geriadur.repositories.SemanticFieldRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
/*
 * WordStemServiceImpl implement the interface WordStemService which allow CRUD
 * fonctions on the object WordStem
 */
public class WordStemService implements IWordStemService {

    @Autowired
    private WordStemRepository wordStemRepository;
    @Autowired
    private EtymonNameRepository etymonNameRepository;
    @Autowired
    private QuoteRepository quoteRepository;
    @Autowired
    private LiteralTranslationRepository literalTranslationRepository;
    @Autowired
    private SemanticFieldRepository semanticFieldRepository;

    /* getAllWordStems() returns all the wordStems present in the database */
    @Override
    public List<WordStem> getAllWordStems() {
        List<WordStem> wordStems = new ArrayList<>();
        for (WordStem wordStem : wordStemRepository.findAll()) {
            wordStems.add(wordStem);
        }
        return wordStems;
    }

    public void setWordStemQuoteLink(Quote quote, String wordStemStr) {
        Optional<WordStem> wordStem = wordStemRepository.findByWordStemName(wordStemStr);
        if (wordStem.isPresent()) {
            List<WordStem> wordStems = quote.getWordStems();
            wordStems.add(wordStem.get());
            quote.setWordStems(wordStems);
            quoteRepository.save(quote);
        } else
            throw new IllegalArgumentException("the wordStem: " + wordStemStr + " doesn't exist in DB.");
    }

    public void addProperNoun(CreateEtymo createEtymo) {
        literalTranslationRepository.save(createEtymo.getLitTrans());
        EtymonName etymonName = new EtymonName();
        etymonName.setEtymoName(createEtymo.getEtymoName());
        etymonName.setCurrentName(createEtymo.getCurrentName());
        etymonName.setWordTheme(createEtymo.getWordTheme());
        etymonName.setLitTrans(createEtymo.getLitTrans());
        etymonName.setDescrFr(createEtymo.getDescrFr());
        etymonName.setDescrEng(createEtymo.getDescrEng());
        /* TODO semanticfield is not define yet */
        etymonName.setSemanticField(semanticFieldRepository.getReferenceById(1L));
        etymonNameRepository.save(etymonName);
    }

    public void setWordStemEtymonLink(String etymonNameStr, List<String> wordStemsString) {
        EtymonName etymonName = etymonNameRepository.findEtymonNameByCurrentName(etymonNameStr).get();
        Map<Integer, WordStem> wordStems = new HashMap<>();
        for (int i = 0; i < wordStemsString.size(); ++i) {
            Optional<WordStem> wordStem = wordStemRepository.findByWordStemName(wordStemsString.get(i));
            if (wordStem.isPresent()) {
                wordStems.put(i, wordStem.get());
            } else
                throw new IllegalArgumentException("the wordStem: " + wordStemsString.get(i) + " doesn't exist in DB.");
        }
        etymonName.setWordStemPc(wordStems);
        etymonNameRepository.save(etymonName);
    }

    public void addAWordStem(CreateWordStem createWordStem) {
        WordStem wordStem = new WordStem();
        wordStem.setWordStemName(createWordStem.getWordStemName());
        wordStem.setWordStemLanguage(createWordStem.getWordStemLanguage());
        wordStem.setGender(createWordStem.getGender());
        wordStem.setWordClass(createWordStem.getWordClass());
        wordStem.setDescrEng(createWordStem.getDescrEng());
        wordStem.setDescrFr(createWordStem.getDescrFr());
        wordStem.setReferenceWordsEng(createWordStem.getReferenceWordsEng());
        wordStem.setReferenceWordsFr(createWordStem.getReferenceWordsFr());
        wordStem.setPhonetic(createWordStem.getPhonetic());
        if (createWordStem.getParentsWordStemStr() != null) {
            for (String parentStr : createWordStem.getParentsWordStemStr()) {
                wordStem.getParents().add(wordStemRepository.findByWordStemName(parentStr).get());
            }
        }
        wordStemRepository.save(wordStem);
    }

    /* getWordStemByID() returns the specify wordStem according to the his ID in DB */
    @Override
    public WordStem getWordStemByID(Long id) {
        Optional<WordStem> wordStem = wordStemRepository.findById(id);
        if (wordStem.isPresent()) {
            return wordStem.get();
        } else
            throw new RuntimeException("Their is no wordStem with the id:" + id);
    }

    /* deleteWordStem() delete a wordStem according to the his ID in DB */
    @Override
    public void deleteWordStem(Long id) {
        Optional<WordStem> wordStem = wordStemRepository.findById(id);
        if (wordStem.isPresent()) {
            wordStemRepository.delete(wordStem.get());
        } else
            throw new RuntimeException("Their is no wordStem with the id: " + id + " to delete");
    }
    /*
     * findPaginated returns a list of wordStemsWord with te size define by the
     * pageSize argument. This data will be show for the client in a pageable table
     */

    @Override
    public Page<WordStem> findPaginated(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return wordStemRepository.findAll(pageable);
    }

    public void addWordStem(WordStem wordStem) {
        wordStemRepository.save(wordStem);
    }

    public void addSemanticField(SemanticField semanticField) {
        semanticFieldRepository.save(semanticField);
    }
    /** getStatisticInfo() returns a simple count of each etymon (proper name) registered in DB and group by themes */
    public StatisticDTO getStatisticInfo() {
        StatisticDTO statisticDTO = new StatisticDTO();
        statisticDTO.setPlacesCount(etymonNameRepository.findAllEtymonNamesByWordTheme(1).size());
        statisticDTO.setHistoristicFiguresCount(etymonNameRepository.findAllEtymonNamesByWordTheme(2).size());
        statisticDTO.setMythicFiguresCount(etymonNameRepository.findAllEtymonNamesByWordTheme(3).size());
        statisticDTO.setTribesCount(etymonNameRepository.findAllEtymonNamesByWordTheme(4).size());
        statisticDTO.setObjectsCount(etymonNameRepository.findAllEtymonNamesByWordTheme(5).size());
        statisticDTO.setWordStemsCount((int) wordStemRepository.count());
        System.out.println(
                " WordStems count: " + statisticDTO.getWordStemsCount() +
                        "\n Placescount: " + statisticDTO.getPlacesCount() +
                        "\n Historcic figures count: " + statisticDTO.getHistoristicFiguresCount() +
                        "\n Mythic figures count: " + statisticDTO.getMythicFiguresCount() +
                        "\n Tribes count: " + statisticDTO.getTribesCount() +
                        "\n Obects count: " + statisticDTO.getObjectsCount());
        return statisticDTO;
    }
}
