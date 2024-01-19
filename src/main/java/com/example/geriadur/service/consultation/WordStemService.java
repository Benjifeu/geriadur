package com.example.geriadur.service.consultation;


import com.example.geriadur.domain.SemanticField;
import com.example.geriadur.domain.consultation.WordStem;
import com.example.geriadur.domain.consultation.Quote;
import com.example.geriadur.dto.CreateEtymo;
import com.example.geriadur.dto.CreateWordStem;

import com.example.geriadur.dto.StatisticDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WordStemService {
    List<WordStem> getAllWordStems();
    void addWordStem(WordStem wordStem);
    WordStem getWordStemByID(Long id);
    void deleteWordStem(Long id);
    Page<WordStem> findPaginated (int pageNum, int pageSize);
    void addAWordStem(CreateWordStem createWordStem);
    void setWordStemQuoteLink(Quote quote, String wordStemString);
    void setWordStemEtymonLink(String currentName, List<String> wordStems);
    void addProperNoun(CreateEtymo createEtymo);
    void addSemanticField(SemanticField semanticField);
    StatisticDTO getStatisticInfo();
}
