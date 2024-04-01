package com.example.geriadur.dto;

import com.example.geriadur.entity.consultation.Source;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ShowSourcesPage {
    List<Source> pageSources;
    int currentPage;
    int pageCount;
    int wordstemsCount;
}
