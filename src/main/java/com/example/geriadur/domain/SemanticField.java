package com.example.geriadur.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "semanticField")
public class SemanticField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "semanticfield_id")
    private Long semanticFieldId;

    @Column(name = "semanticfield_nameFr", nullable = false)
    private String semanticFieldNameFr;

    @Column(name = "semanticfield_nameEng", nullable = false)
    private String semanticFieldNameEng;

    @Column(name = "semanticfield_size")
    private int semanticFieldSize;


    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH}
    )
    @JoinTable(
            name = "semanticField_Etymon",
            joinColumns = @JoinColumn(name = "semanticfield_id"),
            inverseJoinColumns = @JoinColumn(name = "etymon_id")
    )
    private Set<Etymon> etymons = new HashSet<>();
}
