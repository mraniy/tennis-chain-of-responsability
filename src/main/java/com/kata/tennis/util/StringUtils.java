package com.kata.tennis.util;

import com.kata.tennis.model.GamesAndMaybeTieBreakPoints;
import com.kata.tennis.model.Player;

import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringUtils {

    public static String getLineOfScoreByPlayer(String pointsWonByPlayer, Player player, String playerGames, String playerSets) {
        return getNameCompletedByNCaracters(player, 10) + " " + pointsWonByPlayer + " "
                + playerGames + " "
                + playerSets;
    }

    public static String getNameCompletedByNCaracters(Player player, int numberOfChars) {
        StringBuffer nameCompletedBySpaces = new StringBuffer(player.getName());
        IntStream.range(player.getName().length(), numberOfChars)
                .forEach(value -> nameCompletedBySpaces.append(" "));
        return nameCompletedBySpaces.toString();
    }

    public static String retrieveUnitsOfSetsWonBySomePlayer(LinkedList<Integer> gamesWonByPlayer) {
        return gamesWonByPlayer.stream()
                .map(integer -> String.valueOf(integer))
                .collect(Collectors.joining(" "));
    }

    public static String retrieveUnitsOfGamesWonBySomePlayer(LinkedList<GamesAndMaybeTieBreakPoints> gamesWonByPlayer) {
        return gamesWonByPlayer.stream()
                .map(StringUtils::getScoreOfGame)
                .collect(Collectors.joining(" "));

    }

    public static String getScoreOfGame(GamesAndMaybeTieBreakPoints gamesAndMaybeTieBreakPoints) {
        String tieBreakPoints = gamesAndMaybeTieBreakPoints.getTieBreakPoints().map(tieBreakPoint -> String.valueOf(tieBreakPoint)).orElseGet(() -> "");
        int intGames = gamesAndMaybeTieBreakPoints.getGames();
        StringBuffer games = new StringBuffer(String.valueOf(intGames));
        if(!tieBreakPoints.isEmpty()) {
            games.append("(").append(tieBreakPoints).append(")");
        }
        return games.toString();
    }
}
