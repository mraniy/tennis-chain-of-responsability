package com.kata.tennis.main;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;
import com.kata.tennis.model.Score;
import com.kata.tennis.model.ScorePlayer;
import com.kata.tennis.service.*;

import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        ScoreDisplayed scoreDisplayed = null;
        UnitScoreHandler pointHandler = new PointHander();
        UnitScoreHandler gameHandler = new GameHandler();
        UnitScoreHandler tieBreakHandler = new TieBreakHandler();
        UnitScoreHandler MatchHandler = new MatchHandler();
        UnitScoreHandler setHandler = new SetHandler();
        ScoreDisplayHandler displayHandler = new ScoreDisplayHandler();
        pointHandler.setNext(gameHandler);
        gameHandler.setNext(tieBreakHandler);
        tieBreakHandler.setNext(setHandler);
        setHandler.setNext(MatchHandler);
        Player federer = new Player("Federer");
        Player nadal = new Player("Nadal");
        ScorePlayer scoreFederer = new ScorePlayer(0,0,0, 0, 0);
        ScorePlayer scoreNadal = new ScorePlayer(0,0,0, 0, 0);
        Score score = new Score(scoreFederer, scoreNadal);
        Match match = new Match(federer, nadal, score);
        while(match.getWinner() == null) {
            long randomwinner = Math.round(Math.random());
            if(randomwinner == 0l) {
                pointHandler.proceed(match, "Federer");
            } else if(randomwinner == 1l) {
                pointHandler.proceed(match, "Nadal");
            }
            scoreDisplayed = displayHandler.show(match);
            String player1Games = scoreDisplayed.getGamesWonByPlayer1().stream()
                    .map(integer -> String.valueOf(integer))
                    .collect(Collectors.joining(" "));

            String player1Sets = scoreDisplayed.getSetsWonByPlayer1().stream()
                    .map(integer -> String.valueOf(integer))
                    .collect(Collectors.joining(" "));

            String player2Games = scoreDisplayed.getGamesWonByPlayer2().stream()
                    .map(integer -> String.valueOf(integer))
                    .collect(Collectors.joining(" "));

            String player2Sets = scoreDisplayed.getSetsWonByPlayer2().stream()
                    .map(integer -> String.valueOf(integer))
                    .collect(Collectors.joining(" "));

            String strFederer = federer.getName() +" "+ scoreDisplayed.getPointsWonByPlayer1()+ " "
                    + player1Games + " "
                    + player1Sets;

            String strNadal = nadal.getName() + "   "+ scoreDisplayed.getPointsWonByPlayer2() + " "
                    + player2Games + " "
                    + player2Sets;
            System.out.println(strFederer);
            System.out.println(strNadal);
            System.out.println();


        }


    }
}
