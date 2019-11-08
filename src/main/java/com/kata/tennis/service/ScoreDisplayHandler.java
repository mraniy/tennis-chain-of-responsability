package com.kata.tennis.service;

import com.kata.tennis.model.EnumPoint;
import com.kata.tennis.model.Match;
import com.kata.tennis.model.ScoreDisplayed;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class ScoreDisplayHandler {

    public ScoreDisplayed show(Match match) {
        ScoreDisplayed scoreDisplayed = new ScoreDisplayed();
        updateScoreGamesDisplayed(match, scoreDisplayed);
        updateScoreSetsDisplayed(match, scoreDisplayed);
        updateScorePointsDisplayed(match, scoreDisplayed);
        return scoreDisplayed;
    }

    private void updateScorePointsDisplayed(Match match, ScoreDisplayed scoreDisplayed) {
        int numberPointsOfGameWonByPlayer1 = match.getScore().getScorePlayer1().getNumberPointsOfGameWonByPlayer();
        int numberPointsOfGameWonByPlayer2 = match.getScore().getScorePlayer2().getNumberPointsOfGameWonByPlayer();
        if(isTieBreak(match)) {
            updateScorePointsDisplayed(scoreDisplayed, String.valueOf(numberPointsOfGameWonByPlayer1), String.valueOf(numberPointsOfGameWonByPlayer2));
        }
        else if (isDeuceOrAdvantage(numberPointsOfGameWonByPlayer1, numberPointsOfGameWonByPlayer2) && numberPointsOfGameWonByPlayer1 > numberPointsOfGameWonByPlayer2) {
            updateScorePointsDisplayed(scoreDisplayed, EnumPoint.ADVANTAGE.getScore(), EnumPoint.FORTY.getScore());
        } else if (isDeuceOrAdvantage(numberPointsOfGameWonByPlayer1, numberPointsOfGameWonByPlayer2) && numberPointsOfGameWonByPlayer2 > numberPointsOfGameWonByPlayer1) {
            updateScorePointsDisplayed(scoreDisplayed, EnumPoint.FORTY.getScore(), EnumPoint.ADVANTAGE.getScore());
        } else if (isDeuceOrAdvantage(numberPointsOfGameWonByPlayer1, numberPointsOfGameWonByPlayer2) && numberPointsOfGameWonByPlayer2 == numberPointsOfGameWonByPlayer1) {
            updateScorePointsDisplayed(scoreDisplayed, EnumPoint.FORTY.getScore(), EnumPoint.FORTY.getScore());
        } else if (!isDeuceOrAdvantage(numberPointsOfGameWonByPlayer1, numberPointsOfGameWonByPlayer2)) {
            updateScorePointsDisplayed(scoreDisplayed, convertPointNumbersToPointString(numberPointsOfGameWonByPlayer1), convertPointNumbersToPointString(numberPointsOfGameWonByPlayer2));
        }
    }

    private boolean isTieBreak(Match match) {
        return match.getScore().getScorePlayer1().getNumberOfGamesWonsByPlayerForCurrentSet(match.getSetNumber()).get() == 6
                && match.getScore().getScorePlayer2().getNumberOfGamesWonsByPlayerForCurrentSet(match.getSetNumber()).get()== 6;
    }

    private void updateScoreGamesDisplayed(Match match, ScoreDisplayed scoreDisplayed) {
        scoreDisplayed.setGamesWonByPlayer1(match.getScore().getScorePlayer1().getNumberGamesWonByPlayerBySet());
        scoreDisplayed.setGamesWonByPlayer2(match.getScore().getScorePlayer2().getNumberGamesWonByPlayerBySet());
    }

    private void updateScoreSetsDisplayed(Match match, ScoreDisplayed scoreDisplayed) {
        int numberSetWonByPlayer1 = match.getScore().getScorePlayer1().getNumberSetWonByPlayer();
        int numberSetWonByPlayer2 = match.getScore().getScorePlayer2().getNumberSetWonByPlayer();
        scoreDisplayed.setSetsWonByPlayer1(new LinkedList(Arrays.asList(new AtomicInteger(numberSetWonByPlayer1))));
        scoreDisplayed.setSetsWonByPlayer2(new LinkedList(Arrays.asList(new AtomicInteger(numberSetWonByPlayer2))));
    }


    private void updateScorePointsDisplayed(ScoreDisplayed scoreDisplayed, String scorePlayer1, String scorePlayer2) {
        scoreDisplayed.setPointsWonByPlayer1(scorePlayer1);
        scoreDisplayed.setPointsWonByPlayer2(scorePlayer2);
    }

    private String convertPointNumbersToPointString(int numberPointsOfGameWonByPlayer) {
        if(numberPointsOfGameWonByPlayer == 0) {
            return EnumPoint.LOVE.getScore();
        }
        if (numberPointsOfGameWonByPlayer == 1) {
            return EnumPoint.FIFTEEN.getScore();
        } else if (numberPointsOfGameWonByPlayer == 2) {
            return EnumPoint.THIRTY.getScore();
        } else {
            return EnumPoint.FORTY.getScore();
        }
    }

    private boolean isDeuceOrAdvantage(int numberPointsOfGameWonByPlayer1, int numberPointsOfGameWonByPlayer2) {
        return numberPointsOfGameWonByPlayer1 >= 3 && numberPointsOfGameWonByPlayer2 >= 3;
    }
}
