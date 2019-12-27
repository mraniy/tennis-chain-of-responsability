package kata.tennis;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;
import com.kata.tennis.model.ScorePlayer;
import com.kata.tennis.service.TieBreakHandler;
import com.kata.tennis.service.UnitScoreHandler;
import org.junit.Test;


import static kata.tennis.DataFactory.aScore;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
public class TestTieBreakHandler {

    @Test
    public void should_update_game_with_correct_values_when_player_win_the_game_without_advantage() {
        // given
        int numberPointsOfGameWonByFederer = 3;
        int numberPointsOfGameWonByNadal = 7;
        ScorePlayer scoreFederer = aScore(numberPointsOfGameWonByFederer, 1, 6);
        ScorePlayer scoreNadal = aScore(numberPointsOfGameWonByNadal, 1, 6);
        Player federer = new Player("Federer",scoreFederer);
        Player nadal = new Player("Nadal",scoreNadal);

        Match match = new Match(federer, nadal);
        // when
        UnitScoreHandler gameHandler = new TieBreakHandler();
        gameHandler.refreshScore(match, nadal);
        // then
        assertThat(match.getPlayer2().getScorePlayer().getNumberGamesWonByPlayerBySet().get(0).getGames(), is(7));
        assertThat(match.getPlayer1().getScorePlayer().getNumberPointsOfGameWonByPlayer(), is(0));
        assertThat(match.getPlayer2().getScorePlayer().getNumberPointsOfGameWonByPlayer(), is(0));
    }

    @Test
    public void should_update_game_with_correct_values_when_player_win_the_game_with_advantage() {
        // given


        int numberGamesWonByFederer = 6;
        int numberGamesWonByNadal = 6;
        ScorePlayer scoreFederer = aScore(10, 1, numberGamesWonByFederer);
        ScorePlayer scoreNadal = aScore(8, 1, numberGamesWonByNadal);
        Player federer = new Player("Federer",scoreFederer);
        Player nadal = new Player("Nadal",scoreNadal);
        Match match = new Match(federer, nadal);
        // when
        UnitScoreHandler gameHandler = new TieBreakHandler();
        gameHandler.refreshScore(match, federer);
        // then
        assertThat(match.getPlayer1().getScorePlayer().getNumberGamesWonByPlayerBySet().get(0).getGames(), is(7));
        assertThat(match.getPlayer1().getScorePlayer().getNumberPointsOfGameWonByPlayer(), is(0));
        assertThat(match.getPlayer2().getScorePlayer().getNumberPointsOfGameWonByPlayer(), is(0));
    }
}
