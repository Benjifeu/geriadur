package com.example.geriadur.util;

import com.example.geriadur.domain.SemanticField;
import com.example.geriadur.domain.consultation.Source;
import com.example.geriadur.dto.*;
import com.example.geriadur.service.consultation.api.ISourceService;
import com.example.geriadur.service.consultation.api.IWordStemService;
import com.example.geriadur.service.user.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;


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
    private IWordStemService wordStemService;
    @Autowired
    private ISourceService sourceService;

    //list of data transfer objects that will be persisted in the database
    private List<CreateWordStem> wordStemsInit = new ArrayList<>();
    private List<CreateEtymo> etymonNamesInit = new ArrayList<>();
    private List<Source> sourcesInit = new ArrayList<>();
    private List<SemanticField> semanticFieldsInit = new ArrayList<>();
    private List<CreateUser> usersInit = new ArrayList<>();

    //names of the the json files to retrieve
    private final String jsonWordStem = "wordStemsInit";
    private final String jsonSource = "sourcesInit";
    private final String jsonEtymon = "etymonsInit";
    private final String jsonSemField = "semanticFieldsInit";
    private final String jsonUser = "usersInit";

    /**
     * InjectionData() the data in order to be transformed as entities and then
     * persisted in the mySql database
     **/
    @PostConstruct
    public void InjectionData() throws IOException {
        readJsonData(jsonWordStem);
        readJsonData(jsonSource);
        readJsonData(jsonEtymon);
        readJsonData(jsonSemField);
        readJsonData(jsonUser);

        // save all the semanticField
        for (SemanticField semanticField : semanticFieldsInit) {
            wordStemService.addSemanticField(semanticField);
        }

        // save all the sources
        for (Source source : sourcesInit) {
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

        // save all default users
        for (CreateUser createUser : usersInit) {
            userService.save(createUser);
        }

        // config the wordStems of each etymons
        for (CreateEtymo etymonName : etymonNamesInit) {
            System.out.println(etymonName.getWordStems());
            wordStemService.setWordStemEtymonLink(etymonName.getCurrentName(), etymonName.getWordStems());
        }

        wordStemService.getStatisticInfo();
    }

    /**
     * readJsonData(String jsonName) fetch the data contained in the json files at
     * the root of the project
     **/
    void readJsonData(String jsonName) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        InputStream is = null;
         try {
         is = new FileInputStream("src/main/java/com/example/geriadur/" + jsonName + ".json");
         } catch (FileNotFoundException e) {
         e.printStackTrace();
         }

        try {
            switch (jsonName) {
                case jsonWordStem:
                    wordStemsInit = mapper.readValue(is, new TypeReference<List<CreateWordStem>>() {});
                    break;
                    case jsonEtymon:
                    etymonNamesInit = mapper.readValue(is, new TypeReference<List<CreateEtymo>>() {});
                    break;
                    case jsonSemField:
                    semanticFieldsInit = mapper.readValue(is, new TypeReference<List<SemanticField>>() {});
                    break;
                    case jsonSource:
                    sourcesInit = mapper.readValue(is, new TypeReference<List<Source>>() {});
                    break;
                    case jsonUser:
                    usersInit = mapper.readValue(is, new TypeReference<List<CreateUser>>() {});
                    break;
                default:
                    break;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        log.info("initial json data read and converted");
    }
}
