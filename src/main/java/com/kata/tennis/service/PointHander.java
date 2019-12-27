package com.kata.tennis.service;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;
import com.kata.tennis.model.ScorePlayer;

import java.util.Optional;

public class PointHander extends UnitScoreHandler {

    public PointHander() {
        next = Optional.of(new GameHandler());
    }

    @Override
    public void refreshScore(Match match, Player player) {
        ScorePlayer scorePlayer = player.getScorePlayer();
        scorePlayer.setNumberPointsOfGameWonByPlayer(scorePlayer.getNumberPointsOfGameWonByPlayer()+1);
    }

}
