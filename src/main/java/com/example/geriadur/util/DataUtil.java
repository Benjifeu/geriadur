package com.example.geriadur.util;

import com.example.geriadur.domain.EtymonName;
import com.example.geriadur.domain.LiteralTranslation;
import com.example.geriadur.domain.consultation.Lexeme;
import com.example.geriadur.domain.SemanticField;
import com.example.geriadur.domain.consultation.Quote;
import com.example.geriadur.domain.consultation.Source;
import com.example.geriadur.dto.UserRegistrationDto;
import com.example.geriadur.repositories.*;
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
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class DataUtil {

    @Autowired
    private LexemeRepository lexemeRepository;
    @Autowired
    private SemanticFieldRepository semanticFieldRepository;
    @Autowired
    private SourceRepository sourceRepository;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private EtymonNameRepository etymonNameRepository;
    @Autowired
    private LiteralTranslationRepository literalTranslationRepository;
    @Autowired
    private QuoteRepository quoteRepository;
    private List<Lexeme> lexemesInit = new ArrayList<>();
    private List<EtymonName> etymonNamesInit = new ArrayList<>();
    private List<Source> sourcesInit = new ArrayList<>();
    private List<Quote> quotesInit = new ArrayList<>();
    @PostConstruct
    public void InjectionData() throws IOException {
        readJsonData("lexemesInit");
        readJsonData("sourcesInit");
        readJsonData("etymonsInit");
        readJsonData("quotesInit");
        lexemesInit.get(1).setChildren(Stream.of(lexemesInit.get(2), lexemesInit.get(3)).collect(Collectors.toSet()));
        lexemesInit.get(6).setParents(Stream.of(lexemesInit.get(4), lexemesInit.get(0)).collect(Collectors.toSet()));
        SemanticField semanticField = new SemanticField();
        semanticField.setSemanticFieldNameEng("battlefield");
        semanticField.setSemanticFieldNameFr("militaire");
        SemanticField semanticField2 = new SemanticField();
        semanticField2.setSemanticFieldNameEng("family");
        semanticField2.setSemanticFieldNameFr("famille");
        SemanticField semanticField3 = new SemanticField();
        semanticField3.setSemanticFieldNameEng("working");
        semanticField3.setSemanticFieldNameFr("travail");
        SemanticField semanticField4 = new SemanticField();
        semanticField4.setSemanticFieldNameEng("religion");
        semanticField4.setSemanticFieldNameFr("religion");
        ArrayList<LiteralTranslation> literalTranslations = new ArrayList<>();
        //ArrayList<EtymonName> etymonNames = new ArrayList<>();
        for (EtymonName etymonName : etymonNamesInit) {
            literalTranslations.add(etymonName.getLitTrans());
            etymonName.setSemanticField(semanticField);
        }
        lexemeRepository.saveAll(lexemesInit);
        literalTranslationRepository.saveAll(literalTranslations);
        semanticFieldRepository.save(semanticField);
        semanticFieldRepository.save(semanticField2);
        semanticFieldRepository.save(semanticField3);
        semanticFieldRepository.save(semanticField4);
        etymonNameRepository.saveAll(etymonNamesInit);
        sourceRepository.saveAll(sourcesInit);
        quoteRepository.saveAll(quotesInit);
        setLexemeQuoteLink(quoteRepository.findByQuoteId(1L).get(), "bailh");
        setLexemeQuoteLink(quoteRepository.findByQuoteId(2L).get(), "seno");
        setSourceQuoteLink(quoteRepository.findByQuoteId(1L).get(), 2L);//Dictionaire Rostrenen
        setSourceQuoteLink(quoteRepository.findByQuoteId(2L).get(), 6L);//Dictionnaire gaulois delamarre
        setLexemeEtymonLink(etymonNameRepository.findEtymonNameByEtymonId(1L).get(), Arrays.asList("uer", "kinget", "rix"));
        setLexemeEtymonLink(etymonNameRepository.findEtymonNameByEtymonId(2L).get(), Arrays.asList("kara", "uos", "os"));
        setLexemeEtymonLink(etymonNameRepository.findEtymonNameByEtymonId(3L).get(), Arrays.asList("kom", "bhiyo", "os"));
        setLexemeEtymonLink(etymonNameRepository.findEtymonNameByEtymonId(4L).get(), Arrays.asList("aku", "kuno"));
        setLexemeEtymonLink(etymonNameRepository.findEtymonNameByEtymonId(5L).get(), Arrays.asList("kuno", "belin", "os"));
        setLexemeEtymonLink(etymonNameRepository.findEtymonNameByEtymonId(6L).get(), Arrays.asList("talu", "iestin"));
        setLexemeEtymonLink(etymonNameRepository.findEtymonNameByEtymonId(7L).get(), Arrays.asList("bran", "os"));
        setLexemeEtymonLink(etymonNameRepository.findEtymonNameByEtymonId(8L).get(), Arrays.asList("ambi", "catu", "os"));
        setLexemeEtymonLink(etymonNameRepository.findEtymonNameByEtymonId(9L).get(), Arrays.asList("ambio", "rix"));
        setLexemeEtymonLink(etymonNameRepository.findEtymonNameByEtymonId(10L).get(), Arrays.asList("uer", "tigern"));
        setLexemeEtymonLink(etymonNameRepository.findEtymonNameByEtymonId(11L).get(), Arrays.asList("argntom", "onios"));
        setLexemeEtymonLink(etymonNameRepository.findEtymonNameByEtymonId(12L).get(), Arrays.asList("ari", "uid"));
        setLexemeEtymonLink(etymonNameRepository.findEtymonNameByEtymonId(13L).get(), Arrays.asList("kuno", "maros"));
        setLexemeEtymonLink(etymonNameRepository.findEtymonNameByEtymonId(14L).get(), Arrays.asList("teuta", "ualos"));
        setLexemeEtymonLink(etymonNameRepository.findEtymonNameByEtymonId(15L).get(), Arrays.asList("korios", "soldurio"));
        setLexemeEtymonLink(etymonNameRepository.findEtymonNameByEtymonId(16L).get(), Arrays.asList("ostimios"));
        setLexemeEtymonLink(etymonNameRepository.findEtymonNameByEtymonId(17L).get(), Arrays.asList("ad", "treb", "tegos"));
        setLexemeEtymonLink(etymonNameRepository.findEtymonNameByEtymonId(18L).get(), Arrays.asList("karnon"));
        setLexemeEtymonLink(etymonNameRepository.findEtymonNameByEtymonId(19L).get(), Arrays.asList("belin", "uak"));
        setLexemeEtymonLink(etymonNameRepository.findEtymonNameByEtymonId(20L).get(), Arrays.asList("keno", "manos"));
        setLexemeEtymonLink(etymonNameRepository.findEtymonNameByEtymonId(21L).get(), Arrays.asList("leng"));
        setLexemeEtymonLink(etymonNameRepository.findEtymonNameByEtymonId(22L).get(), Arrays.asList("sec","gwanu"));
        setLexemeEtymonLink(etymonNameRepository.findEtymonNameByEtymonId(23L).get(), Arrays.asList("allo","brog"));
        setLexemeEtymonLink(etymonNameRepository.findEtymonNameByEtymonId(24L).get(), Arrays.asList("ari","uerno"));
        setLexemeEtymonLink(etymonNameRepository.findEtymonNameByEtymonId(25L).get(), Arrays.asList("ambi","ianos"));
        userService.save(new UserRegistrationDto("UserAccount", "lastname", "email", "pass", "U", 1));
        userService.save(new UserRegistrationDto("Admin", "lastname", "emailAdmin", "pass", "A", 1));
    }

    private void setSourceQuoteLink(Quote quote, long l) {
        quote.setSource(sourceRepository.getReferenceById(l));
        quoteRepository.save(quote);
    }

    public void setLexemeQuoteLink(Quote quote, String lexemeStr) {
            Optional<Lexeme> lexeme = lexemeRepository.findByLexemeName(lexemeStr);
            if (lexeme.isPresent()) {
                quote.setLexemes(Arrays.asList(lexeme.get()));
                quoteRepository.save(quote);
            } else throw new IllegalArgumentException("the lexeme: " + lexemeStr  + " doesn't exist in DB.");
        }
    public void setLexemeEtymonLink(EtymonName etymonName, List<String> lexemesString) {
        Map<Integer, Lexeme> lexemes = new HashMap<>();
        for (int i = 0; i < lexemesString.size(); ++i) {
            Optional<Lexeme> lexeme = lexemeRepository.findByLexemeName(lexemesString.get(i));
            if (lexeme.isPresent()) {
                lexemes.put(i, lexeme.get());
            } else throw new IllegalArgumentException("the lexeme: " + lexemesString.get(i) + " doesn't exist in DB.");
        }
        etymonName.setLexemePc(lexemes);
        etymonNameRepository.save(etymonName);
    }


    void readJsonData(String jsonName) throws IOException {

        JsonNode jsonNode = null;
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = new FileInputStream("src/main/java/com/example/geriadur/" + jsonName + ".json");
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
        if (jsonName.equals("lexemesInit")) {
            lexemesInit = mapper.convertValue(jsonNode, new TypeReference<>() {
            });
        } else if (jsonName.equals("sourcesInit")) {
            sourcesInit = mapper.convertValue(jsonNode, new TypeReference<>() {
            });
        } else if (jsonName.equals("etymonsInit")) {
            etymonNamesInit = mapper.convertValue(jsonNode, new TypeReference<>() {
            });
        } else if (jsonName.equals("quotesInit")) {
            quotesInit = mapper.convertValue(jsonNode, new TypeReference<>() {
            });
        }

    }

}
