package com.example.geriadur.entity.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "registration_date", nullable = false)
    private Date registrationDate;

    /**
     * language value: 1=french, 2=english, 3=breton
     */
    @Column(name = "language", columnDefinition = "integer default 1", nullable = false)
    private int language;

    /**
     * language value: 1=french, 2=english, 3=breton
     */
    @Column(name = "score_places", columnDefinition = "integer default 0", nullable = false)
    private int scorePlaces;
    @Column(name = "score_h_figures", columnDefinition = "integer default 0", nullable = false)
    private int scoreHfigures;
    @Column(name = "score_m_figures", columnDefinition = "integer default 0", nullable = false)
    private int scoreMfigures;
    @Column(name = "score_tribes", columnDefinition = "integer default 0", nullable = false)
    private int scoreTribes;
    @Column(name = "score_Objects", columnDefinition = "integer default 0", nullable = false)
    private int scoreObjects;



    /**
     * the name of the role should always be preceded of the term "ROLE_" + the name of the role, ex: "ROLE_USER
     */
    private String role;

    public UserAccount(String firstName, String lastName, String email, int language, String password, String role, Date date) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.language = language;
        this.password = password;
        this.role = role;
        this.registrationDate = date;
    }
}
