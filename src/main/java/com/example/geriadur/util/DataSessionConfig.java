package com.example.geriadur.util;

import com.example.geriadur.domain.EtymonName;
import com.example.geriadur.domain.SemanticField;
import com.example.geriadur.dto.LiteralTranslation;
import com.example.geriadur.repositories.EtymonNameRepository;
import com.example.geriadur.repositories.SemanticFieldRepository;
import com.example.geriadur.repositories.UserRepository;
import com.example.geriadur.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Configuration
public class DataSessionConfig {


    @Autowired
    private EtymonNameRepository etymonNameRepository;
    @Autowired
    private SemanticFieldRepository semanticFieldRepository;
    @Autowired
    private UserRepository userRepository;


    @Bean
    @SessionScope
    public Set<LiteralTranslation> getAllLiteralTranslationBySemField(@Value("1")long semanticFieldId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int language = userRepository.findByEmail(auth.getPrincipal().toString()).get().getLanguage();
        Set<EtymonName> etymonNames = getAllEtymon(semanticFieldId);
        Set<LiteralTranslation> literalTranslations = new HashSet<>();
        for (EtymonName etymonName : etymonNames)
            switch (language) {
                case 1:
                    literalTranslations.add(new LiteralTranslation(etymonName.getResponseFr()));
                case 2:
                    literalTranslations.add(new LiteralTranslation(etymonName.getResponseEng()));
            }
        return literalTranslations;
    }
    @Bean
    @SessionScope
    public int getScore() {
        return 0;
    }
    @Bean
    @SessionScope
    public List<EtymonName> get15RandomEtymonName(@Value("1")long semanticFieldId) {
        Set<EtymonName> etymonNames = getAllEtymon(semanticFieldId);
        for (EtymonName element : etymonNames)
            if (etymonNames.size() > 15)
                etymonNames.remove(element);
        List<EtymonName> etymonNameList = new ArrayList<>(etymonNames);
        return etymonNameList;
    }


    public Set<EtymonName> getAllEtymon(long semanticFieldId) {
        Optional<SemanticField> semanticField = semanticFieldRepository.findById(semanticFieldId);
        if (semanticField.isPresent()) {
            return etymonNameRepository.findEtymonNamesBySemanticField(semanticField.get());
        } else throw new RuntimeException("Their is no semantic field with the id:" + semanticFieldId);
    }


}
