package com.example.geriadur.service.consultation.api;

import com.example.geriadur.dto.*;
import com.example.geriadur.entity.EtymonName;
import com.example.geriadur.entity.SemanticField;
import com.example.geriadur.entity.consultation.Quote;
import com.example.geriadur.entity.consultation.WordStem;

import java.util.List;

public interface IWordStemService {
    List<ProperNounsDTO> getProperNouns();
    List<WordStem> getAllWordStems();
    void setWordStemQuoteLink(Quote quote, String wordStemStr);
    void addProperNoun(CreateEtymo createEtymo);
    void setWordStemEtymonLink(String etymonNameStr, List<String> wordStemsString);
    void addAWordStem(CreateWordStem createWordStem);
    Quote addQuote(String quoteStr, String source);
    WordStem getWordStemByID(Long id);
    void deleteWordStem(Long id);
    ShowWordstemPage findPaginated(int pageNum, int pageSize);
    void addWordStem(WordStem wordStem);
    void addSemanticField(SemanticField semanticField);
    StatisticDTO getStatisticInfo();

    void saveAllSemanticField(List<SemanticField> semanticFieldsInit);

    void saveAllProperNouns(List<CreateEtymo> etymonNamesInit);

    void saveAllWordStems(List<CreateWordStem> wordStemsInit);
}
