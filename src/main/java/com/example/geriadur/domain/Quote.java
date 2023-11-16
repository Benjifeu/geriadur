package com.example.geriadur.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class Quote {
    @Id
    String quoteId;
    @Column(nullable = false)
    String quote;
    @ManyToOne
    Source source;
}
