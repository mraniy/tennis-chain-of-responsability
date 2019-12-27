package kata.tennis;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;
import com.kata.tennis.model.ScorePlayer;
import com.kata.tennis.service.MatchHandler;
import com.kata.tennis.service.UnitScoreHandler;
import org.junit.Test;

import java.util.Optional;

import static kata.tennis.DataFactory.aScore;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestMatchHandler {

    @Test
    public void should_player1_win_the_match_when_player1_reach_two_sets() {
        // given

        ScorePlayer scoreFederer = aScore(0, 2, 0);
        ScorePlayer scoreNadal = aScore(0, 1, 0);
        Player federer = new Player("Federer",scoreFederer);
        Player nadal = new Player("Nadal",scoreNadal);

        Match match = new Match(federer, nadal);
        // when
        UnitScoreHandler matchHandler = new MatchHandler();
        matchHandler.refreshScore(match, federer);
        // then
        assertThat(match.getWinner(), is(Optional.of("Federer")));
    }



    @Test
    public void should_player2_win_the_match_when_player2_reach_two_sets() {
        // given
        ScorePlayer scoreFederer = aScore(0, 0, 0);
        ScorePlayer scoreNadal = aScore(0, 2, 0);
        Player federer = new Player("Federer",scoreFederer);
        Player nadal = new Player("Nadal",scoreNadal);
        Match match = new Match(federer, nadal);
        // when
        UnitScoreHandler matchHandler = new MatchHandler();
        matchHandler.refreshScore(match, nadal);
        // then
        assertThat(match.getWinner(), is(Optional.of("Nadal")));
    }
}
