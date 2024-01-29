package com.example.geriadur.repositories;

import com.example.geriadur.entity.consultation.Quote;
import com.example.geriadur.entity.consultation.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
    Set<Quote> findBySource(Source source);
}
