package com.kata.tennis.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Score {

    private ScorePlayer scorePlayer1;

    private ScorePlayer scorePlayer2;


}
