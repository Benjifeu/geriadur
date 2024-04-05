package com.example.geriadur.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lit_trans")
public class LiteralTranslation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="lit_trans_id")
    private Long litTransId;

    @Column(name = "lit_trans_eng")
    private String litTransEng;

    @Column(name = "lit_trans_fr", nullable = false)
    private String litTransFr;

    /**The type of literal transcription refer to the nature of the proper noun
     * 1: male person or god name (singular)
     * 2: female person or goddess name (singular)
     * 3: tribe or people name (plural)
     * 4: country name (singular)
     * 5: country name (plural)
     * 6: city name (singular)
     * 7: river name (singular)
     * 8: god and heroes name
     * 9: goddess name
     * 10: object
     */
    @Column(name = "lit_trans_type", nullable = false)
    private int litTransType;

    @OneToOne(mappedBy = "litTrans")
    private ProperNoun etymonName;

}
