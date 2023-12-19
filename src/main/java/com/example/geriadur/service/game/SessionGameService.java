package com.example.geriadur.service.game;

import com.example.geriadur.domain.EtymonName;
import com.example.geriadur.dto.LiteralTranslation;

import java.util.List;
import java.util.Set;

public interface SessionGameService {
    Set<LiteralTranslation> get5responseChoices(int gamestep);
    List<EtymonName> get15RandomEtymonName();
    boolean verifyResponse(int step, LiteralTranslation response);
    int getScore();
}
