package com.kata.tennis.service;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.ScorePlayer;

public interface IGameHandler {


    default void incrementTheRightGameOfSet(Match match1, ScorePlayer scorePlayer) {
        switch (match1.getSetNumber()) {
            case 1 : scorePlayer.setNumberGamesWonByPlayerSet1(scorePlayer.getNumberGamesWonByPlayerSet1() + 1); break;
            case 2 : scorePlayer.setNumberGamesWonByPlayerSet2(scorePlayer.getNumberGamesWonByPlayerSet2() + 1); break;
            case 3 : scorePlayer.setNumberGamesWonByPlayerSet3(scorePlayer.getNumberGamesWonByPlayerSet3() + 1); break;
        }
    }

    default void setPointsToZero(Match match) {
        match.getScore().getScorePlayer1().setNumberPointsOfGameWonByPlayer(0);
        match.getScore().getScorePlayer2().setNumberPointsOfGameWonByPlayer(0);
    }

    default boolean gameWonBySomePlayer(ScorePlayer scorePlayer1, ScorePlayer scorePlayer2, int limitToWinToGame, int limitMinToLoseTheGame) {
        return gameWonBySomePlayerWithAdvantage(scorePlayer1, scorePlayer2, limitToWinToGame) ||
                gameWonBySomeWithoutAdvantage(scorePlayer1, scorePlayer2, limitToWinToGame, limitMinToLoseTheGame);
    }

    default boolean gameWonBySomePlayerWithAdvantage(ScorePlayer scorePlayer1, ScorePlayer scorePlayer2, int limitToWinToGame) {
        return scorePlayer1.getNumberPointsOfGameWonByPlayer() >= limitToWinToGame
                && scorePlayer1.getNumberPointsOfGameWonByPlayer() == scorePlayer2.getNumberPointsOfGameWonByPlayer() + 2;
    }

    default boolean gameWonBySomeWithoutAdvantage(ScorePlayer scorePlayer1, ScorePlayer scorePlayer2, int limitToWinTheGame, int limitMinToLoseTheGame) {
        return scorePlayer1.getNumberPointsOfGameWonByPlayer() == limitToWinTheGame
                && scorePlayer2.getNumberPointsOfGameWonByPlayer() < limitMinToLoseTheGame;
    }
}
