package com.example.geriadur.service.consultation;

import com.example.geriadur.domain.consultation.Lexeme;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LexemeService {
    List<Lexeme> getAllLexemes();
    void addLexeme(Lexeme lexeme);
    Lexeme getLexemeByID(Long id);
    void deleteLexeme(Long id);
    Page<Lexeme> findPaginated (int pageNum, int pageSize);

}
