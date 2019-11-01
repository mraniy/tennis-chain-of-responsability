package com.kata.tennis.service;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;

import java.util.Optional;

public class MatchHandler extends UnitScoreHandler {
    public MatchHandler() {
        next = Optional.empty();
    }

    @Override
    public Match refreshScore(Match match, Player player) {
        setPlayer1AsWinnerIfHeWinsTheTie(match, player);
        setPlayer2AsWinnerIfHeWinsTheTie(match, player);
        return match;
    }

    private void setPlayer2AsWinnerIfHeWinsTheTie(Match match, Player player) {
        Optional.ofNullable(match)
                .filter(match1 -> match1.getPlayer2().getName().equals(player.getName()))
                .filter(match1 -> match1.getScore().getScorePlayer2().getNumberSetWonByPlayer() ==2)
                .ifPresent(match1 -> match1.setWinner(Optional.of(player.getName())));
    }

    private void setPlayer1AsWinnerIfHeWinsTheTie(Match match, Player player) {
        Optional.ofNullable(match)
                .filter(match1 -> match1.getPlayer1().getName().equals(player.getName()))
                .filter(match1 -> match1.getScore().getScorePlayer1().getNumberSetWonByPlayer()==2)
                .ifPresent(match1 -> match1.setWinner(Optional.of(player.getName())));
    }
}
