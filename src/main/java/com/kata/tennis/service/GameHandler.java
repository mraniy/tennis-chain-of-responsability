package com.kata.tennis.service;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;

import java.util.Optional;

public class GameHandler extends UnitScoreHandler implements IGameHandler {
    public GameHandler() {
        next = new TieBreakHandler();
    }

    private final int LIMITTOWINTOGAME = 4;
    private final int LIMITMINTOLOSETHEGAME = 3;

    @Override
    public Match refreshScore(Match match, Player player) {
        if(itsNotATieBreak(match)) {
            incrementGamesOfPlayer1IfHeWinTheGame(match, player);
            incrementGamesOfPlayer2IfHeWinTheGame(match, player);
        }
        return match;
    }

    private boolean itsNotATieBreak(Match match) {
        return match.getScore().getScorePlayer1().getNumberOfGamesWonsByPlayerForCurrentSet(match.getSetNumber()) < 6
                || match.getScore().getScorePlayer2().getNumberOfGamesWonsByPlayerForCurrentSet(match.getSetNumber())< 6;
    }

    private void incrementGamesOfPlayer2IfHeWinTheGame(Match match, Player player) {
        Optional.ofNullable(match)
                .filter(match1 -> match1.getPlayer2().getName().equals(player.getName()))
                .filter(match1 -> gameWonBySomePlayer(match1.getScore().getScorePlayer2(), match1.getScore().getScorePlayer1(), LIMITTOWINTOGAME, LIMITMINTOLOSETHEGAME))
                .ifPresent(match1 -> {
                    incrementTheRightGameOfSet(match1, match1.getScore().getScorePlayer2());
                    setPointsToZero(match);
                        }
                );
    }



    private void incrementGamesOfPlayer1IfHeWinTheGame(Match match, Player player) {
        Optional.ofNullable(match)
                .filter(match1 -> match1.getPlayer1().getName().equals(player.getName()))
                .filter(match1 -> gameWonBySomePlayer(match1.getScore().getScorePlayer1(), match1.getScore().getScorePlayer2(), LIMITTOWINTOGAME, LIMITMINTOLOSETHEGAME))
                .ifPresent(match1 -> {
                    incrementTheRightGameOfSet(match1, match1.getScore().getScorePlayer1());
                    setPointsToZero(match);
                        }
                );
    }


}
