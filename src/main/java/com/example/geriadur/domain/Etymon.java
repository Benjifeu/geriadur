package com.example.geriadur.domain;

import com.example.geriadur.constants.LanguageEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
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

    @Column(nullable = false)
    private LanguageEnum etymonLanguage;

    @Column(name = "etymon_name", nullable = false)
    private String etymonName;
/**
     @ManyToMany( fetch = FetchType.LAZY,
     cascade = {CascadeType.PERSIST,CascadeType.MERGE}
     )
     @JoinTable( name="etymon_etymon",
     joinColumns = @JoinColumn(name = "etymon_id"),
     inverseJoinColumns = @JoinColumn(name = "etymon_id")
     )
     private List<Etymon> etymons = new ArrayList<>();
**/
    @Column(name = "phonetic")
    String phonetic;

    @Column(name = "gender")
    char gender;

    @Column(name = "wordClass")
    String wordClass;

    @Column(name = "description")
    String description;

    @ManyToMany(
            mappedBy = "etymons"
    )
    private List<SemanticField> semanticFields = new ArrayList<>();

    @ManyToMany(
            mappedBy = "etymons"
    )
    private List<ReferenceWord> referenceWords = new ArrayList<>();

    @ManyToMany(
            mappedBy = "etymons"
    )
    private List<Quote> quotes = new ArrayList<>();
}
