package kata.tennis;

import com.kata.tennis.model.GamesAndMaybeTieBreakPoints;
import com.kata.tennis.model.ScorePlayer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DataFactory {

    public static ScorePlayer aScore( int numberPointsOfGameWonByPlayer, int numberSetWonByPlayer, Integer... numberGamesWonByPlayerSet) {
        ScorePlayer scorePlayer = new ScorePlayer();
        scorePlayer.setNumberPointsOfGameWonByPlayer(numberPointsOfGameWonByPlayer);
        scorePlayer.setNumberSetWonByPlayer(numberSetWonByPlayer);
        List<GamesAndMaybeTieBreakPoints> gamesAndMaybeTieBreakPoints = Arrays.asList(numberGamesWonByPlayerSet).stream().map(Integer -> new GamesAndMaybeTieBreakPoints(Integer, Optional.empty())).collect(Collectors.toList());
        scorePlayer.setNumberGamesWonByPlayerBySet(new LinkedList<>(gamesAndMaybeTieBreakPoints));
        return scorePlayer;
    }
}
