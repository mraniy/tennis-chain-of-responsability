package com.kata.tennis.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScorePlayer {

    private int numberPointsOfGameWonByPlayer;
    private int numberGamesWonByPlayerSet1;
    private int numberGamesWonByPlayerSet2;
    private int numberGamesWonByPlayerSet3;
    private int numberSetWonByPlayer;

    public ScorePlayer() {
        numberPointsOfGameWonByPlayer = 0;
        numberGamesWonByPlayerSet1 = 0;
        numberGamesWonByPlayerSet2 = 0;
        numberGamesWonByPlayerSet3 = 0;
        numberSetWonByPlayer = 0;
    }

    public int getNumberOfGamesWonsByPlayerForCurrentSet(int currentSet) {
        if(currentSet ==1) return numberGamesWonByPlayerSet1;
        else if(currentSet == 2) return numberGamesWonByPlayerSet2;
        else return numberGamesWonByPlayerSet3;

    }

}
