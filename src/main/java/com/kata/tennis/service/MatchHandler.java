package com.kata.tennis.service;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class MatchHandler extends UnitScoreHandler {

    private final int NUMBER_OF_SETS_TO_WIN_THE_TIE = 3;

    public MatchHandler() {
        next = Optional.empty();
    }

    @Override
    public void refreshScore(Match match, Player player) {
        if(hasPlayer2WonTheMatch(match, player) || hasPlayer1WonTheMatch(match, player)) {
            setWinner(player,match);
            removeLastSetAddedAndDecrementSetNumber(match);
        }
    }

    public void removeLastSetAddedAndDecrementSetNumber(Match match) {
        match.getScore().getScorePlayer1().getNumberGamesWonByPlayerBySet().removeLast();
        match.getScore().getScorePlayer2().getNumberGamesWonByPlayerBySet().removeLast();
        match.setSetNumber(new AtomicInteger(match.getSetNumber()).decrementAndGet());
    }

    private boolean hasPlayer1WonTheMatch(Match match, Player player) {
        return match.getPlayer1().getName().equals(player.getName()) && match.getScore().getScorePlayer1().getNumberSetWonByPlayer() == NUMBER_OF_SETS_TO_WIN_THE_TIE;
    }

    private boolean hasPlayer2WonTheMatch(Match match, Player player) {
        return match.getPlayer2().getName().equals(player.getName()) && match.getScore().getScorePlayer2().getNumberSetWonByPlayer() == NUMBER_OF_SETS_TO_WIN_THE_TIE;
    }


    private void setWinner(Player player, Match match1) {
        match1.setWinner(Optional.of(player.getName()));
    }


}
