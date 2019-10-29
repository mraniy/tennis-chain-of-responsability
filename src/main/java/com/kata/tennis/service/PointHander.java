package com.kata.tennis.service;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;
import com.kata.tennis.model.ScorePlayer;

import java.util.Optional;

public class PointHander extends UnitScoreHandler {

    @Override
    public Match refreshScore(Match match, Player player) {
        return Optional.ofNullable(match)
                .filter(match1 -> match1.getPlayer1().getName().equals(player.getName()))
                .map(match1 ->
                        updateScore(match, match.getScore().getScorePlayer1()))
                .orElseGet(() -> updateScore(match, match.getScore().getScorePlayer2()));
    }

    private Match updateScore(Match match, ScorePlayer scorePlayer1) {
        int numberPointsOfGameWonByPlayer = scorePlayer1.getNumberPointsOfGameWonByPlayer();
        int numberPointsOfGameWonByPlayer1 = numberPointsOfGameWonByPlayer + 1;
        scorePlayer1.setNumberPointsOfGameWonByPlayer(numberPointsOfGameWonByPlayer1);
        return match;
    }


}
