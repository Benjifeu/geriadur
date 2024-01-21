package com.example.geriadur.repositories;

import com.example.geriadur.domain.consultation.Quote;
import com.example.geriadur.domain.consultation.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
    Optional<Quote> findBySource(Source source);
    Optional<Quote> findByQuoteId(Long id);
}
