package com.example.geriadur.entity;

import com.example.geriadur.entity.consultation.WordStem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

/**
 * The ProperNoun define a proper noun that have celtic etymological origins
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "propernoun")
public class ProperNoun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "propernoun_id")
    private Long propernounId;

    @Column(name = "current_name", nullable = false)
    private String currentName;

    @Column(name = "etymo_name", nullable = false)
    private String etymoName;


    @Column(name = "image", nullable = true)
    private String image;

    /**
     * linked proto-celtic wordStem
     */

    /*@ManyToMany(mappedBy = "etymonNames")*/
    @ManyToMany( fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    @JoinTable( name="word_stem_propernoun",
            joinColumns = @JoinColumn(name = "propernoun_id"),
            inverseJoinColumns = @JoinColumn(name = "wordStem_id")
    )
    private Map<Integer, WordStem> wordStemPc;

    @Column(name = "descr_fr", length =3000)
    private String descrFr;

    @Column(name = "descr_eng", length =3000)
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


    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "lit_trans_id")
    private LiteralTranslation litTrans;

}
