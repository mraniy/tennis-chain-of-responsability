package com.kata.tennis.main;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;
import com.kata.tennis.model.ScorePlayer;
import com.kata.tennis.service.PointHander;
import com.kata.tennis.service.ScoreDisplayHandler;
import com.kata.tennis.model.ScoreDisplayed;
import com.kata.tennis.service.UnitScoreHandler;



import static com.kata.tennis.util.StringUtils.getLineOfScoreByPlayer;
import static com.kata.tennis.util.StringUtils.retrieveUnitsOfGamesWonBySomePlayer;
import static com.kata.tennis.util.StringUtils.retrieveUnitsOfSetsWonBySomePlayer;

public class Main {

    static UnitScoreHandler pointHandler = new PointHander();
    static ScoreDisplayHandler displayHandler = new ScoreDisplayHandler();

    public static void main(String[] args) {
        Integer numberOfPointsPlayed = 0;
        Player federer = new Player("Federer", new ScorePlayer());
        Player nadal = new Player("Nadal", new ScorePlayer());
        Match match = new Match(federer, nadal);
        for (;!match.getWinner().isPresent(); numberOfPointsPlayed++ ) {
            generatePointAndGiveItToRandomPlayer(match);
            ScoreDisplayed scoreDisplayed = displayHandler.show(match);
            displayScore(scoreDisplayed, federer, nadal);
        }
        System.out.println("number of points generated randomly : " + numberOfPointsPlayed);
    }

    private static void displayScore(ScoreDisplayed scoreDisplayed, Player federer, Player nadal) {
        String player1Games = retrieveUnitsOfGamesWonBySomePlayer(scoreDisplayed.getGamesWonByPlayer1());
        String player1Sets = retrieveUnitsOfSetsWonBySomePlayer(scoreDisplayed.getSetsWonByPlayer1());
        String player2Games = retrieveUnitsOfGamesWonBySomePlayer(scoreDisplayed.getGamesWonByPlayer2());
        String player2Sets = retrieveUnitsOfSetsWonBySomePlayer(scoreDisplayed.getSetsWonByPlayer2());
        String strFederer = getLineOfScoreByPlayer(scoreDisplayed.getPointsWonByPlayer1(), federer, player1Games, player1Sets);
        String strNadal = getLineOfScoreByPlayer(scoreDisplayed.getPointsWonByPlayer2(), nadal, player2Games, player2Sets);
        showScoreOfPlayers(strFederer, strNadal);
    }

    private static void showScoreOfPlayers(String strFederer, String strNadal) {
        System.out.println(strFederer);
        System.out.println(strNadal);
        System.out.println();
    }


    private static void generatePointAndGiveItToRandomPlayer(Match match) {
        long randomwinner = Math.round(Math.random());
        switch (String.valueOf(randomwinner)) {
            case "0":
                pointHandler.proceed(match, match.getPlayer1());
                break;
            default:
                pointHandler.proceed(match, match.getPlayer2());
                break;
        }
    }
}
