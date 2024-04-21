package com.example.geriadur.service.consultation.api;

import com.example.geriadur.dto.*;
import com.example.geriadur.entity.SemanticField;
import com.example.geriadur.entity.consultation.Quote;
import com.example.geriadur.entity.consultation.WordStem;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IWordStemService {
    List<ProperNounsDTO> getProperNouns();
    List<WordstemBasicDTO> findAll();
    List<String> getWordStemsPCStringList();
    void setWordStemQuoteLink(Quote quote, String wordStemStr);
    void setWordStemEtymonLink(String etymonNameStr, List<String> wordStemsString);
    WordstemFullDTO getWordStemByName(String id);
    void deleteWordStem(Long id);
    void addProperNoun(ProperNounsDTO createEtymo);
    WordStem addWordStem(WordstemBasicDTO wordStem);
    Quote addQuote(String quoteStr, String source);
    void addSemanticField(SemanticField semanticField);
    StatisticDTO getStatisticInfo();
    void saveImage(MultipartFile file, long properNounId);
    void saveAllSemanticField(List<SemanticField> semanticFieldsInit);
}
