package kata.tennis;

import com.kata.tennis.model.GamesAndMaybeTieBreakPoints;
import com.kata.tennis.model.ScorePlayer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class DataFactory {

    public static ScorePlayer aScore( int numberPointsOfGameWonByPlayer, int numberSetWonByPlayer, AtomicInteger... numberGamesWonByPlayerSet) {
        ScorePlayer scorePlayer = new ScorePlayer();
        scorePlayer.setNumberPointsOfGameWonByPlayer(numberPointsOfGameWonByPlayer);
        scorePlayer.setNumberSetWonByPlayer(numberSetWonByPlayer);
        List<GamesAndMaybeTieBreakPoints> gamesAndMaybeTieBreakPoints = Arrays.asList(numberGamesWonByPlayerSet).stream().map(atomicInteger -> new GamesAndMaybeTieBreakPoints(atomicInteger, Optional.empty())).collect(Collectors.toList());
        scorePlayer.setNumberGamesWonByPlayerBySet(new LinkedList<>(gamesAndMaybeTieBreakPoints));
        return scorePlayer;
    }
}
