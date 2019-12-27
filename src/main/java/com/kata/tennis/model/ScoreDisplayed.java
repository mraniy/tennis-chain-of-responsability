package com.kata.tennis.model;

import lombok.Data;

import java.util.LinkedList;

@Data
public class ScoreDisplayed {

    private LinkedList<GamesAndMaybeTieBreakPoints> gamesWonByPlayer1;

    private LinkedList<GamesAndMaybeTieBreakPoints> gamesWonByPlayer2;

    private LinkedList<Integer> setsWonByPlayer1;

    private LinkedList<Integer> setsWonByPlayer2;

    private String pointsWonByPlayer1;

    private String pointsWonByPlayer2;


}
