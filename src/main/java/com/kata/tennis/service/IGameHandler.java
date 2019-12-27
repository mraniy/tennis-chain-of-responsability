package com.kata.tennis.service;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;
import com.kata.tennis.model.ScorePlayer;

public interface IGameHandler {

    default boolean itsATieBreak(Match match) {
        int gamesWonByPlayer1 = match.getPlayer1().getScorePlayer().getNumberOfGamesWonsByPlayerForCurrentSet(match.getSetNumber());
        int gamesWonByPlayer2 = match.getPlayer2().getScorePlayer().getNumberOfGamesWonsByPlayerForCurrentSet(match.getSetNumber());
        return gamesWonByPlayer1 == 6 && gamesWonByPlayer2 == 6;
    }

    default boolean incrementGamesIfSomePlayerWinsTheGame(Match match, Player player, int limitToWinToGame) {
        boolean gameWonByPlayer1 = gameWonBySomePlayer(match.getPlayer2().getScorePlayer(), match.getPlayer1().getScorePlayer(), limitToWinToGame);
        boolean gameWonByPlayer2 = gameWonBySomePlayer(match.getPlayer1().getScorePlayer(), match.getPlayer2().getScorePlayer(), limitToWinToGame);
        if (gameWonByPlayer1 || gameWonByPlayer2) {
            incrementGame(player.getScorePlayer());
            return true;
        }
        return false;
    }

    default void incrementGame(ScorePlayer scorePlayer) {
        Integer games = scorePlayer.getNumberGamesWonByPlayerBySet().getLast().getGames();
        scorePlayer.getNumberGamesWonByPlayerBySet().getLast().setGames(games+1);
    }

    default void setPointsToZero(Match match) {
        match.getPlayer1().getScorePlayer().setNumberPointsOfGameWonByPlayer(0);
        match.getPlayer2().getScorePlayer().setNumberPointsOfGameWonByPlayer(0);
    }

    default boolean gameWonBySomePlayer(ScorePlayer scorePlayer1, ScorePlayer scorePlayer2, int limitToWinToGame) {
        return scorePlayer1.getNumberPointsOfGameWonByPlayer() >= limitToWinToGame
                && scorePlayer1.getNumberPointsOfGameWonByPlayer() >= scorePlayer2.getNumberPointsOfGameWonByPlayer() + 2;
    }


}
