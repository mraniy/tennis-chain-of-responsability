package com.kata.tennis.service;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;

import java.util.Optional;

public class GameHandler extends UnitScoreHandler implements IGameHandler {
    public GameHandler() {
        next = Optional.of(new TieBreakHandler());
    }

    private final int LIMITTOWINTOGAME = 4;


    @Override
    public void refreshScore(Match match, Player player) {
        if (!itsATieBreak(match)) {
            boolean hasSomePlayerWonTheGame = incrementGamesIfSomePlayerWinsTheGame(match, player, LIMITTOWINTOGAME);
            if(hasSomePlayerWonTheGame) setPointsToZero(match);
        }
    }

}
