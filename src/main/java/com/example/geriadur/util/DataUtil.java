package com.example.geriadur.util;

import com.example.geriadur.domain.SemanticField;
import com.example.geriadur.domain.consultation.Source;
import com.example.geriadur.dto.*;
import com.example.geriadur.service.consultation.WordStemService;
import com.example.geriadur.service.consultation.SourceService;
import com.example.geriadur.service.user.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * the DataUtil class is responsible to retrieve the data from the JSON files
 * present at the root of the project
 ** (wordStemsInit, , etymonsInit, semanticFieldinit, sourcesInit)
 **/
@Service
@Slf4j
public class DataUtil {

    @Autowired
    private UserService userService;
    @Autowired
    private WordStemService wordStemService;
    @Autowired
    private SourceService sourceService;

    private List<CreateWordStem> wordStemsInit = new ArrayList<>();
    private List<CreateEtymo> etymonNamesInit = new ArrayList<>();
    private List<Source> sourcesInit = new ArrayList<>();
    private List<SemanticField> semanticFieldsInit = new ArrayList<>();

    /**
     * InjectionData() the data in order to be transformed as entities and then
     * persisted in the mySql database
     **/
    @PostConstruct
    public void InjectionData() throws IOException {
        readJsonData("wordStemsInit");
        readJsonData("sourcesInit");
        readJsonData("etymonsInit");
        readJsonData("semanticFieldsInit");

        // save all the semanticField
        for (SemanticField semanticField: semanticFieldsInit) {
            wordStemService.addSemanticField(semanticField);
        }

        // save all the sources
        for (Source source: sourcesInit) {
            sourceService.addSource(source);
        }

        // save all the etymons
        for (CreateEtymo createEtymo : etymonNamesInit) {
            wordStemService.addProperNoun(createEtymo);
        }

        // save all the wordStems
        for (CreateWordStem createWordStem : wordStemsInit) {
            wordStemService.addAWordStem(createWordStem);
        }

        // config the wordStems of each etymons
        for (CreateEtymo etymonName : etymonNamesInit) {
            System.out.println(etymonName.getWordStems());
            wordStemService.setWordStemEtymonLink(etymonName.getCurrentName(), etymonName.getWordStems());
        }

        userService.save(new UserRegistrationDto("UserAccount", "lastname", "email", "pass", "U", 1));
        userService.save(new UserRegistrationDto("Admin", "lastname", "emailAdmin", "pass", "A", 1));
        wordStemService.getStatisticInfo();
    }




    /**
     * readJsonData(String jsonName) fetch the data contained in the json files at the root of the project
     **/
    void readJsonData(String jsonName) throws IOException {
        JsonNode jsonNode = null;
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = new FileInputStream("src/main/java/com/example/geriadur/" + jsonName + ".json");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                is));
        try {
            jsonNode = mapper.readTree(bufferedReader.lines().collect(Collectors.joining()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("initial data read");
        log.info(jsonNode.textValue());
        if (jsonName.equals("wordStemsInit")) {
            wordStemsInit = mapper.convertValue(jsonNode, new TypeReference<>() {
            });
        } else if (jsonName.equals("sourcesInit")) {
            sourcesInit = mapper.convertValue(jsonNode, new TypeReference<>() {
            });
        } else if (jsonName.equals("etymonsInit")) {
            etymonNamesInit = mapper.convertValue(jsonNode, new TypeReference<>() {
            });
        } else if (jsonName.equals("semanticFieldsInit")) {
            semanticFieldsInit = mapper.convertValue(jsonNode, new TypeReference<>() {
            });
        }

    }

}
