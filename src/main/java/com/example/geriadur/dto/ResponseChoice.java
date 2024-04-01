package com.example.geriadur.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class ResponseChoice {
    String responseChoice;
    boolean correctness;
}
