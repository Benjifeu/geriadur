package com.example.geriadur.service.consultation;

import com.example.geriadur.dto.*;
import com.example.geriadur.entity.EtymonName;
import com.example.geriadur.entity.SemanticField;
import com.example.geriadur.entity.consultation.Source;
import com.example.geriadur.entity.consultation.WordStem;
import com.example.geriadur.entity.consultation.Quote;
import com.example.geriadur.repositories.*;
import com.example.geriadur.service.consultation.api.IWordStemService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
/**
 ** WordStemServiceImpl implement the interface WordStemService which allow CRUD
 ** fonctions on the object WordStem
 **/
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
    @Autowired
    private SourceRepository sourceRepository;


    public void addProperNoun(CreateEtymo createEtymo) {
        literalTranslationRepository.save(createEtymo.getLitTrans());
        EtymonName etymonName = new EtymonName();
        etymonName.setEtymoName(createEtymo.getEtymoName());
        etymonName.setCurrentName(createEtymo.getCurrentName());
        etymonName.setWordTheme(createEtymo.getWordTheme());
        etymonName.setLitTrans(createEtymo.getLitTrans());
        etymonName.setDescrFr(createEtymo.getDescrFr());
        etymonName.setDescrEng(createEtymo.getDescrEng());
        etymonNameRepository.save(etymonName);
        log.info("The proper noun stem: \"" + etymonName.getCurrentName() + "\" has been added.");
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

    /**
     * addAWordStem(...) save a new wordstem from the dto CreateWordStem
     * and setting the links with the quotes, sources and parents
     */
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
        if (createWordStem.getSemanticField() != null) {
            wordStem.setSemanticField(semanticFieldRepository.findSemanticFieldBySemFieldNameEng(createWordStem.getSemanticField()).get());
        }

        if (createWordStem.getQuotes() != null) {
            Set<Quote> quotes = new HashSet<>();
            for (int i = 0; i < createWordStem.getQuotes().size(); i++) {
                quotes.add(addQuote(createWordStem.getQuotes().get(i), createWordStem.getSources().get(i)));
            }
            wordStem.setQuotes(quotes);
        }
        if (createWordStem.getSources() != null) {
            Set<Source> sources = new HashSet<>();
            for (int i = 0; i < createWordStem.getSources().size(); i++) {
                sources.add(sourceRepository.findSourceByAbbreviation(createWordStem.getSources().get(i)).get());
            }
            wordStem.setSources(sources);

        }
        if (createWordStem.getParentsWordStemStr() != null) {
            for (String parentStr : createWordStem.getParentsWordStemStr()) {
                wordStem.getParents().add(wordStemRepository.findByWordStemName(parentStr).get());
            }
        }
        wordStemRepository.save(wordStem);
        log.info("The new word stem: \"" + wordStem.getWordStemName() + "\" has been added.");
    }

    /**
     * addQuote() save a new quote in DB and return it
     */
    @Override
    public Quote addQuote(String quoteStr, String source) {
        Quote quote = new Quote();
        quote.setQuoteText(quoteStr);
        Optional<Source> optSource = sourceRepository.findSourceByAbbreviation(source);
        if (optSource.isPresent()) {
            quote.setSource(optSource.get());
            quoteRepository.save(quote);
            log.info("A quote for the source: \"" + quote.getSource().getSourceNameInEnglish() + "\" has been added.");
            return quote;
        } else
            throw new RuntimeException("The source " + source + " of the new quote: \"" + quoteStr + "\" doesn't exist.");

    }

    /**
     * getWordStemByID() returns the specified wordStem according to his ID in DB
     */
    @Override
    public WordStem getWordStemByID(Long id) {
        Optional<WordStem> wordStem = wordStemRepository.findById(id);
        if (wordStem.isPresent()) {
            return wordStem.get();
        } else
            throw new RuntimeException("Their is no wordStem with the id:" + id);
    }

    /**
     * deleteWordStem() delete a wordStem according to the his ID in DB
     */
    @Override
    public void deleteWordStem(Long id) {
        Optional<WordStem> wordStem = wordStemRepository.findById(id);
        if (wordStem.isPresent()) {
            wordStemRepository.delete(wordStem.get());
        } else
            throw new RuntimeException("Their is no wordStem with the id: " + id + " to delete");
    }

    /**
     * * findPaginated returns a list of wordStemsWord with te size define by the
     * pageSize argument. This data will be show for the client in a pageable table
     **/
    @Override
    public ShowWordstemPage findPaginated(int pageNum, int pageSize) {
        List<ShowWordstem> wordStems = new ArrayList<>();
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page page = wordStemRepository.findAll(pageable);
        for (WordStem wordStem : (List<WordStem>) page.getContent()) {
            String parent=null;
            if(!wordStem.getParents().isEmpty()){
                parent= wordStem.getParents().stream().findFirst().get().getWordStemName();
            }
            ShowWordstem showWordstem = new ShowWordstem(
                    wordStem.getWordStemName(),
                    wordStem.getWordStemLanguage().toString(),
                    wordStem.getPhonetic(),
                    wordStem.getGender().toString(),
                    wordStem.getWordClass().toString(),
                    wordStem.getReferenceWordsEng(),
                    wordStem.getReferenceWordsFr(), wordStem.getSemanticField().getSemFieldNameFr(), parent);
            wordStems.add(showWordstem);
            log.info("The word stem "+wordStem.getWordStemName()+" has been retrieved to be displayed on the wordstem list page.");
        }
        ShowWordstemPage showWordstemPage = new ShowWordstemPage();
        showWordstemPage.setPageWordstems(wordStems);
        showWordstemPage.setWordstemsCount((int) page.getTotalElements());
        showWordstemPage.setCurrentPage(pageNum);
        showWordstemPage.setPageCount(page.getTotalPages());
        return showWordstemPage;
    }


    public void addWordStem(WordStem wordStem) {
        wordStemRepository.save(wordStem);
    }

    public void addSemanticField(SemanticField semanticField) {
        semanticFieldRepository.save(semanticField);
    }


    /**
     * getStatisticInfo() returns a simple count of each etymon (proper name) registered in DB and group by themes
     */
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

    /**
     * getAllWordStems() returns all the wordStems present in the database
     */
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

}
