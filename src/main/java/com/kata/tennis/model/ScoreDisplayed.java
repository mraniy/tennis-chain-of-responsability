package com.kata.tennis.model;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class ScoreDisplayed {

    private LinkedList<AtomicInteger> gamesWonByPlayer1;

    private LinkedList<AtomicInteger> gamesWonByPlayer2;

    private LinkedList<AtomicInteger> setsWonByPlayer1;

    private LinkedList<AtomicInteger> setsWonByPlayer2;

    private String pointsWonByPlayer1;

    private String pointsWonByPlayer2;

}
