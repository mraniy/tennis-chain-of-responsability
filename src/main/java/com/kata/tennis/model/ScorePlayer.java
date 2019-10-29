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


    public int getNumberOfGamesWonsByPlayerForCurrentSet(int currentSet) {
        if(currentSet ==1) return numberGamesWonByPlayerSet1;
        else if(currentSet == 2) return numberGamesWonByPlayerSet2;
        else return numberGamesWonByPlayerSet3;

    }

}
