package com.kata.tennis.service;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;
import com.kata.tennis.model.ScorePlayer;

public interface IGameHandler {

    default boolean itsATieBreak(Match match) {
        return match.getScore().getScorePlayer1().getNumberOfGamesWonsByPlayerForCurrentSet(match.getSetNumber()).get() == 6
                && match.getScore().getScorePlayer2().getNumberOfGamesWonsByPlayerForCurrentSet(match.getSetNumber()).get() == 6;
    }

    default boolean incrementGamesIfSomePlayerWinsTheGame(Match match, Player player, int limitToWinToGame) {
        if (match.getPlayer2().getName().equals(player.getName()) &&
                gameWonBySomePlayer(match.getScore().getScorePlayer2(), match.getScore().getScorePlayer1(), limitToWinToGame)) {
            incrementGame(match.getScore().getScorePlayer2());
            return true;
        } else if (match.getPlayer1().getName().equals(player.getName()) &&
                gameWonBySomePlayer(match.getScore().getScorePlayer1(), match.getScore().getScorePlayer2(), limitToWinToGame)) {
            incrementGame(match.getScore().getScorePlayer1());
            return true;
        }
        return false;
    }

    default void incrementGame(ScorePlayer scorePlayer) {
        incrementTheRightGameOfSet(scorePlayer);
    }


    default void incrementTheRightGameOfSet(ScorePlayer scorePlayer) {
        scorePlayer.getNumberGamesWonByPlayerBySet().getLast().getGames().incrementAndGet();
    }

    default void setPointsToZero(Match match) {
        match.getScore().getScorePlayer1().setNumberPointsOfGameWonByPlayer(0);
        match.getScore().getScorePlayer2().setNumberPointsOfGameWonByPlayer(0);
    }

    default boolean gameWonBySomePlayer(ScorePlayer scorePlayer1, ScorePlayer scorePlayer2, int limitToWinToGame) {
        return scorePlayer1.getNumberPointsOfGameWonByPlayer() >= limitToWinToGame
                && scorePlayer1.getNumberPointsOfGameWonByPlayer() >= scorePlayer2.getNumberPointsOfGameWonByPlayer() + 2;
    }


}
