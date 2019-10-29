package com.kata.tennis.service;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;
import com.kata.tennis.model.ScorePlayer;

import java.util.Optional;

public class SetHandler extends UnitScoreHandler {



    private void incrementSetNumber(Match match) {
        match.setSetNumber(match.getSetNumber()+1);
    }

    @Override
    public Match refreshScore(Match match, Player player) {
        incrementNumberOfSetsOfPlayer1IfHeWinTheSet(match, player);
        incrementNumberOfSetsOfPlayer2IfHeWinTheSet(match,player);
        return match;
    }

    private void incrementNumberOfSetsOfPlayer1IfHeWinTheSet(Match match, Player player) {
        Optional.ofNullable(match)
                .filter(match1 -> match1.getPlayer1().getName().equals(player.getName()))
                .filter(match1 -> hasPlayer1WonSet(match ,match1.getScore().getScorePlayer1(), match1.getScore().getScorePlayer2()))
                .ifPresent(match1 -> {
                    int numberSetWonByPlayer = match1.getScore().getScorePlayer1().getNumberSetWonByPlayer();
                    match1.getScore().getScorePlayer1().setNumberSetWonByPlayer(numberSetWonByPlayer+1);
                    incrementSetNumber(match);
                });
    }


    private void incrementNumberOfSetsOfPlayer2IfHeWinTheSet(Match match, Player player) {
        Optional.ofNullable(match)
                .filter(match1 -> match1.getPlayer2().getName().equals(player.getName()))
                .filter(match1 -> hasPlayer1WonSet(match, match1.getScore().getScorePlayer2(), match1.getScore().getScorePlayer1()))
                .ifPresent(match1 -> {
                    int numberSetWonByPlayer = match1.getScore().getScorePlayer2().getNumberSetWonByPlayer();
                    match1.getScore().getScorePlayer2().setNumberSetWonByPlayer(numberSetWonByPlayer+1);
                    incrementSetNumber(match);
                });
    }

    private boolean hasPlayer1WonSet(Match match,ScorePlayer scorePlayer1, ScorePlayer scorePlayer2) {
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
