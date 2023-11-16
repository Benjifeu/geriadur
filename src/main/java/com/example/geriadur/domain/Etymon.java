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
    private int etymonId;

    @Column(nullable = false)
    private LanguageEnum languageEnum;

    @Column(nullable = false)
    private String etymon;

    @ManyToMany(
            mappedBy = "etymons"
    )
    private List<SemanticField> semanticFields = new ArrayList<>();

    @ManyToMany(
            mappedBy = "etymons"
    )
    private List<ReferenceWord> referenceWords = new ArrayList<>();
}
