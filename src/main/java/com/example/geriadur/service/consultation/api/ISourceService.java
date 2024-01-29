package com.example.geriadur.service.consultation.api;

import com.example.geriadur.entity.consultation.Author;

import com.example.geriadur.entity.consultation.Source;

import com.example.geriadur.dto.CreateSource;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISourceService {

    List<Source> getAllSources();

    void deleteSource(Long id);

    Page<Source> findPaginated(int pageNum, int pageSize);

    void addSource(CreateSource Source);

    Source getSourceByID(Long id);

    void addAuthor(Author author);
}
