package com.example.geriadur.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ShowUser {
    String userName;
    String monthAndYearJoined;
    /**
     * corresponding indexes
     * 0: places and countries
     * 1: historic figures
     * 2: mythic figures
     * 3: tribes and clans
     * 4: arms and other things
     */
    List<Integer> scoreByTheme;

}
