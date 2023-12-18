package com.example.geriadur.repositories;

import com.example.geriadur.domain.SemanticField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemanticFieldRepository extends JpaRepository<SemanticField, Long> {
}
