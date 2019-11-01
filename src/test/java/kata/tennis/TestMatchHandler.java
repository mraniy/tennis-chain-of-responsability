package kata.tennis;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;
import com.kata.tennis.model.Score;
import com.kata.tennis.model.ScorePlayer;
import com.kata.tennis.service.MatchHandler;
import com.kata.tennis.service.UnitScoreHandler;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestMatchHandler {

    @Test
    public void should_player1_win_the_match_when_player1_reach_two_sets() {
        // given
        Player federer = new Player("Federer");
        Player nadal = new Player("Nadal");
        ScorePlayer scoreFederer = new ScorePlayer(0, 0,0,0, 2);
        ScorePlayer scoreNadal = new ScorePlayer(0, 0, 0,0,1);
        Score score = new Score(scoreFederer, scoreNadal);
        Match match = new Match(federer, nadal, score);
        // when
        UnitScoreHandler matchHandler = new MatchHandler();
        Match matchAfterRefresh = matchHandler.refreshScore(match, federer);
        // then
        assertThat(matchAfterRefresh.getWinner(), is(Optional.of("Federer")));
    }



    @Test
    public void should_player2_win_the_match_when_player2_reach_two_sets() {
        // given
        Player federer = new Player("Federer");
        Player nadal = new Player("Nadal");

        ScorePlayer scoreFederer = new ScorePlayer(0, 0,0,0, 0);
        ScorePlayer scoreNadal = new ScorePlayer(0, 0,0,0, 2);
        Score score = new Score(scoreFederer, scoreNadal);
        Match match = new Match(federer, nadal, score);
        // when
        UnitScoreHandler matchHandler = new MatchHandler();
        Match matchAfterRefresh = matchHandler.refreshScore(match, nadal);
        // then
        assertThat(matchAfterRefresh.getWinner(), is(Optional.of("Nadal")));
    }
}
