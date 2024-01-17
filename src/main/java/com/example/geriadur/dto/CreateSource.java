package com.example.geriadur.dto;

import lombok.Getter;
import lombok.Setter;

//entry for the registration of a new text source in the DB
@Getter
@Setter
public class CreateSource {
    String sourceName;
    String date;
    String description;
    String language;
}
