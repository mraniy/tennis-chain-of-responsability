package kata.tennis;

import com.kata.tennis.model.*;
import com.kata.tennis.service.ScoreDisplayHandler;
import com.kata.tennis.model.ScoreDisplayed;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestScoreDisplayHandler {

    @Test
    public void should_return_correct_score_when_advantage() {
        // given
        Player federer = new Player("Federer");
        Player nadal = new Player("Nadal");
        ScorePlayer scoreFederer = new ScorePlayer(7, 4, 0,0,1);
        ScorePlayer scoreNadal = new ScorePlayer(6, 3,0,0, 1);
        Score score = new Score(scoreFederer, scoreNadal);
        Match match = new Match(federer, nadal, score);
        // when
        ScoreDisplayHandler scoreDisplayHandler = new ScoreDisplayHandler();
        ScoreDisplayed scoreDisplayed = scoreDisplayHandler.show(match);
        // then
        assertThat(scoreDisplayed.getGamesWonByPlayer1().get(0), is(4));
        assertThat(scoreDisplayed.getGamesWonByPlayer2().get(0), is(3));
        assertThat(scoreDisplayed.getPointsWonByPlayer1(), is(EnumPoint.ADVANTAGE.getScore()));
        assertThat(scoreDisplayed.getPointsWonByPlayer2(), is(EnumPoint.FORTY.getScore()));
    }

    @Test
    public void should_return_correct_score_when_deuce() {
        // given
        Player federer = new Player("Federer");
        Player nadal = new Player("Nadal");
        ScorePlayer scoreFederer = new ScorePlayer(9, 4,0,0, 1);
        ScorePlayer scoreNadal = new ScorePlayer(9, 3,0,0, 1);
        Score score = new Score(scoreFederer, scoreNadal);
        Match match = new Match(federer, nadal, score);
        // when
        ScoreDisplayHandler scoreDisplayHandler = new ScoreDisplayHandler();
        ScoreDisplayed scoreDisplayed = scoreDisplayHandler.show(match);
        // then
        assertThat(scoreDisplayed.getGamesWonByPlayer1().get(0), is(4));
        assertThat(scoreDisplayed.getGamesWonByPlayer2().get(0), is(3));
        assertThat(scoreDisplayed.getPointsWonByPlayer1(), is("40"));
        assertThat(scoreDisplayed.getPointsWonByPlayer2(), is("40"));
    }

    @Test
    public void should_return_correct_score_when_normal_score() {
        // given
        Player federer = new Player("Federer");
        Player nadal = new Player("Nadal");
        ScorePlayer scoreFederer = new ScorePlayer(3, 4,0,0, 1);
        ScorePlayer scoreNadal = new ScorePlayer(1, 3,0,0, 1);
        Score score = new Score(scoreFederer, scoreNadal);
        Match match = new Match(federer, nadal, score);
        // when
        ScoreDisplayHandler scoreDisplayHandler = new ScoreDisplayHandler();
        ScoreDisplayed scoreDisplayed = scoreDisplayHandler.show(match);
        // then
        assertThat(scoreDisplayed.getGamesWonByPlayer1().get(0), is(4));
        assertThat(scoreDisplayed.getGamesWonByPlayer2().get(0), is(3));
        assertThat(scoreDisplayed.getPointsWonByPlayer1(), is("40"));
        assertThat(scoreDisplayed.getPointsWonByPlayer2(), is("15"));
    }
}
