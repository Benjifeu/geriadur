package com.example.geriadur.service.consultation;

import com.example.geriadur.domain.consultation.Quote;
import com.example.geriadur.domain.consultation.Source;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SourceService {
    List<Source> getAllSources();
    void addSource(Source source);
    Source getSourceByID(Long id);
    void deleteSource(Long id);
    Page<Source> findPaginated (int pageNum, int pageSize);
    void setSourceQuoteLink(Quote quote, long l);
    void addQuote(Quote quote);
}
