package com.kata.tennis.model;

public enum EnumPoint {

    LOVE ("00"),
    FIFTEEN ("15"),
    THIRTY ("30"),
    FORTY ("40"),
    ADVANTAGE("AD");

    private String score = "";

    //Constructeur
    EnumPoint(String score){
        this.score = score;
    }


    public String getScore() {
        return score;
    }
}
