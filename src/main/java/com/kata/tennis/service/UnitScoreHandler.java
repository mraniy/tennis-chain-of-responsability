package com.kata.tennis.service;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;
import lombok.Data;

@Data
public abstract class UnitScoreHandler {

    protected UnitScoreHandler next;

    public abstract Match refreshScore(Match match, Player player);

    public void proceed(Match match, String pointWinner) {
        if (pointWinner.equals(match.getPlayer1().getName()))
            refreshScore(match, match.getPlayer1());
        else {
            refreshScore(match, match.getPlayer2());
        }
        if (next != null) {
            next.proceed(match, pointWinner);
        }

    }
}
