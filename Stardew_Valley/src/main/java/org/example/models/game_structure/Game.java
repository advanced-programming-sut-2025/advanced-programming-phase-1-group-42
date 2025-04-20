package org.example.models.game_structure;

import org.example.models.game_structure.weathers.Weather;
import org.example.models.interactions.Player;

import java.util.ArrayList;

import org.example.models.game_structure.Map;
import org.example.models.interactions.Player;
import org.example.models.interactions.User;

import java.util.ArrayList;

public class Game {

    private DateTime dateTime;
    private Weather weather;
    private Tomorrow tomorrow;
    private final ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private Map map;
    private User gameCreator;
    private Map CurrentMap = null;
    private Player currentPlayingPlayer;
    private int counter = 0;

    // A Function to change game base of the cycle of players and moves the game forward
    public void gameFlow() {
        //TODO
    }

    public Game(User gameCreator) {
        this.gameCreator = gameCreator;
    }

    public void setCurrentMap(Map map) {
        CurrentMap = map;
    }

    public Map getCurrentMap() {
        return CurrentMap;
    }

    public User getGameCreator() {
        return gameCreator;
    }

    public void setGameCreator(User gameCreator) {
        this.gameCreator = gameCreator;
    }

    public Player getCurrentPlayingPlayer() {
        return currentPlayingPlayer;
    }

    public void setCurrentPlayingPlayer(Player currentPlayingPlayer) {
        this.currentPlayingPlayer = currentPlayingPlayer;
    }

    public void nextPlayer() {
        counter++;
        if (counter >= players.size()) {
            counter = 0;
        }
        currentPlayingPlayer = players.get(counter);
    }

}
