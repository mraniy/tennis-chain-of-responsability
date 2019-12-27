package com.kata.tennis.service;

import com.kata.tennis.model.GamesAndMaybeTieBreakPoints;
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
        if (itsATieBreak(match)) {
            addPointsRelatedToTieBreak(match);
            boolean hasSomePlayerWonTheGame = incrementGamesIfSomePlayerWinsTheGame(match, player, LIMITTOWINTOGAME);
            if(hasSomePlayerWonTheGame) setPointsToZero(match);
        }
    }

    private void addPointsRelatedToTieBreak(Match match) {
        GamesAndMaybeTieBreakPoints currentGamesAndMaybeTieBreakPointsWonByPlayer1 = match.getPlayer1().getScorePlayer().getNumberGamesWonByPlayerBySet().get(match.getSetNumber() - 1);
        GamesAndMaybeTieBreakPoints currentGamesAndMaybeTieBreakPointsWonByPlayer2 = match.getPlayer2().getScorePlayer().getNumberGamesWonByPlayerBySet().get(match.getSetNumber() - 1);
        currentGamesAndMaybeTieBreakPointsWonByPlayer1.setTieBreakPoints(Optional.of(match.getPlayer1().getScorePlayer().getNumberPointsOfGameWonByPlayer()));
        currentGamesAndMaybeTieBreakPointsWonByPlayer2.setTieBreakPoints(Optional.of(match.getPlayer2().getScorePlayer().getNumberPointsOfGameWonByPlayer()));
    }


}
