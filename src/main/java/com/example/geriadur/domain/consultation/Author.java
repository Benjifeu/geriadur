package com.example.geriadur.domain.consultation;

import com.example.geriadur.domain.consultation.Source;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="author_id")
    private Long authorId;

    @Column(name="author_name",nullable = false)
    private String authorName;

    @Column(name="biography")
    private String biography;

    @Column(name="author_birthdate")
    private LocalDate dateOfBirth;

    @Column(name="author_deathdate")
    private LocalDate dateOfDeath;

    @Column(name="author_nationality",nullable = false)
    private String nationality;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    @JoinTable(
            name="author_source",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "source_id")
    )
    private List<Source> sources = new ArrayList<>();
}
