package kata.tennis;

import com.kata.tennis.model.ScorePlayer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class DataFactory {

    public static ScorePlayer aScore( int numberPointsOfGameWonByPlayer, int numberSetWonByPlayer, AtomicInteger... numberGamesWonByPlayerSet) {
        ScorePlayer scorePlayer = new ScorePlayer();
        scorePlayer.setNumberPointsOfGameWonByPlayer(numberPointsOfGameWonByPlayer);
        scorePlayer.setNumberSetWonByPlayer(numberSetWonByPlayer);
        scorePlayer.setNumberGamesWonByPlayerBySet(new LinkedList(Arrays.asList(numberGamesWonByPlayerSet)));
        return scorePlayer;
    }
}
