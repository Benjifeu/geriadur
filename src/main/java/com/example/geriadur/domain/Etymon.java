package com.example.geriadur.domain;

import com.example.geriadur.constants.GenderEnum;
import com.example.geriadur.constants.LanguageEnum;
import com.example.geriadur.constants.WordClassEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "etymon")
public class Etymon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "etymon_id")
    private Long etymonId;

    @Column(name = "etymon_name", nullable = false)
    private String etymonName;

    @Column(name = "etymon_language",nullable = false)
    private LanguageEnum etymonLanguage;

    @Column(name = "phonetic")
    private String phonetic;

    @Column(name = "gender")
    private GenderEnum gender;

    @Column(name = "wordClass")
    private WordClassEnum wordClass;

    @Column(name = "ref_words_eng",nullable = false)
    private String referenceWordsEng;

    @Column(name = "ref_words_fr")
    private String referenceWordsFr;

    @Column(name = "descr_fr")
    private String descrFr;

    @Column(name = "descr_eng")
    private String descrEng;

    @ManyToMany(
            mappedBy = "etymons"
    )
    private Set<SemanticField> semanticFields = new HashSet<>();

    @ManyToMany(
            mappedBy = "etymons"
    )
    private Set<Quote> quotes = new HashSet<>();


     @ManyToMany( fetch = FetchType.LAZY,
     cascade = {CascadeType.PERSIST,CascadeType.MERGE}
     )
     @JoinTable( name="etymon_parent",
     joinColumns = @JoinColumn(name = "child_id"),
     inverseJoinColumns = @JoinColumn(name = "parent_id")
     )
     private Set<Etymon> parents = new HashSet<>();


    @ManyToMany( fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE}
    )
    @JoinTable( name="etymon_child",
            joinColumns = @JoinColumn(name = "parent_id"),
            inverseJoinColumns = @JoinColumn(name = "child_id")
    )
    private Set<Etymon> children = new HashSet<>();
}
