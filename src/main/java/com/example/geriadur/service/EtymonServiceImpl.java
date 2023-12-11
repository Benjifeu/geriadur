package com.example.geriadur.service;

import com.example.geriadur.domain.Etymon;
import com.example.geriadur.dto.ShowEtymon;
import com.example.geriadur.repositories.EtymonRepository;
import com.example.geriadur.repositories.SemanticFieldRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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
    @Override
    public Etymon getEtymonByID(Long id) {
        Optional<Etymon> etymon = etymonRepository.findById(id);
        if(etymon.isPresent()){
            return etymon.get();
        }
        else throw new RuntimeException("Their is no etymon with the id:" + id);
    }
    @Override
    public void deleteEtymon(Long id) {
        Optional<Etymon> etymon = etymonRepository.findById(id);
        if(etymon.isPresent()){
            etymonRepository.delete(etymon.get());
        }
        else throw new RuntimeException("Their is no etymon with the id: " + id + " to delete");
    }
    @Override
    public Page<Etymon> findPaginated(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum-1, pageSize);
        return etymonRepository.findAll(pageable);
    }

    @Override
    public List<ShowEtymon> getShowEtymons(List<Etymon> etymons) {
        List<ShowEtymon> showEtymons = new ArrayList<>();
        for (Etymon etymon: etymons) {
            ShowEtymon showEtymon = new ShowEtymon();
            showEtymon.setEtymonId(etymon.getEtymonId());
            showEtymon.setEtymonName(etymon.getEtymonName());
            showEtymon.setEtymonLanguage(etymon.getEtymonLanguage());
            showEtymon.setPhonetic(etymon.getPhonetic());
            showEtymon.setGender(etymon.getGender());
            showEtymon.setWordClass(etymon.getWordClass());
            //semanticFieldRepository.findSemanticFieldByEtymons(Arrays.asList(etymon));
            //showEtymon.setReferenceWords(etymon.getReferenceWords().get());
            showEtymons.add(showEtymon);
        }
        return showEtymons;
    }

    public void addEtymon(Etymon etymon){
        etymonRepository.save(etymon);
    }
}
