package kata.tennis;

import com.kata.tennis.model.Match;
import com.kata.tennis.model.Player;

import com.kata.tennis.model.ScorePlayer;
import com.kata.tennis.service.GameHandler;
import com.kata.tennis.service.UnitScoreHandler;
import org.junit.Test;


import static kata.tennis.DataFactory.aScore;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestGameHandler {

    @Test
    public void should_update_game_with_correct_values_when_player_win_the_game_without_advantage() {
        // given



        int numberPointsOfGameWonByFederer = 1;
        int numberPointsOfGameWonByNadal = 4;

        ScorePlayer scoreFederer = aScore(numberPointsOfGameWonByFederer, 1, 4);
        ScorePlayer scoreNadal = aScore(numberPointsOfGameWonByNadal, 2, 2);
        Player federer = new Player("Federer",scoreFederer);
        Player nadal = new Player("Nadal",scoreNadal);
        Match match = new Match(federer, nadal);
        // when
        UnitScoreHandler gameHandler = new GameHandler();
        gameHandler.refreshScore(match, nadal);
        // then
        assertThat(match.getPlayer2().getScorePlayer().getNumberGamesWonByPlayerBySet().get(0).getGames(), is(3));
        assertThat(match.getPlayer1().getScorePlayer().getNumberPointsOfGameWonByPlayer(), is(0));
        assertThat(match.getPlayer2().getScorePlayer().getNumberPointsOfGameWonByPlayer(), is(0));
    }

    @Test
    public void should_update_game_with_correct_values_when_player_win_the_game_with_advantage() {
        // given
        int numberGamesWonByFederer = 4;
        int numberGamesWonByNadal = 2;

        ScorePlayer scoreFederer = aScore(8, 1, numberGamesWonByFederer);
        ScorePlayer scoreNadal = aScore(6, 1, numberGamesWonByNadal);
        Player federer = new Player("Federer",scoreFederer);
        Player nadal = new Player("Nadal",scoreNadal);
        Match match = new Match(federer, nadal);
        // when
        UnitScoreHandler gameHandler = new GameHandler();
        gameHandler.refreshScore(match, federer);
        // then
        assertThat(match.getPlayer1().getScorePlayer().getNumberGamesWonByPlayerBySet().get(0).getGames(), is(5));
        assertThat(match.getPlayer1().getScorePlayer().getNumberPointsOfGameWonByPlayer(), is(0));
        assertThat(match.getPlayer2().getScorePlayer().getNumberPointsOfGameWonByPlayer(), is(0));
    }


}
