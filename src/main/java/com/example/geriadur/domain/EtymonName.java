package com.example.geriadur.domain;

import com.example.geriadur.domain.consultation.Lexeme;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**The etymon define the proto-celtic root world and all his descandant
 *
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "etymon_name")
public class EtymonName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "etymon_id")
    private Long etymonId;

    @Column(name = "current_name", nullable = false)
    private String currentName;

    @Column(name = "etymo_name", nullable = false)
    private String etymoName;

    @Column(name = "response_fr", nullable = false, unique = true)
    private String responseFr;

    @Column(name = "response_eng")
    private String responseEng;

    /** linked proto-celtic lexeme*/
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "lexeme_id")
    private Set<Lexeme> lexemePc;

    @Column(name = "descr_fr")
    private String descrFr;

    @Column(name = "descr_eng")
    private String descrEng;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sem_field_id")
    private SemanticField semanticField;

}
