package com.kata.tennis.service;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;
import lombok.Data;

import java.util.Optional;

@Data
public abstract class UnitScoreHandler {

    protected Optional<UnitScoreHandler> next;

    public abstract void refreshScore(Match match, Player player);

    public void proceed(Match match, String pointWinner) {
        if (pointWinner.equals(match.getPlayer1().getName()))
            refreshScore(match, match.getPlayer1());
        else {
            refreshScore(match, match.getPlayer2());
        }
        next.ifPresent(unitScoreHandler -> unitScoreHandler.proceed(match,pointWinner));
    }
}
