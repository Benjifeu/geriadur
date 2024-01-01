package com.example.geriadur.domain;

import com.example.geriadur.domain.consultation.Lexeme;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The etymon define the proto-celtic root world and all his descandant
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

    /**
     * linked proto-celtic lexeme
     */

    /*@ManyToMany(mappedBy = "etymonNames")*/
    @ManyToMany( fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    @JoinTable( name="lexeme_etymon",
            joinColumns = @JoinColumn(name = "etymon_id"),
            inverseJoinColumns = @JoinColumn(name = "lexeme_id")
    )
    private Map<Integer, Lexeme> lexemePc;

    @Column(name = "descr_fr")
    private String descrFr;

    @Column(name = "descr_eng")
    private String descrEng;

    /**
     * The wordTheme chosen for the gameSession
     * 1: places and countries
     * 2: historic figures
     * 3: mythic figures
     * 4: tribes and clans
     * 5: arms and other things
     */
    @Column(name = "word_theme")
    private Long wordTheme;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "sem_field_id")
    private SemanticField semanticField;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "lit_trans_id")
    private LiteralTranslation litTrans;

}
