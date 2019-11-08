package kata.tennis;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;
import com.kata.tennis.model.Score;
import com.kata.tennis.model.ScorePlayer;
import com.kata.tennis.service.GameHandler;
import com.kata.tennis.service.UnitScoreHandler;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static kata.tennis.DataFactory.aScore;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestGameHandler {

    @Test
    public void should_update_game_with_correct_values_when_player_win_the_game_without_advantage() {
        // given
        Player federer = new Player("Federer");
        Player nadal = new Player("Nadal");


        int numberPointsOfGameWonByFederer = 1;
        int numberPointsOfGameWonByNadal = 4;

        ScorePlayer scoreFederer = aScore(numberPointsOfGameWonByFederer, 1, new AtomicInteger(4));
        ScorePlayer scoreNadal = aScore(numberPointsOfGameWonByNadal, 2, new AtomicInteger(2));
        Score score = new Score(scoreFederer, scoreNadal);
        Match match = new Match(federer, nadal, score);
        // when
        UnitScoreHandler gameHandler = new GameHandler();
        Match matchAfterRefresh = gameHandler.refreshScore(match, nadal);
        // then
        assertThat(matchAfterRefresh.getScore().getScorePlayer2().getNumberGamesWonByPlayerBySet().get(0).get(), is(3));
        assertThat(matchAfterRefresh.getScore().getScorePlayer1().getNumberPointsOfGameWonByPlayer(), is(0));
        assertThat(matchAfterRefresh.getScore().getScorePlayer2().getNumberPointsOfGameWonByPlayer(), is(0));
    }

    @Test
    public void should_update_game_with_correct_values_when_player_win_the_game_with_advantage() {
        // given
        Player federer = new Player("Federer");
        Player nadal = new Player("Nadal");

        int numberGamesWonByFederer = 4;
        int numberGamesWonByNadal = 2;

        ScorePlayer scoreFederer = aScore(8, 1, new AtomicInteger(numberGamesWonByFederer));
        ScorePlayer scoreNadal = aScore(6, 1, new AtomicInteger(numberGamesWonByNadal));
        Score score = new Score(scoreFederer, scoreNadal);
        Match match = new Match(federer, nadal, score);
        // when
        UnitScoreHandler gameHandler = new GameHandler();
        Match matchAfterRefresh = gameHandler.refreshScore(match, federer);
        // then
        assertThat(matchAfterRefresh.getScore().getScorePlayer1().getNumberGamesWonByPlayerBySet().get(0).get(), is(5));
        assertThat(matchAfterRefresh.getScore().getScorePlayer1().getNumberPointsOfGameWonByPlayer(), is(0));
        assertThat(matchAfterRefresh.getScore().getScorePlayer2().getNumberPointsOfGameWonByPlayer(), is(0));
    }


}
