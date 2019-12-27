package com.kata.tennis.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;

@Data
@AllArgsConstructor
public class GamesAndMaybeTieBreakPoints {

    private Integer games;
    private Optional<Integer> tieBreakPoints = Optional.empty();
}
