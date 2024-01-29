package com.example.geriadur.entity.consultation;

import com.example.geriadur.constants.GenderEnum;
import com.example.geriadur.constants.LanguageEnum;
import com.example.geriadur.constants.WordClassEnum;
import com.example.geriadur.entity.EtymonName;
import com.example.geriadur.entity.SemanticField;
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
@Table(name = "wordStem")
public class WordStem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wordStem_id")
    private Long wordStemId;

    @Column(name = "wordStem_name", nullable = false)
    private String wordStemName;

    @Column(name = "wordStem_language",nullable = false)
    private LanguageEnum wordStemLanguage;

    @Column(name = "phonetic")
    private String phonetic;

    @Column(name = "gender")
    private GenderEnum gender;

    @Column(name = "wordClass")
    private WordClassEnum wordClass;

    @Column(name = "ref_words_eng")
    private String referenceWordsEng;

    @Column(name = "ref_words_fr",nullable = false)
    private String referenceWordsFr;

    @Column(name = "descr_fr")
    private String descrFr;

    @Column(name = "descr_eng")
    private String descrEng;

    @ManyToOne
    @JoinColumn(name = "sem_field_id")
    private SemanticField semanticField;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "wordStem_quote",
            joinColumns = @JoinColumn(name = "wordStem_id"),
            inverseJoinColumns = @JoinColumn(name = "quote_id"))
    private Set<Quote> quotes = new HashSet<>();

    @ManyToMany( fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE})
    @JoinTable( name="wordStem_source",
            joinColumns = @JoinColumn(name = "wordStem_id"),
            inverseJoinColumns = @JoinColumn(name = "source_id"))
    private Set<Source> sources = new HashSet<>();

     @ManyToMany( fetch = FetchType.LAZY,
     cascade = {CascadeType.MERGE})
     @JoinTable( name="wordStem_parent",
     joinColumns = @JoinColumn(name = "child_id"),
     inverseJoinColumns = @JoinColumn(name = "parent_id"))
     private Set<WordStem> parents = new HashSet<>();

/*
    @ManyToMany( fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable( name="wordStem_child",
            joinColumns = @JoinColumn(name = "parent_id"),
            inverseJoinColumns = @JoinColumn(name = "child_id"))
    private Set<WordStem> children = new HashSet<>();*/


    @ManyToMany (mappedBy = "wordStemPc")
    private List<EtymonName> etymonNames = new ArrayList<>();
}
