package com.kata.tennis.service;

import com.kata.tennis.model.Match;

import java.util.Arrays;

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
            updateScorePointsDisplayed(scoreDisplayed, "AD", "40");
        } else if (isDeuceOrAdvantage(numberPointsOfGameWonByPlayer1, numberPointsOfGameWonByPlayer2) && numberPointsOfGameWonByPlayer2 > numberPointsOfGameWonByPlayer1) {
            updateScorePointsDisplayed(scoreDisplayed, "40", "AD");
        } else if (isDeuceOrAdvantage(numberPointsOfGameWonByPlayer1, numberPointsOfGameWonByPlayer2) && numberPointsOfGameWonByPlayer2 == numberPointsOfGameWonByPlayer1) {
            updateScorePointsDisplayed(scoreDisplayed, "40", "40");
        } else if (!isDeuceOrAdvantage(numberPointsOfGameWonByPlayer1, numberPointsOfGameWonByPlayer2)) {
            updateScorePointsDisplayed(scoreDisplayed, convertPointNumbersToPointString(numberPointsOfGameWonByPlayer1), convertPointNumbersToPointString(numberPointsOfGameWonByPlayer2));
        }
    }

    private boolean isTieBreak(Match match) {
        return match.getScore().getScorePlayer1().getNumberOfGamesWonsByPlayerForCurrentSet(match.getSetNumber()) == 6
                && match.getScore().getScorePlayer2().getNumberOfGamesWonsByPlayerForCurrentSet(match.getSetNumber())== 6;
    }

    private void updateScoreGamesDisplayed(Match match, ScoreDisplayed scoreDisplayed) {
        int numberGamesWonByPlayer1Set1 = match.getScore().getScorePlayer1().getNumberGamesWonByPlayerSet1();
        int numberGamesWonByPlayer2Set1 = match.getScore().getScorePlayer2().getNumberGamesWonByPlayerSet1();
        int numberGamesWonByPlayer1Set2 = match.getScore().getScorePlayer1().getNumberGamesWonByPlayerSet2();
        int numberGamesWonByPlayer2Set2 = match.getScore().getScorePlayer2().getNumberGamesWonByPlayerSet2();
        int numberGamesWonByPlayer1Set3 = match.getScore().getScorePlayer1().getNumberGamesWonByPlayerSet3();
        int numberGamesWonByPlayer2Set3 = match.getScore().getScorePlayer2().getNumberGamesWonByPlayerSet3();
        scoreDisplayed.setGamesWonByPlayer1(Arrays.asList(numberGamesWonByPlayer1Set1,numberGamesWonByPlayer1Set2,numberGamesWonByPlayer1Set3));
        scoreDisplayed.setGamesWonByPlayer2(Arrays.asList(numberGamesWonByPlayer2Set1, numberGamesWonByPlayer2Set2,numberGamesWonByPlayer2Set3));
    }

    private void updateScoreSetsDisplayed(Match match, ScoreDisplayed scoreDisplayed) {
        int numberSetWonByPlayer1 = match.getScore().getScorePlayer1().getNumberSetWonByPlayer();
        int numberSetWonByPlayer2 = match.getScore().getScorePlayer2().getNumberSetWonByPlayer();
        scoreDisplayed.setSetsWonByPlayer1(Arrays.asList(numberSetWonByPlayer1));
        scoreDisplayed.setSetsWonByPlayer2(Arrays.asList(numberSetWonByPlayer2));
    }


    private void updateScorePointsDisplayed(ScoreDisplayed scoreDisplayed, String scorePlayer1, String scorePlayer2) {
        scoreDisplayed.setPointsWonByPlayer1(scorePlayer1);
        scoreDisplayed.setPointsWonByPlayer2(scorePlayer2);
    }

    private String convertPointNumbersToPointString(int numberPointsOfGameWonByPlayer) {
        if(numberPointsOfGameWonByPlayer == 0) {
            return "0";
        }
        if (numberPointsOfGameWonByPlayer == 1) {
            return "15";
        } else if (numberPointsOfGameWonByPlayer == 2) {
            return "30";
        } else {
            return "40";
        }
    }

    private boolean isDeuceOrAdvantage(int numberPointsOfGameWonByPlayer1, int numberPointsOfGameWonByPlayer2) {
        return numberPointsOfGameWonByPlayer1 >= 3 && numberPointsOfGameWonByPlayer2 >= 3;
    }
}
