package com.kata.tennis.service;



import com.kata.tennis.model.Match;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

    public Match initMatch(String namePlayer1, String namePlayer2) {
        return null;
    }

    public void refreshScore(Match match, String whoWonThePoint) {
        // qui a gagné le point
        // ajouter un point au joueur concerné
        // est ce que le point permets de gagner un jeu
        // est ce que le point permets de gagner un set
        // est ce que le point permets de gagner un match
        // est ce que le point permets d'initialiser un tie break

    }
}
