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

    private void incrementSetNumber(Match match) {
        match.setSetNumber(new AtomicInteger(match.getSetNumber()).incrementAndGet());
        match.getScore().getScorePlayer1().getNumberGamesWonByPlayerBySet().add(new AtomicInteger(0));
        match.getScore().getScorePlayer2().getNumberGamesWonByPlayerBySet().add(new AtomicInteger(0));
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
                .filter(match1 -> hasPlayerWonSet(match ,match1.getScore().getScorePlayer1(), match1.getScore().getScorePlayer2()))
                .ifPresent(match1 -> {
                    match1.getScore().getScorePlayer1().setNumberSetWonByPlayer(new AtomicInteger( match1.getScore().getScorePlayer1().getNumberSetWonByPlayer()).incrementAndGet());
                    incrementSetNumber(match1);
                });
    }


    private void incrementNumberOfSetsOfPlayer2IfHeWinTheSet(Match match, Player player) {
        Optional.ofNullable(match)
                .filter(match1 -> match1.getPlayer2().getName().equals(player.getName()))
                .filter(match1 -> hasPlayerWonSet(match, match1.getScore().getScorePlayer2(), match1.getScore().getScorePlayer1()))
                .ifPresent(match1 -> {
                    match1.getScore().getScorePlayer2().setNumberSetWonByPlayer(new AtomicInteger( match1.getScore().getScorePlayer2().getNumberSetWonByPlayer()).incrementAndGet());
                    incrementSetNumber(match1);
                });
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
