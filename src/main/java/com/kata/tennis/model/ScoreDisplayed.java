package com.kata.tennis.model;

import lombok.Data;

import java.util.List;

@Data
public class ScoreDisplayed {

    private List<Integer> gamesWonByPlayer1;

    private List<Integer> gamesWonByPlayer2;

    private List<Integer> setsWonByPlayer1;

    private List<Integer> setsWonByPlayer2;

    private String pointsWonByPlayer1;

    private String pointsWonByPlayer2;

}
