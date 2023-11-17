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
    @Column(name = "semanticfield_id")
    private Long semanticFieldId;

    @Column(name = "semanticfield_name")
    private String semanticFieldName;

    @Column(name = "semanticfield_size")
    private int semanticFieldSize;


    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name = "semanticField_Etymon",
            joinColumns = @JoinColumn(name = "semanticfield_id"),
            inverseJoinColumns = @JoinColumn(name = "etymon_id")
    )
    private List<Etymon> etymons = new ArrayList<>();
}
