package com.example.geriadur.domain.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    private String email;

    private String password;

    @Column(name = "score",columnDefinition = "integer default 0", nullable = false)
    private int score;

    /**language value: 1=french, 2=english, 3=breton*/
    @Column(name = "language",columnDefinition = "integer default 1", nullable = false)
    private int language;



//the name of the role should always be preceded of the term "ROLE_" + the name of the role, ex: "ROLE_USER
    private String role;

    public User(String firstName, String lastName, String email, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
