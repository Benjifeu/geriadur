package com.example.geriadur.repositories;

import com.example.geriadur.domain.consultation.Lexeme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface LexemeRepository extends JpaRepository<Lexeme, Long> {
    Optional<Lexeme> findByLexemeName(String lexemeName);
}
