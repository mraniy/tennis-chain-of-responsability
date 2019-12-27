package kata.tennis;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;
import com.kata.tennis.model.ScorePlayer;
import com.kata.tennis.service.PointHander;
import com.kata.tennis.service.UnitScoreHandler;
import org.junit.Test;


import static kata.tennis.DataFactory.aScore;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;



public class TestPointHandler {

    @Test
    public void should_update_match_with_correct_values() {
        // given

        int numberPointsOfGameWonByFederer = 1;
        int numberPointsOfGameWonByNadal = 3;

        ScorePlayer scoreFederer = aScore(numberPointsOfGameWonByFederer, 1, 4);
        ScorePlayer scoreNadal = aScore(numberPointsOfGameWonByNadal, 1, 2);
        Player federer = new Player("Federer",scoreFederer);
        Player nadal = new Player("Nadal",scoreNadal);
        Match match = new Match(federer, nadal);
        // when
        UnitScoreHandler pointHandler = new PointHander();
        pointHandler.refreshScore(match, federer);
        // then
        assertThat(match.getPlayer1().getScorePlayer().getNumberPointsOfGameWonByPlayer(), is(2));
    }
}
