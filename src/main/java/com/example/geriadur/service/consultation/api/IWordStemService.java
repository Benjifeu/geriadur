package com.example.geriadur.service.consultation.api;

import com.example.geriadur.dto.*;
import com.example.geriadur.entity.ProperNoun;
import com.example.geriadur.entity.SemanticField;
import com.example.geriadur.entity.consultation.Quote;
import com.example.geriadur.entity.consultation.WordStem;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IWordStemService {
    List<ProperNounsDTO> getProperNouns();
    List<WordStem> getAllWordStems();
    void setWordStemQuoteLink(Quote quote, String wordStemStr);
    void addProperNoun(CreateProperNoun createEtymo);
    void setWordStemEtymonLink(String etymonNameStr, List<String> wordStemsString);
    void addAWordStem(CreateWordStem createWordStem);
    Quote addQuote(String quoteStr, String source);
    WordStem getWordStemByID(Long id);
    void deleteWordStem(Long id);
    void addWordStem(WordStem wordStem);
    void addSemanticField(SemanticField semanticField);
    StatisticDTO getStatisticInfo();
    void saveImage(MultipartFile file, long properNounId);
    void saveAllSemanticField(List<SemanticField> semanticFieldsInit);
    void saveAllProperNouns(List<CreateProperNoun> etymonNamesInit);
    void saveAllWordStems(List<CreateWordStem> wordStemsInit);
    List<ShowWordstem> findAll();
}
