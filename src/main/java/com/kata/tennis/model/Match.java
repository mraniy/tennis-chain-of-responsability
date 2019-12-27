package com.kata.tennis.model;

import lombok.Data;

import java.util.Optional;

@Data
public class Match {

    private Player player1;

    private Player player2;

    private Optional<String> winner = Optional.empty();

    private Integer setNumber = 1;

    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }


}
