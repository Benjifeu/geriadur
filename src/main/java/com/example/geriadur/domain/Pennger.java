package com.example.geriadur.domain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Pennger")
public class Pennger {

    @Id
    String accountIban;
    @Column(nullable = false)
    String accountName;
    @Column(nullable = false)
    String email;
}
