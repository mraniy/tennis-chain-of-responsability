package com.kata.tennis.service;

import com.kata.tennis.model.GamesAndMaybeTieBreakPoints;
import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class TieBreakHandler extends UnitScoreHandler implements IGameHandler {

    public TieBreakHandler() {
        next = Optional.of(new SetHandler());
    }

    private final int LIMITTOWINTOGAME = 7;


    @Override
    public void refreshScore(Match match, Player player) {
        if (itsATieBreak(match)) {
            boolean hasSomePlayerWonTheGame = incrementGamesIfSomePlayerWinsTheGame(match, player, LIMITTOWINTOGAME);
            GamesAndMaybeTieBreakPoints currentGamesAndMaybeTieBreakPointsWonByPlayer1 = match.getScore().getScorePlayer1().getNumberGamesWonByPlayerBySet().get(match.getSetNumber() - 1);
            currentGamesAndMaybeTieBreakPointsWonByPlayer1.setTieBreakPoints(Optional.of(new AtomicInteger(match.getScore().getScorePlayer1().getNumberPointsOfGameWonByPlayer())));
            GamesAndMaybeTieBreakPoints currentGamesAndMaybeTieBreakPointsWonByPlayer2 = match.getScore().getScorePlayer2().getNumberGamesWonByPlayerBySet().get(match.getSetNumber() - 1);
            currentGamesAndMaybeTieBreakPointsWonByPlayer2.setTieBreakPoints(Optional.of(new AtomicInteger(match.getScore().getScorePlayer2().getNumberPointsOfGameWonByPlayer())));
            if(hasSomePlayerWonTheGame) setPointsToZero(match);
        }
    }


}
