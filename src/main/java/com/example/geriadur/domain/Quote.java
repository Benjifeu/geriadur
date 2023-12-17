package com.example.geriadur.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "quote_text", nullable = false)
    private String quoteText;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false)
    @JoinColumn(name = "source_id", nullable = false
    )
    private Source source;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name = "quote_etymon",
            joinColumns = @JoinColumn(name = "quote_id"),
            inverseJoinColumns = @JoinColumn(name = "etymon_id")
    )
    private List<Etymon> etymons = new ArrayList<>();
}
