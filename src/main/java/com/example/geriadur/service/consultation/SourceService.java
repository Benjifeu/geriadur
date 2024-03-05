package com.example.geriadur.service.consultation;

import com.example.geriadur.entity.consultation.Author;
import com.example.geriadur.entity.consultation.Source;
import com.example.geriadur.dto.CreateSource;
import com.example.geriadur.repositories.AuthorRepository;
import com.example.geriadur.repositories.QuoteRepository;
import com.example.geriadur.repositories.SourceRepository;
import com.example.geriadur.service.consultation.api.ISourceService;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class SourceService implements ISourceService {
    @Autowired
    private SourceRepository sourceRepository;
    @Autowired
    private QuoteRepository quoteRepository;
    @Autowired
    private AuthorRepository authorRepository;

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

    public void addSource(CreateSource createSource) {
        Source source = new Source();
        source.setSourceNameInOriginalLanguage(createSource.getSourceNameInOriginalLanguage());
        source.setSourceNameInEnglish(createSource.getSourceNameInEnglish());
        source.setTypeOfSource(createSource.getTypeOfSource());
        source.setLanguage(createSource.getLanguage());
        source.setAbbreviation(createSource.getAbbreviation());
        source.setDescription(createSource.getDescription());
        source.setDateOfPublication(createSource.getDateOfPublication());
        System.out.println(createSource.getAuthors());
        if (createSource.getAuthors() != null) {
            source.setAuthors(Stream.of(
                    authorRepository.findAuthorByAuthorName(createSource.getAuthors()).get()
            ).collect(Collectors.toSet()));
        }
        sourceRepository.save(source);
        log.info("The source with the title name: \"" +source.getSourceNameInEnglish() + "\" has been saved.");
    }

    @Override
    public void addAuthor(Author author) {
        authorRepository.save(author);
    }
}
