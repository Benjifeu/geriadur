package com.example.geriadur.constants;

public enum TypeOfSourceEnum {
    N("Novel"),D("Dictionary"),E("Epic"),P("Poetry"),T("Theatre"),G("Gloss"),I("Inscription"),M("Magazine");
    private String typeOfSource;
    TypeOfSourceEnum(String type) {
        this.typeOfSource =type;
    }

    public String getTypeOfSource() {
        return typeOfSource;
    }
}
