package com.example.geriadur.service;

import com.example.geriadur.domain.Etymon;
import com.example.geriadur.domain.Source;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SourceService {
    List<Source> getAllSources();
    void addSource(Source source);
    Source getSourceByID(Long id);
    void deleteSource(Long id);
    Page<Source> findPaginated (int pageNum, int pageSize);
}
