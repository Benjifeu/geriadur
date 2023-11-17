package com.example.geriadur.domain;

import com.example.geriadur.constants.LanguageEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "refWord")
public class ReferenceWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="refword_id")
    private Long id;

    @Column(name="ref_word_name")
    String refWordName;

    @Column(name="ref_word_language")
    LanguageEnum refWordNameLanguage;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE}
    )
    @JoinTable(
            name="referceWord_Etymon",
            joinColumns = @JoinColumn(name = "refWord_id"),
            inverseJoinColumns = @JoinColumn(name = "etymon_id")
    )
    private List<Etymon> etymons = new ArrayList<>();

}
