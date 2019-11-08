package com.kata.tennis.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@AllArgsConstructor
public class ScorePlayer {

    private int numberPointsOfGameWonByPlayer;
    private LinkedList<AtomicInteger> numberGamesWonByPlayerBySet;

    private int numberSetWonByPlayer;

    public ScorePlayer() {
        numberPointsOfGameWonByPlayer = 0;
        numberGamesWonByPlayerBySet = new LinkedList<>();
        numberGamesWonByPlayerBySet.add(new AtomicInteger(0));
        numberSetWonByPlayer = 0;
    }

    public AtomicInteger getNumberOfGamesWonsByPlayerForCurrentSet(int currentSet) {
        AtomicInteger atomicInteger= null;
        try {
             atomicInteger = numberGamesWonByPlayerBySet.get(currentSet - 1);
        } catch(IndexOutOfBoundsException e) {
            System.out.println(currentSet);
            e.printStackTrace();
        }
        return atomicInteger;
    }

}
