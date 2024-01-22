package com.example.geriadur.repositories;

import com.example.geriadur.domain.SemanticField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SemanticFieldRepository extends JpaRepository<SemanticField, Long> {

    Optional<SemanticField> findSemanticFieldBySemFieldNameEng(String semFieldNameEng);
}
