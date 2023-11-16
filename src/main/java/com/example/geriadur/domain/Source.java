package com.example.geriadur.domain;

import com.example.geriadur.constants.LanguageEnum;
import com.example.geriadur.constants.TypeOfSourceEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Source {
    @Id
    String quoteId;
    @Column(nullable = false)
    String sourceNameInEnglish;
    @Column(nullable = true)
    String sourceNameInOriginalLanguage;
    @Column(nullable = false)
    TypeOfSourceEnum typeOfSource;
    @Column(nullable = false)
    LocalDate dateOfPublication;
    @Column(nullable = false)
    LanguageEnum language;
    @ManyToMany(
            mappedBy = "sources"
    )
    List<Author> authors = new ArrayList<>();

}
