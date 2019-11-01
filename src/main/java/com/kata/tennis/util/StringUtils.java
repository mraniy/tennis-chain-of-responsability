package com.kata.tennis.util;

import com.kata.tennis.model.Player;

import java.util.List;
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

    public static String retrieveUnitsOfGameWonBySomePlayer(List<Integer> gamesWonByPlayer) {
        return gamesWonByPlayer.stream()
                .map(integer -> String.valueOf(integer))
                .collect(Collectors.joining(" "));
    }
}
