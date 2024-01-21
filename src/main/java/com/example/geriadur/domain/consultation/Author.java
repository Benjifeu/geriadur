package com.example.geriadur.domain.consultation;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

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

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="author_birthdate")
    private Date dateOfBirth;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name="author_deathdate")
    private Date dateOfDeath;

    @Column(name="author_nationality",nullable = false)
    private String nationality;

    @ManyToMany(mappedBy = "authors")
    private Set<Source> sources = new HashSet<>();
}
