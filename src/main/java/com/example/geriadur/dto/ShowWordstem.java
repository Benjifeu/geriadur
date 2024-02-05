package com.example.geriadur.dto;

import com.example.geriadur.entity.consultation.WordStem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ShowWordstem {
    List<WordStem> pageWordstems;
    int currentPage;
    int pageCount;
    int wordstemsCount;
}
