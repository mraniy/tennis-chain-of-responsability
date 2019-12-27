package com.kata.tennis.service;

import com.kata.tennis.model.GamesAndMaybeTieBreakPoints;
import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;
import com.kata.tennis.model.ScorePlayer;

import java.util.Optional;

public class SetHandler extends UnitScoreHandler {
    public SetHandler() {
        next = Optional.of(new MatchHandler());
    }

    private void incrementSetNumberAndAddNewSet(Match match) {
        match.setSetNumber(match.getSetNumber()+1);
        match.getPlayer1().getScorePlayer().getNumberGamesWonByPlayerBySet().add(new GamesAndMaybeTieBreakPoints(0,Optional.empty()));
        match.getPlayer2().getScorePlayer().getNumberGamesWonByPlayerBySet().add(new GamesAndMaybeTieBreakPoints(0,Optional.empty()));
    }

    @Override
    public void refreshScore(Match match, Player player) {
        handleSetWinner(match, match.getPlayer1().getScorePlayer(), match.getPlayer2().getScorePlayer());
        handleSetWinner(match, match.getPlayer2().getScorePlayer(), match.getPlayer1().getScorePlayer());
    }

    public void handleSetWinner(Match match, ScorePlayer scorePlayer1, ScorePlayer scorePlayer2) {
        if (hasPlayerWonSet(match, scorePlayer1, scorePlayer2)) {
            scorePlayer1.setNumberSetWonByPlayer(scorePlayer1.getNumberSetWonByPlayer()+1);
            incrementSetNumberAndAddNewSet(match);
        }
    }


    private boolean hasPlayerWonSet(Match match, ScorePlayer scorePlayer1, ScorePlayer scorePlayer2) {
        return hasPlayer1WonTieBreak(match, scorePlayer1, scorePlayer2) ||
                hasPlayer1WonUsualSet(match, scorePlayer1, scorePlayer2);
    }

    private boolean hasPlayer1WonUsualSet(Match match, ScorePlayer scorePlayer1, ScorePlayer scorePlayer2) {
        int numberGamesWonByPlayer1 = scorePlayer1.getNumberOfGamesWonsByPlayerForCurrentSet(match.getSetNumber());
        int numberGamesWonByPlayer2 = scorePlayer2.getNumberOfGamesWonsByPlayerForCurrentSet(match.getSetNumber());
        int differenceOfPointsBetweenPlayer1AndPlayer2 = numberGamesWonByPlayer1 - numberGamesWonByPlayer2;
        if ((numberGamesWonByPlayer1 == 6 || numberGamesWonByPlayer1 == 7)
                && differenceOfPointsBetweenPlayer1AndPlayer2 >= 2)
            return true;
        return false;
    }

    private boolean hasPlayer1WonTieBreak(Match match , ScorePlayer scorePlayer1, ScorePlayer scorePlayer2) {
        return scorePlayer1.getNumberOfGamesWonsByPlayerForCurrentSet(match.getSetNumber()) == 7
                && scorePlayer2.getNumberOfGamesWonsByPlayerForCurrentSet(match.getSetNumber()) == 6;
    }


}
