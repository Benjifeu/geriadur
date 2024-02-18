package com.example.geriadur.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ShowUser {
    String userName;
    String monthAndYearJoined;
    List<Integer> scoreByTheme;

}
