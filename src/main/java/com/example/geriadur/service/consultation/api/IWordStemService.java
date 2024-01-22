package com.example.geriadur.service.consultation.api;

import com.example.geriadur.domain.SemanticField;
import com.example.geriadur.domain.consultation.Quote;
import com.example.geriadur.domain.consultation.WordStem;
import com.example.geriadur.dto.CreateEtymo;
import com.example.geriadur.dto.CreateWordStem;
import com.example.geriadur.dto.StatisticDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IWordStemService {
    List<WordStem> getAllWordStems();
    void setWordStemQuoteLink(Quote quote, String wordStemStr);
    void addProperNoun(CreateEtymo createEtymo);
    void setWordStemEtymonLink(String etymonNameStr, List<String> wordStemsString);
    void addAWordStem(CreateWordStem createWordStem);

    Quote addQuote(String quoteStr, String source);

    WordStem getWordStemByID(Long id);
    void deleteWordStem(Long id);
    Page<WordStem> findPaginated(int pageNum, int pageSize);
    void addWordStem(WordStem wordStem);
    void addSemanticField(SemanticField semanticField);
    StatisticDTO getStatisticInfo();
}
