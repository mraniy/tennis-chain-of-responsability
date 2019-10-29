package com.kata.tennis.model;

import lombok.Data;

@Data
public class Match {

    private Player player1;

    private Player player2;

    private Score score;

    private String winner;

    private Integer setNumber = 1;

    public Match(Player player1, Player player2, Score score) {
        this.player1 = player1;
        this.player2 = player2;
        this.score = score;
    }


}
