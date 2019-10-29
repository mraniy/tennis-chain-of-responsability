package kata.tennis;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;
import com.kata.tennis.model.Score;
import com.kata.tennis.model.ScorePlayer;
import com.kata.tennis.service.SetHandler;
import com.kata.tennis.service.UnitScoreHandler;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestSetHandler {

    @Test
    public void should_update_set_with_correct_values_when_player_win_the_set_without_tiebreak() {
        // given
        Player federer = new Player("Federer");
        Player nadal = new Player("Nadal");
        ScorePlayer scoreFederer = new ScorePlayer(0, 3,0,0, 1);
        ScorePlayer scoreNadal = new ScorePlayer(0, 6, 0,0,1);
        Score score = new Score(scoreFederer, scoreNadal);
        Match match = new Match(federer, nadal, score);
        // when
        UnitScoreHandler setHandler = new SetHandler();
        Match matchAfterRefresh = setHandler.refreshScore(match, nadal);
        // then
        assertThat(matchAfterRefresh.getScore().getScorePlayer2().getNumberSetWonByPlayer(), is(2));
    }

    @Test
    public void should_update_set_with_correct_values_when_player_win_the_set_with_tiebreak() {
        // given
        Player federer = new Player("Federer");
        Player nadal = new Player("Nadal");
        ScorePlayer scoreFederer = new ScorePlayer(0, 7,0,0, 1);
        ScorePlayer scoreNadal = new ScorePlayer(0, 6, 0,0,1);
        Score score = new Score(scoreFederer, scoreNadal);
        Match match = new Match(federer, nadal, score);
        // when
        UnitScoreHandler setHandler = new SetHandler();
        Match matchAfterRefresh = setHandler.refreshScore(match, federer);
        // then
        assertThat(matchAfterRefresh.getScore().getScorePlayer1().getNumberSetWonByPlayer(), is(2));
    }
}