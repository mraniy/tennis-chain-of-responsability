package kata.tennis;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;
import com.kata.tennis.model.ScorePlayer;
import com.kata.tennis.service.SetHandler;
import com.kata.tennis.service.UnitScoreHandler;
import org.junit.Test;
import static kata.tennis.DataFactory.aScore;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestSetHandler {

    @Test
    public void should_update_set_with_correct_values_when_player_win_the_set_without_tiebreak() {
        // given

        ScorePlayer scoreFederer = aScore(0, 1, 3);
        ScorePlayer scoreNadal = aScore(0, 1, 6);
        Player federer = new Player("Federer",scoreFederer);
        Player nadal = new Player("Nadal",scoreNadal);
        Match match = new Match(federer, nadal);
        // when
        UnitScoreHandler setHandler = new SetHandler();
        setHandler.refreshScore(match, nadal);
        // then
        assertThat(match.getPlayer2().getScorePlayer().getNumberSetWonByPlayer(), is(2));
    }

    @Test
    public void should_update_set_with_correct_values_when_player_win_the_set_with_tiebreak() {
        // given

        ScorePlayer scoreFederer = aScore(0, 1, 7);
        ScorePlayer scoreNadal = aScore(0, 1, 6);
        Player federer = new Player("Federer",scoreFederer);
        Player nadal = new Player("Nadal",scoreNadal);
        Match match = new Match(federer, nadal);
        // when
        UnitScoreHandler setHandler = new SetHandler();
        setHandler.refreshScore(match, federer);
        // then
        assertThat(match.getPlayer1().getScorePlayer().getNumberSetWonByPlayer(), is(2));
    }
}