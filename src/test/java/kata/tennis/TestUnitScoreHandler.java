package kata.tennis;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;
import com.kata.tennis.model.Score;
import com.kata.tennis.model.ScorePlayer;
import com.kata.tennis.service.PointHander;
import com.kata.tennis.service.UnitScoreHandler;
import org.junit.Test;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static kata.tennis.DataFactory.aScore;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class TestUnitScoreHandler {

    UnitScoreHandler pointHandler = new PointHander();

    @Test
    public void should_determine_match_winner_correctly() {
        // given
        Player federer = new Player("Federer");
        Player nadal = new Player("Nadal");


        ScorePlayer scoreFederer = aScore(3, 1, new AtomicInteger(5));
        ScorePlayer scoreNadal = aScore(2, 1, new AtomicInteger(4));

        Score score = new Score(scoreFederer, scoreNadal);
        Match match = new Match(federer, nadal, score);
        // when

        pointHandler.proceed(match, "Federer");
        // then
        assertThat(match.getWinner() , is(Optional.of("Federer")));
    }

    @Test
    public void should_determine_set_winner_correctly() {
        // given
        Player federer = new Player("Federer");
        Player nadal = new Player("Nadal");

        ScorePlayer scoreFederer = aScore(7, 0, new AtomicInteger(4));
        ScorePlayer scoreNadal = aScore(8, 0, new AtomicInteger(5));

        Score score = new Score(scoreFederer, scoreNadal);
        Match match = new Match(federer, nadal, score);
        // when

        pointHandler.proceed(match, "Nadal");
        // then
        assertThat(match.getScore().getScorePlayer2().getNumberSetWonByPlayer() , is(1));
    }
}
