package com.example.geriadur.entity;

import com.example.geriadur.entity.consultation.WordStem;
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
@Table(name = "semanticField")
public class SemanticField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sem_field_id")
    private Long semanticFieldId;

    @Column(name = "sem_field_nameFr", nullable = false)
    private String semFieldNameFr;

    @Column(name = "sem_field_nameEng", nullable = false)
    private String semFieldNameEng;


    @OneToMany(mappedBy = "semanticField"
    )
    private Set<WordStem> wordStems = new HashSet<>();
}
