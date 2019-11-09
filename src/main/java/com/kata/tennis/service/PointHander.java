package com.kata.tennis.service;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;
import com.kata.tennis.model.ScorePlayer;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class PointHander extends UnitScoreHandler {

    public PointHander() {
        next = Optional.of(new GameHandler());
    }

    @Override
    public void refreshScore(Match match, Player player) {
        if(match.getPlayer1().getName().equals(player.getName())) {
            updateScore(match, match.getScore().getScorePlayer1());
        } else {
            updateScore(match, match.getScore().getScorePlayer2());
        }
    }

    private Match updateScore(Match match, ScorePlayer scorePlayer) {
        scorePlayer.setNumberPointsOfGameWonByPlayer(new AtomicInteger(scorePlayer.getNumberPointsOfGameWonByPlayer()).incrementAndGet());
        return match;
    }


}
