package com.example.geriadur.domain.consultation;

import com.example.geriadur.constants.GenderEnum;
import com.example.geriadur.constants.LanguageEnum;
import com.example.geriadur.constants.WordClassEnum;
import com.example.geriadur.domain.EtymonName;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "lexeme")
public class Lexeme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lexeme_id")
    private Long lexemeId;

    @Column(name = "lexeme_name", nullable = false)
    private String lexemeName;

    @Column(name = "lexeme_language",nullable = false)
    private LanguageEnum lexemeLanguage;

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


    @ManyToMany(mappedBy = "lexemes")
    private Set<Quote> quotes = new HashSet<>();


     @ManyToMany( fetch = FetchType.LAZY,
     cascade = {CascadeType.PERSIST,CascadeType.MERGE})
     @JoinTable( name="lexeme_parent",
     joinColumns = @JoinColumn(name = "child_id"),
     inverseJoinColumns = @JoinColumn(name = "parent_id"))
     private Set<Lexeme> parents = new HashSet<>();


    @ManyToMany( fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable( name="lexeme_child",
            joinColumns = @JoinColumn(name = "parent_id"),
            inverseJoinColumns = @JoinColumn(name = "child_id"))
    private Set<Lexeme> children = new HashSet<>();


    @ManyToMany (mappedBy = "lexemePc")
    private List<EtymonName> etymonNames = new ArrayList<>();
}
