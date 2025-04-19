package org.example.models.game_structure;

import org.example.models.game_structure.weathers.Weather;
import org.example.models.interactions.Player;

import java.util.ArrayList;

public class Game {

    private DateTime dateTime;
    private Weather weather;
    private Tomorrow tomorrow;
    private final ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private Map map;

    // A Function to change game base of the cycle of players and moves the game forward
    public void gameFlow() {
        //TODO
    }

}
