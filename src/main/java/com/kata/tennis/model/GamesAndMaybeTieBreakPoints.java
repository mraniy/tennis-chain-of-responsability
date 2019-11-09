package com.kata.tennis.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@AllArgsConstructor
public class GamesAndMaybeTieBreakPoints {

    private AtomicInteger games;
    private Optional<AtomicInteger> tieBreakPoints = Optional.empty();
}
