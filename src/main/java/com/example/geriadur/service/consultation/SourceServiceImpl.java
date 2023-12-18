package com.example.geriadur.service.consultation;

import com.example.geriadur.domain.consultation.Source;
import com.example.geriadur.repositories.SourceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Slf4j
public class SourceServiceImpl implements SourceService {
    @Autowired
    private SourceRepository SourceRepository;

    @Override
    public List<Source> getAllSources() {
        List<Source> Sources = new ArrayList<>();
        for (Source Source : SourceRepository.findAll()) {
            Sources.add(Source);
        }
        return Sources;
    }
    @Override
    public Source getSourceByID(Long id) {
        Optional<Source> source = SourceRepository.findById(id);
        if(source.isPresent()){
            return source.get();
        }
        else throw new RuntimeException("Their is no Source with the id:" + id);
    }
    @Override
    public void deleteSource(Long id) {
        Optional<Source> Source = SourceRepository.findById(id);
        if(Source.isPresent()){
            SourceRepository.delete(Source.get());
        }
        else throw new RuntimeException("Their is no Source with the id: " + id + " to delete");
    }
    @Override
    public Page<Source> findPaginated(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum-1, pageSize);
        return SourceRepository.findAll(pageable);
    }
    public void addSource(Source Source){
        SourceRepository.save(Source);
    }
}
