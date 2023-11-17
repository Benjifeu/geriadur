package com.example.geriadur.domain;

import com.example.geriadur.constants.LanguageEnum;
import com.example.geriadur.constants.TypeOfSourceEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "source")
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="source_id")
    private Long sourceId;

    @Column(name = "source_name_english", nullable = false)
    private String sourceNameInEnglish;

    @Column(name = "source_name_original",nullable = true)
    private String sourceNameInOriginalLanguage;

    @Column(name = "type_source",nullable = false)
    private TypeOfSourceEnum typeOfSource;

    @Column(name = "date_publication",nullable = false)
    private LocalDate dateOfPublication;

    @Column(name = "language",nullable = false)
    private LanguageEnum language;

    @ManyToMany(
            mappedBy = "sources"
    )
    private List<Author> authors = new ArrayList<>();

}
