package kata.tennis;

import com.kata.tennis.model.*;
import com.kata.tennis.service.ScoreDisplayHandler;
import com.kata.tennis.model.ScoreDisplayed;
import org.junit.Test;

import static kata.tennis.DataFactory.aScore;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestScoreDisplayHandler {

    @Test
    public void should_return_correct_score_when_advantage() {
        // given

        ScorePlayer scoreFederer =  aScore(7, 1, 4);
        ScorePlayer scoreNadal = aScore(6, 1, 3);
        Player federer = new Player("Federer",scoreFederer);
        Player nadal = new Player("Nadal",scoreNadal);
        Match match = new Match(federer, nadal);
        // when
        ScoreDisplayHandler scoreDisplayHandler = new ScoreDisplayHandler();
        ScoreDisplayed scoreDisplayed = scoreDisplayHandler.show(match);
        // then
        assertThat(scoreDisplayed.getGamesWonByPlayer1().get(0).getGames(), is(4));
        assertThat(scoreDisplayed.getGamesWonByPlayer2().get(0).getGames(), is(3));
        assertThat(scoreDisplayed.getPointsWonByPlayer1(), is(EnumPoint.ADVANTAGE.getScore()));
        assertThat(scoreDisplayed.getPointsWonByPlayer2(), is(EnumPoint.FORTY.getScore()));
    }

    @Test
    public void should_return_correct_score_when_deuce() {
        // given

        ScorePlayer scoreFederer = aScore(9, 1, 4);
        ScorePlayer scoreNadal = aScore(9, 1, 3);
        Player federer = new Player("Federer",scoreFederer);
        Player nadal = new Player("Nadal",scoreNadal);
        Match match = new Match(federer, nadal);
        // when
        ScoreDisplayHandler scoreDisplayHandler = new ScoreDisplayHandler();
        ScoreDisplayed scoreDisplayed = scoreDisplayHandler.show(match);
        // then
        assertThat(scoreDisplayed.getGamesWonByPlayer1().get(0).getGames(), is(4));
        assertThat(scoreDisplayed.getGamesWonByPlayer2().get(0).getGames(), is(3));
        assertThat(scoreDisplayed.getPointsWonByPlayer1(), is(EnumPoint.FORTY.getScore()));
        assertThat(scoreDisplayed.getPointsWonByPlayer2(), is(EnumPoint.FORTY.getScore()));
    }

    @Test
    public void should_return_correct_score_when_normal_score() {
        // given

        ScorePlayer scoreFederer = aScore(3, 1, 4);
        ScorePlayer scoreNadal = aScore(1, 1, 3);
        Player federer = new Player("Federer",scoreFederer);
        Player nadal = new Player("Nadal",scoreNadal);
        Match match = new Match(federer, nadal);
        // when
        ScoreDisplayHandler scoreDisplayHandler = new ScoreDisplayHandler();
        ScoreDisplayed scoreDisplayed = scoreDisplayHandler.show(match);
        // then
        assertThat(scoreDisplayed.getGamesWonByPlayer1().get(0).getGames(), is(4));
        assertThat(scoreDisplayed.getGamesWonByPlayer2().get(0).getGames(), is(3));
        assertThat(scoreDisplayed.getPointsWonByPlayer1(), is(EnumPoint.FORTY.getScore()));
        assertThat(scoreDisplayed.getPointsWonByPlayer2(), is(EnumPoint.FIFTEEN.getScore()));
    }
}
