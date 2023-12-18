package com.example.geriadur.service.game;

import com.example.geriadur.domain.EtymonName;
import com.example.geriadur.dto.LiteralTranslation;

import java.util.List;
import java.util.Set;

public interface SessionGameService {
    Set<LiteralTranslation> get4LiteralTranslationWithoutSpecified(LiteralTranslation currentLitTrans);

    Set<LiteralTranslation> getAllLiteralTranslationBySemField(long semanticFieldId, int language);

    List<EtymonName> get15RandomEtymonName(long semanticFieldId);

    EtymonName getNextEtymonName();

}
