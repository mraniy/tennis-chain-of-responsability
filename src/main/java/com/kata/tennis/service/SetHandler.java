package com.kata.tennis.service;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;
import com.kata.tennis.model.ScorePlayer;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class SetHandler extends UnitScoreHandler {
    public SetHandler() {
        next = Optional.of(new MatchHandler());
    }

    private void incrementSetNumberAndAddNewSet(Match match) {
        match.setSetNumber(new AtomicInteger(match.getSetNumber()).incrementAndGet());
        match.getScore().getScorePlayer1().getNumberGamesWonByPlayerBySet().add(new AtomicInteger(0));
        match.getScore().getScorePlayer2().getNumberGamesWonByPlayerBySet().add(new AtomicInteger(0));
    }

    @Override
    public void refreshScore(Match match, Player player) {
        handleSetWinner(match, player, match.getPlayer1(), match.getScore().getScorePlayer1(), match.getScore().getScorePlayer2());
        handleSetWinner(match, player, match.getPlayer2(), match.getScore().getScorePlayer2(), match.getScore().getScorePlayer1());
    }

    public void handleSetWinner(Match match, Player player1, Player player, ScorePlayer scorePlayer1, ScorePlayer scorePlayer2) {
        if (player.getName().equals(player1.getName()) &&
                hasPlayerWonSet(match, scorePlayer1, scorePlayer2)) {
            scorePlayer1.setNumberSetWonByPlayer(new AtomicInteger(scorePlayer1.getNumberSetWonByPlayer()).incrementAndGet());
            incrementSetNumberAndAddNewSet(match);
        }
    }


    private boolean hasPlayerWonSet(Match match, ScorePlayer scorePlayer1, ScorePlayer scorePlayer2) {
        return hasPlayer1WonTieBreak(match, scorePlayer1, scorePlayer2) ||
                hasPlayer1WonUsualSet(match, scorePlayer1, scorePlayer2);
    }

    private boolean hasPlayer1WonUsualSet(Match match, ScorePlayer scorePlayer1, ScorePlayer scorePlayer2) {
        int numberGamesWonByPlayer1 = scorePlayer1.getNumberOfGamesWonsByPlayerForCurrentSet(match.getSetNumber()).get();
        int numberGamesWonByPlayer2 = scorePlayer2.getNumberOfGamesWonsByPlayerForCurrentSet(match.getSetNumber()).get();
        int differenceOfPointsBetweenPlayer1AndPlayer2 = numberGamesWonByPlayer1 - numberGamesWonByPlayer2;
        if ((numberGamesWonByPlayer1 == 6 || numberGamesWonByPlayer1 == 7)
                && differenceOfPointsBetweenPlayer1AndPlayer2 >= 2)
            return true;
        return false;
    }

    private boolean hasPlayer1WonTieBreak(Match match , ScorePlayer scorePlayer1, ScorePlayer scorePlayer2) {
        return scorePlayer1.getNumberOfGamesWonsByPlayerForCurrentSet(match.getSetNumber()).get() == 7
                && scorePlayer2.getNumberOfGamesWonsByPlayerForCurrentSet(match.getSetNumber()).get() == 6;
    }


}
