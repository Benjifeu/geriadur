package com.example.geriadur.service.consultation;

import com.example.geriadur.domain.consultation.Quote;
import com.example.geriadur.domain.consultation.Source;
import com.example.geriadur.repositories.QuoteRepository;
import com.example.geriadur.repositories.SourceRepository;
import com.example.geriadur.service.consultation.api.ISourceService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SourceService implements ISourceService {
    @Autowired
    private SourceRepository sourceRepository;
    @Autowired
    private QuoteRepository quoteRepository;

    @Override
    public List<Source> getAllSources() {
        List<Source> Sources = new ArrayList<>();
        for (Source Source : sourceRepository.findAll()) {
            Sources.add(Source);
        }
        return Sources;
    }

    @Override
    public Source getSourceByID(Long id) {
        Optional<Source> source = sourceRepository.findById(id);
        if (source.isPresent()) {
            return source.get();
        } else
            throw new RuntimeException("Their is no Source with the id:" + id);
    }

    @Override
    public void deleteSource(Long id) {
        Optional<Source> Source = sourceRepository.findById(id);
        if (Source.isPresent()) {
            sourceRepository.delete(Source.get());
        } else
            throw new RuntimeException("Their is no Source with the id: " + id + " to delete");
    }

    @Override
    public Page<Source> findPaginated(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return sourceRepository.findAll(pageable);
    }

    public void addSource(Source Source) {
        sourceRepository.save(Source);
    }


    public void setSourceQuoteLink(Quote quote, long l) {
        quote.setSource(sourceRepository.getReferenceById(l));
        quoteRepository.save(quote);
    }



    @Override
    public void addQuote(Quote quote) {
        quoteRepository.save(quote);
    }
}
