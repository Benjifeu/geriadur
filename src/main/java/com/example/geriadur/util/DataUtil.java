package com.example.geriadur.util;

import com.example.geriadur.entity.SemanticField;
import com.example.geriadur.entity.consultation.Author;
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
 * IMPORTANT: this class is unused in a deployed context (the data is fetched
 * from a remote database)
 *
 * the DataUtil class is responsible to retrieve the data from the JSON files
 * present at the root of the project
 * * (wordStemsInit, , etymonsInit, semanticFieldinit, sourcesInit)
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

    // list of data transfer objects that will be persisted in the database
    private List<CreateWordStem> wordStemsInit = new ArrayList<>();
    private List<Author> authorsInit = new ArrayList<>();
    private List<CreateEtymo> etymonNamesInit = new ArrayList<>();
    private List<CreateSource> sourcesInit = new ArrayList<>();
    private List<SemanticField> semanticFieldsInit = new ArrayList<>();
    private List<CreateUser> usersInit = new ArrayList<>();

    // names of the the json files to retrieve
    private final String jsonAuthors = "authorsInit";
    private final String jsonWordStem = "wordStemsInit";
    private final String jsonSource = "sourcesInit";
    private final String jsonEtymon = "etymonsInit";
    private final String jsonSemField = "semanticFieldsInit";
    private final String jsonUser = "usersInit";

    /**
     * InjectionData() the data in order to be transformed as entities and then
     * persisted in the mySql database
     **/
    // @PostConstruct
    public void InjectionData() throws IOException {
        readJsonData(jsonAuthors);
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
        for (Author author : authorsInit) {
            sourceService.addAuthor(author);
        }

        // save all the sources
        for (CreateSource source : sourcesInit) {
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
            is = new FileInputStream("src/main/java/com/example/geriadur/util/dataInit/" + jsonName + ".json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            switch (jsonName) {
                case jsonWordStem:
                    wordStemsInit = mapper.readValue(is, new TypeReference<List<CreateWordStem>>() {
                    });
                    log.info("wordStemsInit data read and converted");
                    break;
                case jsonAuthors:
                    authorsInit = mapper.readValue(is, new TypeReference<List<Author>>() {
                    });
                    log.info("authorsInit data read and converted");
                    break;
                case jsonEtymon:
                    etymonNamesInit = mapper.readValue(is, new TypeReference<List<CreateEtymo>>() {
                    });
                    log.info("etymonNamesInit data read and converted");
                    break;
                case jsonSemField:
                    semanticFieldsInit = mapper.readValue(is, new TypeReference<List<SemanticField>>() {
                    });
                    log.info("semanticFieldsInit data read and converted");
                    break;
                case jsonSource:
                    sourcesInit = mapper.readValue(is, new TypeReference<List<CreateSource>>() {
                    });
                    log.info("sourcesInit data read and converted");
                    break;
                case jsonUser:
                    usersInit = mapper.readValue(is, new TypeReference<List<CreateUser>>() {
                    });
                    log.info("usersInit data read and converted");
                    break;
                default:
                    break;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
