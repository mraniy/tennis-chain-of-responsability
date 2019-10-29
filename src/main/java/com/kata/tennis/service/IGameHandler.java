package com.kata.tennis.service;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.ScorePlayer;

public interface IGameHandler {


    default void incrementTheRightGameOfSet(Match match1, ScorePlayer scorePlayer) {
        if(match1.getSetNumber() == 1) {
            int numberGamesWonByPlayer = scorePlayer.getNumberGamesWonByPlayerSet1();
            scorePlayer.setNumberGamesWonByPlayerSet1(numberGamesWonByPlayer + 1);
        } else if(match1.getSetNumber() == 2) {
            int numberGamesWonByPlayer = scorePlayer.getNumberGamesWonByPlayerSet2();
            scorePlayer.setNumberGamesWonByPlayerSet2(numberGamesWonByPlayer + 1);
        } else if(match1.getSetNumber() == 3) {
            int numberGamesWonByPlayer = scorePlayer.getNumberGamesWonByPlayerSet3();
            scorePlayer.setNumberGamesWonByPlayerSet3(numberGamesWonByPlayer + 1);
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
