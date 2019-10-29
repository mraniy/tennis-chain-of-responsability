package kata.tennis;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;
import com.kata.tennis.model.Score;
import com.kata.tennis.model.ScorePlayer;
import com.kata.tennis.service.PointHander;
import com.kata.tennis.service.UnitScoreHandler;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;



public class TestPointHandler {

    @Test
    public void should_update_match_with_correct_values() {
        // given
        Player federer = new Player("Federer");
        Player nadal = new Player("Nadal");
        int numberPointsOfGameWonByFederer = 1;
        int numberPointsOfGameWonByNadal = 3;
        ScorePlayer scoreFederer = new ScorePlayer(numberPointsOfGameWonByFederer,4,0,0,1);
        ScorePlayer scoreNadal = new ScorePlayer(numberPointsOfGameWonByNadal,2,0,0,1);
        Score score = new Score(scoreFederer, scoreNadal);
        Match match = new Match(federer, nadal,score);
        // when
        UnitScoreHandler pointHandler = new PointHander();
        Match matchAfterRefresh = pointHandler.refreshScore(match, federer);
        // then
        assertThat(matchAfterRefresh.getScore().getScorePlayer1().getNumberPointsOfGameWonByPlayer(), is(2));
    }
}
