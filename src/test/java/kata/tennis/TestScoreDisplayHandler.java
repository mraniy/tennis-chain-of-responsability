package kata.tennis;

import com.kata.tennis.model.*;
import com.kata.tennis.service.ScoreDisplayHandler;
import com.kata.tennis.model.ScoreDisplayed;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static kata.tennis.DataFactory.aScore;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestScoreDisplayHandler {

    @Test
    public void should_return_correct_score_when_advantage() {
        // given
        Player federer = new Player("Federer");
        Player nadal = new Player("Nadal");
        ScorePlayer scoreFederer =  aScore(7, 1, new AtomicInteger(4));
        ScorePlayer scoreNadal = aScore(6, 1, new AtomicInteger(3));

        Score score = new Score(scoreFederer, scoreNadal);
        Match match = new Match(federer, nadal, score);
        // when
        ScoreDisplayHandler scoreDisplayHandler = new ScoreDisplayHandler();
        ScoreDisplayed scoreDisplayed = scoreDisplayHandler.show(match);
        // then
        assertThat(scoreDisplayed.getGamesWonByPlayer1().get(0).getGames().get(), is(4));
        assertThat(scoreDisplayed.getGamesWonByPlayer2().get(0).getGames().get(), is(3));
        assertThat(scoreDisplayed.getPointsWonByPlayer1(), is(EnumPoint.ADVANTAGE.getScore()));
        assertThat(scoreDisplayed.getPointsWonByPlayer2(), is(EnumPoint.FORTY.getScore()));
    }

    @Test
    public void should_return_correct_score_when_deuce() {
        // given
        Player federer = new Player("Federer");
        Player nadal = new Player("Nadal");
        ScorePlayer scoreFederer = aScore(9, 1, new AtomicInteger(4));
        ScorePlayer scoreNadal = aScore(9, 1, new AtomicInteger(3));

        Score score = new Score(scoreFederer, scoreNadal);
        Match match = new Match(federer, nadal, score);
        // when
        ScoreDisplayHandler scoreDisplayHandler = new ScoreDisplayHandler();
        ScoreDisplayed scoreDisplayed = scoreDisplayHandler.show(match);
        // then
        assertThat(scoreDisplayed.getGamesWonByPlayer1().get(0).getGames().get(), is(4));
        assertThat(scoreDisplayed.getGamesWonByPlayer2().get(0).getGames().get(), is(3));
        assertThat(scoreDisplayed.getPointsWonByPlayer1(), is(EnumPoint.FORTY.getScore()));
        assertThat(scoreDisplayed.getPointsWonByPlayer2(), is(EnumPoint.FORTY.getScore()));
    }

    @Test
    public void should_return_correct_score_when_normal_score() {
        // given
        Player federer = new Player("Federer");
        Player nadal = new Player("Nadal");
        ScorePlayer scoreFederer = aScore(3, 1, new AtomicInteger(4));
        ScorePlayer scoreNadal = aScore(1, 1, new AtomicInteger(3));

        Score score = new Score(scoreFederer, scoreNadal);
        Match match = new Match(federer, nadal, score);
        // when
        ScoreDisplayHandler scoreDisplayHandler = new ScoreDisplayHandler();
        ScoreDisplayed scoreDisplayed = scoreDisplayHandler.show(match);
        // then
        assertThat(scoreDisplayed.getGamesWonByPlayer1().get(0).getGames().get(), is(4));
        assertThat(scoreDisplayed.getGamesWonByPlayer2().get(0).getGames().get(), is(3));
        assertThat(scoreDisplayed.getPointsWonByPlayer1(), is(EnumPoint.FORTY.getScore()));
        assertThat(scoreDisplayed.getPointsWonByPlayer2(), is(EnumPoint.FIFTEEN.getScore()));
    }
}
