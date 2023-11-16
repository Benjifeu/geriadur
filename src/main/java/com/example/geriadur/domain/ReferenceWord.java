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
    private int id;

    @Column(name="name")
    String word;
    @Column(name="language")
    LanguageEnum language;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE}
    )
    @JoinTable(
            name="referceWord_Etymon",
            joinColumns = @JoinColumn(name = "refWord"),
            inverseJoinColumns = @JoinColumn(name = "etymon")
    )
    private List<Etymon> etymons = new ArrayList<>();

}
