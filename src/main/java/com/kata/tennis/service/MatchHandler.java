package com.kata.tennis.service;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;

import java.util.Optional;

public class MatchHandler extends UnitScoreHandler {

    @Override
    public Match refreshScore(Match match, Player player) {
        Optional.ofNullable(match)
                .filter(match1 -> match1.getPlayer1().getName().equals(player.getName()))
                .filter(match1 -> match1.getScore().getScorePlayer1().getNumberSetWonByPlayer()==2)
                .ifPresent(match1 -> match1.setWinner(player.getName()));
        Optional.ofNullable(match)
                .filter(match1 -> match1.getPlayer2().getName().equals(player.getName()))
                .filter(match1 -> match1.getScore().getScorePlayer2().getNumberSetWonByPlayer() ==2)
                .ifPresent(match1 -> match1.setWinner(player.getName()));

        return match;
    }
}
