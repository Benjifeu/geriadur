package com.example.geriadur.service.game;

import com.example.geriadur.domain.EtymonName;
import com.example.geriadur.domain.LiteralTranslation;
import com.example.geriadur.dto.GameSessionStep;

import java.util.List;
import java.util.Set;

public interface SessionGameService {
    //boolean verifyResponse(LiteralTranslation response);
    List<GameSessionStep> get15GameSessionStep(long semanticFieldId);
}
