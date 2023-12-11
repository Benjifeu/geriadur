package com.example.geriadur.service;

import com.example.geriadur.domain.Etymon;
import com.example.geriadur.dto.ShowEtymon;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EtymonService {
    List<Etymon> getAllEtymons();
    void addEtymon(Etymon etymon);
    Etymon getEtymonByID(Long id);
    void deleteEtymon(Long id);
    Page<Etymon> findPaginated (int pageNum, int pageSize);

    List<ShowEtymon> getShowEtymons(List<Etymon> etymons);
}
