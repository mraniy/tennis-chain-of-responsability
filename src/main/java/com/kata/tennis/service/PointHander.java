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
    public Match refreshScore(Match match, Player player) {
        return Optional.ofNullable(match)
                .filter(match1 -> match1.getPlayer1().getName().equals(player.getName()))
                .map(match1 ->
                        updateScore(match, match.getScore().getScorePlayer1()))
                .orElseGet(() -> updateScore(match, match.getScore().getScorePlayer2()));
    }

    private Match updateScore(Match match, ScorePlayer scorePlayer) {
        scorePlayer.setNumberPointsOfGameWonByPlayer(new AtomicInteger(scorePlayer.getNumberPointsOfGameWonByPlayer()).incrementAndGet());
        return match;
    }


}
