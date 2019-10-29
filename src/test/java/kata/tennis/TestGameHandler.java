package kata.tennis;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;
import com.kata.tennis.model.Score;
import com.kata.tennis.model.ScorePlayer;
import com.kata.tennis.service.GameHandler;
import com.kata.tennis.service.UnitScoreHandler;
import org.junit.Test;

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
        ScorePlayer scoreFederer = new ScorePlayer(numberPointsOfGameWonByFederer, 4, 0,0,1);

        ScorePlayer scoreNadal = new ScorePlayer(numberPointsOfGameWonByNadal, 2, 0,0,1);
        Score score = new Score(scoreFederer, scoreNadal);
        Match match = new Match(federer, nadal, score);
        // when
        UnitScoreHandler gameHandler = new GameHandler();
        Match matchAfterRefresh = gameHandler.refreshScore(match, nadal);
        // then
        assertThat(matchAfterRefresh.getScore().getScorePlayer2().getNumberGamesWonByPlayerSet1(), is(3));
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
        ScorePlayer scoreFederer = new ScorePlayer(8, numberGamesWonByFederer,0,0, 1);
        ScorePlayer scoreNadal = new ScorePlayer(6, numberGamesWonByNadal, 0,0,1);
        Score score = new Score(scoreFederer, scoreNadal);
        Match match = new Match(federer, nadal, score);
        // when
        UnitScoreHandler gameHandler = new GameHandler();
        Match matchAfterRefresh = gameHandler.refreshScore(match, federer);
        // then
        assertThat(matchAfterRefresh.getScore().getScorePlayer1().getNumberGamesWonByPlayerSet1(), is(5));
        assertThat(matchAfterRefresh.getScore().getScorePlayer1().getNumberPointsOfGameWonByPlayer(), is(0));
        assertThat(matchAfterRefresh.getScore().getScorePlayer2().getNumberPointsOfGameWonByPlayer(), is(0));
    }


}
