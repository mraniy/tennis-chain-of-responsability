package kata.tennis;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;
import com.kata.tennis.model.Score;
import com.kata.tennis.model.ScorePlayer;
import com.kata.tennis.service.TieBreakHandler;
import com.kata.tennis.service.UnitScoreHandler;
import org.junit.Test;

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
        ScorePlayer scoreFederer = new ScorePlayer(numberPointsOfGameWonByFederer, 6, 0,0,1);

        ScorePlayer scoreNadal = new ScorePlayer(numberPointsOfGameWonByNadal, 6, 0,0,1);
        Score score = new Score(scoreFederer, scoreNadal);
        Match match = new Match(federer, nadal, score);
        // when
        UnitScoreHandler gameHandler = new TieBreakHandler();
        Match matchAfterRefresh = gameHandler.refreshScore(match, nadal);
        // then
        assertThat(matchAfterRefresh.getScore().getScorePlayer2().getNumberGamesWonByPlayerSet1(), is(7));
        assertThat(matchAfterRefresh.getScore().getScorePlayer1().getNumberPointsOfGameWonByPlayer(), is(0));
        assertThat(matchAfterRefresh.getScore().getScorePlayer2().getNumberPointsOfGameWonByPlayer(), is(0));
    }

    @Test
    public void should_update_game_with_correct_values_when_player_win_the_game_with_advantage() {
        // given
        Player federer = new Player("Federer");
        Player nadal = new Player("Nadal");

        int numberGamesWonByFederer = 6;
        int numberGamesWonByNadal = 6;
        ScorePlayer scoreFederer = new ScorePlayer(10, numberGamesWonByFederer,0,0, 1);
        ScorePlayer scoreNadal = new ScorePlayer(8, numberGamesWonByNadal, 0,0,1);
        Score score = new Score(scoreFederer, scoreNadal);
        Match match = new Match(federer, nadal, score);
        // when
        UnitScoreHandler gameHandler = new TieBreakHandler();
        Match matchAfterRefresh = gameHandler.refreshScore(match, federer);
        // then
        assertThat(matchAfterRefresh.getScore().getScorePlayer1().getNumberGamesWonByPlayerSet1(), is(7));
        assertThat(matchAfterRefresh.getScore().getScorePlayer1().getNumberPointsOfGameWonByPlayer(), is(0));
        assertThat(matchAfterRefresh.getScore().getScorePlayer2().getNumberPointsOfGameWonByPlayer(), is(0));
    }
}
