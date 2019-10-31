package com.kata.tennis.main;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;
import com.kata.tennis.model.Score;
import com.kata.tennis.model.ScorePlayer;
import com.kata.tennis.service.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    static UnitScoreHandler pointHandler = new PointHander();
    static ScoreDisplayHandler displayHandler= new ScoreDisplayHandler();

    public static void main(String[] args) {
        Player federer = new Player("Federer");
        Player nadal = new Player("Nadal");
        Match match = new Match(federer, nadal, new Score(new ScorePlayer(), new ScorePlayer()));
        while(match.getWinner() == null) {
            generatePointAndGiveItToRandomPlayer(match);
            ScoreDisplayed scoreDisplayed = displayHandler.show(match);
            displayScore(scoreDisplayed, federer, nadal);
        }
    }

    private static void displayScore(ScoreDisplayed scoreDisplayed, Player federer, Player nadal) {
        String player1Games = retrieveUnitsOfGameWonBySomePlayer(scoreDisplayed.getGamesWonByPlayer1());
        String player1Sets = retrieveUnitsOfGameWonBySomePlayer(scoreDisplayed.getSetsWonByPlayer1());
        String player2Games = retrieveUnitsOfGameWonBySomePlayer(scoreDisplayed.getGamesWonByPlayer2());
        String player2Sets = retrieveUnitsOfGameWonBySomePlayer(scoreDisplayed.getSetsWonByPlayer2());
        String strFederer = federer.getName() +"--------"+ scoreDisplayed.getPointsWonByPlayer1()+ "---------------"
                + player1Games + "-------"
                + player1Sets;
        String strNadal = nadal.getName() + "----------"+ scoreDisplayed.getPointsWonByPlayer2() + "---------------"
                + player2Games + "-------"
                + player2Sets;
        System.out.println(strFederer);
        System.out.println(strNadal);
        System.out.println();
    }

    private static String retrieveUnitsOfGameWonBySomePlayer(List<Integer> gamesWonByPlayer) {
        return gamesWonByPlayer.stream()
                .map(integer -> String.valueOf(integer))
                .collect(Collectors.joining("----------"));
    }

    private static void generatePointAndGiveItToRandomPlayer(Match match) {
        long randomwinner = Math.round(Math.random());
        if(randomwinner == 0l) {
            pointHandler.proceed(match, "Federer");
        } else {
            pointHandler.proceed(match, "Nadal");
        }
    }
}
