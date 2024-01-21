package com.example.geriadur.domain.consultation;

import com.example.geriadur.constants.LanguageEnum;
import com.example.geriadur.constants.TypeOfSourceEnum;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "source")
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "source_id")
    private Long sourceId;

    @Column(name = "abbreviation", nullable = false)
    private String abbreviation;

    @Column(name = "source_name_english", nullable = false)
    private String sourceNameInEnglish;

    @Column(name = "source_name_original")
    private String sourceNameInOriginalLanguage;

    @Column(name = "type_source", nullable = false)
    private TypeOfSourceEnum typeOfSource;

    @Column(name = "date_publication", nullable = false)
    private int dateOfPublication;

    @Column(name = "language", nullable = false)
    private LanguageEnum language;

    @Column(name = "description", length = 100000)
    private String description;


    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE}
    )
    @JoinTable(
            name="source_author",
            joinColumns = @JoinColumn(name = "source_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();

    @OneToMany(mappedBy="source")
    private Set<Quote> quotes = new HashSet<>();

    @ManyToMany(mappedBy = "sources")
    private List<WordStem> wordStems = new ArrayList<>();
}
