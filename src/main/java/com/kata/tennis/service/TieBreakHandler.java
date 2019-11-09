package com.kata.tennis.service;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;

import java.util.Optional;

public class TieBreakHandler extends UnitScoreHandler implements IGameHandler {

    public TieBreakHandler() {
        next = Optional.of(new SetHandler());
    }

    private final int LIMITTOWINTOGAME = 7;


    @Override
    public void refreshScore(Match match, Player player) {
        if (itsATieBreak(match))
            incrementGamesIfSomePlayerWinsTheMatch(match, player, LIMITTOWINTOGAME);
    }


}
