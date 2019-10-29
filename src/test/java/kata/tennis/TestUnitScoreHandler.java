package kata.tennis;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;
import com.kata.tennis.model.Score;
import com.kata.tennis.model.ScorePlayer;
import com.kata.tennis.service.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestUnitScoreHandler {

    UnitScoreHandler pointHandler = new PointHander();
    UnitScoreHandler gameHandler = new GameHandler();
    UnitScoreHandler tieBreakHandler = new TieBreakHandler();
    UnitScoreHandler setHandler = new SetHandler();
    UnitScoreHandler MatchHandler = new MatchHandler();

    @Before
    public void setUp() {
        pointHandler.setNext(gameHandler);
        gameHandler.setNext(tieBreakHandler);
        tieBreakHandler.setNext(setHandler);
        setHandler.setNext(MatchHandler);

    }

    @Test
    public void should_determine_match_winner_correctly() {
        // given
        Player federer = new Player("Federer");
        Player nadal = new Player("Nadal");


        ScorePlayer scoreFederer = new ScorePlayer(3, 5,0,0, 1);
        ScorePlayer scoreNadal = new ScorePlayer(2, 4,0,0, 1);
        Score score = new Score(scoreFederer, scoreNadal);
        Match match = new Match(federer, nadal, score);
        // when

        pointHandler.proceed(match, "Federer");
        // then
        assertThat(match.getWinner() , is("Federer"));
    }

    @Test
    public void should_determine_set_winner_correctly() {
        // given
        Player federer = new Player("Federer");
        Player nadal = new Player("Nadal");


        ScorePlayer scoreFederer = new ScorePlayer(7, 4,0,0, 0);
        ScorePlayer scoreNadal = new ScorePlayer(8, 5, 0,0,0);
        Score score = new Score(scoreFederer, scoreNadal);
        Match match = new Match(federer, nadal, score);
        // when

        pointHandler.proceed(match, "Nadal");
        // then
        assertThat(match.getScore().getScorePlayer2().getNumberSetWonByPlayer() , is(1));
    }
}
