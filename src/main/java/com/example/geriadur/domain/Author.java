package com.example.geriadur.domain;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Author {

    @Id
    String authorId;
    @Column(nullable = false)
    String authorName;
    @Column(nullable = false)
    String biography;
    @Column(nullable = false)
    LocalDate dateOfBirth;
    @Column(nullable = false)
    LocalDate dateOfDeath;
    @Column(nullable = false)
    String nationality;
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE}
    )
    @JoinTable(
            name="author_source",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "etymon")
    )
    private List<Source> sources = new ArrayList<>();
}
