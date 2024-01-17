package com.example.geriadur.service.consultation;

import com.example.geriadur.domain.consultation.Lexeme;
import com.example.geriadur.repositories.LexemeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class LexemeServiceImpl implements LexemeService {

    @Autowired
    private LexemeRepository lexemeRepository;

    @Override
    public List<Lexeme> getAllLexemes() {
        List<Lexeme> lexemes = new ArrayList<>();
        for (Lexeme lexeme : lexemeRepository.findAll()) {
            lexemes.add(lexeme);
        }
        return lexemes;
    }
    @Override
    public Lexeme getLexemeByID(Long id) {
        Optional<Lexeme> lexeme = lexemeRepository.findById(id);
        if(lexeme.isPresent()){
            return lexeme.get();
        }
        else throw new RuntimeException("Their is no lexeme with the id:" + id);
    }
    @Override
    public void deleteLexeme(Long id) {
        Optional<Lexeme> lexeme = lexemeRepository.findById(id);
        if(lexeme.isPresent()){
            lexemeRepository.delete(lexeme.get());
        }
        else throw new RuntimeException("Their is no lexeme with the id: " + id + " to delete");
    }
    @Override
    public Page<Lexeme> findPaginated(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum-1, pageSize);
        return lexemeRepository.findAll(pageable);
    }
/*
    @Override
    public List<ShowLexeme> getShowLexemes(List<Lexeme> lexemes) {
        List<ShowLexeme> showLexemes = new ArrayList<>();
        for (Lexeme lexeme : lexemes) {
            ShowLexeme showLexeme = new ShowLexeme();
            showLexeme.setLexemeId(lexeme.getLexemeId());
            showLexeme.setLexemeName(lexeme.getLexemeName());
            showLexeme.setLexemeLanguage(lexeme.getLexemeLanguage());
            showLexeme.setPhonetic(lexeme.getPhonetic());
            showLexeme.setGender(lexeme.getGender());
            showLexeme.setWordClass(lexeme.getWordClass());
            //semanticFieldRepository.findSemanticFieldByLexemes(Arrays.asList(lexeme));
            //showLexeme.setReferenceWords(lexeme.getReferenceWords().get());
            showLexemes.add(showLexeme);
        }
        return showLexemes;
    }*/

    public void addLexeme(Lexeme lexeme){
        lexemeRepository.save(lexeme);
    }
}
