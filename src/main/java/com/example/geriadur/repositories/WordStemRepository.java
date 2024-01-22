package com.example.geriadur.repositories;

import com.example.geriadur.domain.consultation.WordStem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface WordStemRepository extends JpaRepository<WordStem, Long> {
    Optional<WordStem> findByWordStemName(String wordStemName);
}
