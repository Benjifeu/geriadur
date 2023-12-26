package com.example.geriadur.repositories;

import com.example.geriadur.domain.LiteralTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LiteralTranslationRepository extends JpaRepository<LiteralTranslation, Long> {
    List<LiteralTranslation> findAllByLitTransType (int litTransType);
}
