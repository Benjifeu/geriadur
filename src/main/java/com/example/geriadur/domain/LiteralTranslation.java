package com.example.geriadur.domain;

import com.example.geriadur.domain.EtymonName;
import com.example.geriadur.domain.SemanticField;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
     * 1: male person or god name
     * 2: female person or goddess name
     * 3: tribe name
     * 4: country or city name
     *
     */
    @Column(name = "lit_trans_type", nullable = false)
    private int litTransType;

    @OneToOne(mappedBy = "litTrans")
    private EtymonName etymonName;

}
