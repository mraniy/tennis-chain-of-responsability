package kata.tennis;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;
import com.kata.tennis.model.Score;
import com.kata.tennis.model.ScorePlayer;
import com.kata.tennis.service.TieBreakHandler;
import com.kata.tennis.service.UnitScoreHandler;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static kata.tennis.DataFactory.aScore;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
public class TestTieBreakHandler {

    @Test
    public void should_update_game_with_correct_values_when_player_win_the_game_without_advantage() {
        // given
        Player federer = new Player("Federer");
        Player nadal = new Player("Nadal");


        int numberPointsOfGameWonByFederer = 3;
        int numberPointsOfGameWonByNadal = 7;
        ScorePlayer scoreFederer = aScore(numberPointsOfGameWonByFederer, 1, new AtomicInteger(6));
        ScorePlayer scoreNadal = aScore(numberPointsOfGameWonByNadal, 1, new AtomicInteger(6));

        Score score = new Score(scoreFederer, scoreNadal);
        Match match = new Match(federer, nadal, score);
        // when
        UnitScoreHandler gameHandler = new TieBreakHandler();
        gameHandler.refreshScore(match, nadal);
        // then
        assertThat(match.getScore().getScorePlayer2().getNumberGamesWonByPlayerBySet().get(0).getGames().get(), is(7));
        assertThat(match.getScore().getScorePlayer1().getNumberPointsOfGameWonByPlayer(), is(0));
        assertThat(match.getScore().getScorePlayer2().getNumberPointsOfGameWonByPlayer(), is(0));
    }

    @Test
    public void should_update_game_with_correct_values_when_player_win_the_game_with_advantage() {
        // given
        Player federer = new Player("Federer");
        Player nadal = new Player("Nadal");

        int numberGamesWonByFederer = 6;
        int numberGamesWonByNadal = 6;
        ScorePlayer scoreFederer = aScore(10, 1, new AtomicInteger(numberGamesWonByFederer));
        ScorePlayer scoreNadal = aScore(8, 1, new AtomicInteger(numberGamesWonByNadal));
        Score score = new Score(scoreFederer, scoreNadal);
        Match match = new Match(federer, nadal, score);
        // when
        UnitScoreHandler gameHandler = new TieBreakHandler();
        gameHandler.refreshScore(match, federer);
        // then
        assertThat(match.getScore().getScorePlayer1().getNumberGamesWonByPlayerBySet().get(0).getGames().get(), is(7));
        assertThat(match.getScore().getScorePlayer1().getNumberPointsOfGameWonByPlayer(), is(0));
        assertThat(match.getScore().getScorePlayer2().getNumberPointsOfGameWonByPlayer(), is(0));
    }
}
