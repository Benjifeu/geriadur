package com.example.geriadur.util;

import com.example.geriadur.domain.consultation.Lexeme;
import com.example.geriadur.domain.SemanticField;
import com.example.geriadur.domain.consultation.Source;
import com.example.geriadur.dto.UserRegistrationDto;
import com.example.geriadur.repositories.LexemeRepository;
import com.example.geriadur.repositories.SemanticFieldRepository;
import com.example.geriadur.repositories.SourceRepository;
import com.example.geriadur.service.user.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class DataUtil {
    //@Value("${jsonFileName}")String jsonfile
    @Autowired
    private LexemeRepository lexemeRepository;
    @Autowired
    private SemanticFieldRepository semanticFieldRepository;
    @Autowired
    private SourceRepository sourceRepository;
    @Autowired
    private UserServiceImpl userService;
    private List<Lexeme> lexemesInit = new ArrayList<>();
    private List<Source> sourcesInit = new ArrayList<>();
    @PostConstruct
    public void InjectionData() throws IOException {
        lexemeUtil();
        sourceUtil();
        lexemesInit.get(1).setChildren(Stream.of(lexemesInit.get(2),lexemesInit.get(3)).collect(Collectors.toSet()));
        lexemesInit.get(6).setParents(Stream.of(lexemesInit.get(4),lexemesInit.get(0)).collect(Collectors.toSet()));
        SemanticField semanticField =new SemanticField();
        semanticField.setSemanticFieldNameEng("battlefield");
        semanticField.setSemanticFieldNameFr("militaire");
        SemanticField semanticField2 =new SemanticField();
        semanticField2.setSemanticFieldNameEng("family");
        semanticField2.setSemanticFieldNameFr("famille");
        SemanticField semanticField3 =new SemanticField();
        semanticField3.setSemanticFieldNameEng("working");
        semanticField3.setSemanticFieldNameFr("travail");
        SemanticField semanticField4 =new SemanticField();
        semanticField4.setSemanticFieldNameEng("religion");
        semanticField4.setSemanticFieldNameFr("religion");
        semanticFieldRepository.save(semanticField);
        semanticFieldRepository.save(semanticField2);
        semanticFieldRepository.save(semanticField3);
        semanticFieldRepository.save(semanticField4);
        lexemeRepository.saveAll(lexemesInit);
        sourceRepository.saveAll(sourcesInit);
        userService.save(new UserRegistrationDto("User","lastname","email","pass", "U"));
        userService.save(new UserRegistrationDto("Admin","lastname","emailAdmin","pass", "A"));
    }

    void lexemeUtil() throws IOException {

        JsonNode jsonNode = null;
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = new FileInputStream("src/main/java/com/example/geriadur/lexemesInit.json");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                is
        ));
        try {
            jsonNode = mapper.readTree(bufferedReader.lines().collect(Collectors.joining()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("initial data read");
        log.info(jsonNode.textValue());
        lexemesInit = mapper.convertValue(jsonNode, new TypeReference<>() {
        });
    }
    void sourceUtil() throws IOException {

        JsonNode jsonNode = null;
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = new FileInputStream("src/main/java/com/example/geriadur/sourcesInit.json");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                is
        ));
        try {
            jsonNode = mapper.readTree(bufferedReader.lines().collect(Collectors.joining()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("initial data read");
        log.info(jsonNode.textValue());
        sourcesInit = mapper.convertValue(jsonNode, new TypeReference<>() {
        });
    }


}
