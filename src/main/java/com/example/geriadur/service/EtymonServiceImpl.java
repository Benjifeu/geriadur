package com.example.geriadur.service;

import com.example.geriadur.domain.Etymon;
import com.example.geriadur.repositories.EtymonRepository;
import com.example.geriadur.repositories.SemanticFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EtymonServiceImpl implements EtymonService{
    @Autowired
    private EtymonRepository etymonRepository;

    @Override
    public List<Etymon> getAllEtymons() {
        List<Etymon> etymons = new ArrayList<>();
        for (Etymon etymon : etymonRepository.findAll()) {
            etymons.add(etymon);
        }
        return etymons;
    }

    public void addEtymon(Etymon etymon){


    }
}
