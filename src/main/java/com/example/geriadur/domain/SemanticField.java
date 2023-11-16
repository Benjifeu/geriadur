package com.example.geriadur.domain;

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
@Table(name = "semanticField")
public class SemanticField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int semanticFieldId;

    @Column
    String semanticFieldName;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE}
    )
    @JoinTable(
            name="semanticField_Etymon",
            joinColumns = @JoinColumn(name = "refWord"),
            inverseJoinColumns = @JoinColumn(name = "etymon")
    )
    private List<Etymon> etymons = new ArrayList<>();
}
