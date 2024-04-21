package com.example.geriadur.service.consultation.api;

import com.example.geriadur.entity.consultation.Author;

import com.example.geriadur.entity.consultation.Source;

import com.example.geriadur.dto.CreateSource;
import com.example.geriadur.dto.ShowSourcesPage;
import com.example.geriadur.dto.SourceBasicDTO;

import java.util.List;

public interface ISourceService {

    List<Source> getAllSources();

    void deleteSource(Long id);

    ShowSourcesPage findPaginated(int pageNum, int pageSize);

    void addSource(CreateSource Source);

    Source getSourceByID(Long id);

    void addAuthor(Author author);

    void saveAll(List<CreateSource> sourcesInit);

    void saveAllAuthors(List<Author> authorsInit);

    List<SourceBasicDTO> getSourceStringList();
}
