package com.example.geriadur.domain.consultation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * The Quote Entity persist all the quotes used as references for a wordStem
 * each quote is linked with a litteral source referenced as entity Source
 *
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "quote")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quote_id")
    private Long quoteId;

/* 
    @Column(name = "quote_text", nullable = false)
    private String reference;*/

    @Column(name = "quote_text", nullable = false)
    private String quoteText;


    @ManyToOne(
            fetch = FetchType.LAZY)
    @JoinColumn(name = "source_id"
    )
    private Source source;


    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "quote_wordStem",
            joinColumns = @JoinColumn(name = "quote_id"),
            inverseJoinColumns = @JoinColumn(name = "wordStem_id"))
    private List<WordStem> wordStems = new ArrayList<>();
}
