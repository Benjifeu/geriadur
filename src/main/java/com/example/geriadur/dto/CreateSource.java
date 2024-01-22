package com.example.geriadur.dto;

import com.example.geriadur.constants.LanguageEnum;
import com.example.geriadur.constants.TypeOfSourceEnum;
import lombok.Getter;
import lombok.Setter;

/**entry for the registration of a new text source in the DB
 *
 */
@Getter
@Setter
public class CreateSource {
    String abbreviation;
    String sourceNameInOriginalLanguage;
    String sourceNameInEnglish;
    String authors;
    TypeOfSourceEnum typeOfSource;
    int dateOfPublication;
    LanguageEnum language;
    String description;
}
