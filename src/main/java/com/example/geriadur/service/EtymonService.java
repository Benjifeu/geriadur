package com.example.geriadur.service;

import com.example.geriadur.domain.Etymon;

import java.util.List;

public interface EtymonService {
    List<Etymon> getAllEtymons();
    void addEtymon(Etymon etymon);
}
