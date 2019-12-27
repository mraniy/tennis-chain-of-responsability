package com.kata.tennis.service;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;

import java.util.Optional;

public class MatchHandler extends UnitScoreHandler {

    private final int NUMBER_OF_SETS_TO_WIN_THE_TIE = 2;

    public MatchHandler() {
        next = Optional.empty();
    }

    @Override
    public void refreshScore(Match match, Player player) {
        if(hasPlayerWonTheMatch(player)) {
            setWinner(player,match);
            removeLastSetAddedAndDecrementSetNumber(match);
        }
    }

    public void removeLastSetAddedAndDecrementSetNumber(Match match) {
        match.getPlayer1().getScorePlayer().getNumberGamesWonByPlayerBySet().removeLast();
        match.getPlayer2().getScorePlayer().getNumberGamesWonByPlayerBySet().removeLast();
        match.setSetNumber(match.getSetNumber()-1);
    }

    private boolean hasPlayerWonTheMatch(Player player) {
        return player.getScorePlayer().getNumberSetWonByPlayer() == NUMBER_OF_SETS_TO_WIN_THE_TIE;
    }




    private void setWinner(Player player, Match match1) {
        match1.setWinner(Optional.of(player.getName()));
    }


}
