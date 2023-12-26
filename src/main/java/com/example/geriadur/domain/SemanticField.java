package com.example.geriadur.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private String semanticFieldNameFr;

    @Column(name = "sem_field_nameEng", nullable = false)
    private String semanticFieldNameEng;

    @Column(name = "sem_field_size")
    private int semanticFieldSize;


    @OneToMany(mappedBy = "semanticField"
    )
    private Set<EtymonName> etymonNames = new HashSet<>();
}
