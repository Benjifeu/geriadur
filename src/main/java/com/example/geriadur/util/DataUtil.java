package com.example.geriadur.util;

import com.example.geriadur.domain.Etymon;
import com.example.geriadur.repositories.EtymonRepository;
import com.example.geriadur.service.EtymonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DataUtil {
    @Autowired
    private EtymonRepository etymonRepository;

    private List<Etymon> etymonsInit = new ArrayList<>();
//@Value("${jsonFileName}")String jsonfile
    void etymonUtil() throws IOException {

        JsonNode jsonNode = null;
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = new FileInputStream("src/main/java/com/example/geriadur/etymonsInit.json");
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
        etymonsInit = mapper.convertValue(jsonNode, new TypeReference<List<Etymon>>() {
        });
    }

    @PostConstruct
    public void InjectionData() throws IOException {
        etymonUtil();
        etymonRepository.saveAll(etymonsInit);
    }
}
