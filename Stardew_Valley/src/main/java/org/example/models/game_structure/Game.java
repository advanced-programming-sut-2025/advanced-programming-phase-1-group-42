package org.example.models.game_structure;

import org.example.models.game_structure.weathers.Weather;
import org.example.models.interactions.Player;

import java.util.ArrayList;

import org.example.models.interactions.User;

public class Game {

    private DateTime dateTime;
    private Weather weather;
    private Tomorrow tomorrow;
    private final ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private User gameAdmin;
    private Map CurrentMap = null;
    private Player currentPlayingPlayer;
    private int counter = 0;


    public Game(User gameAdmin) {
        this.gameAdmin = gameAdmin;
    }

    public void setCurrentMap(Map map) {
        CurrentMap = map;
    }

    public Map getCurrentMap() {
        return CurrentMap;
    }

    public User getGameAdmin() {
        return gameAdmin;
    }

    public void setGameAdmin(User gameAdmin) {
        this.gameAdmin = gameAdmin;
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

    public Map getMap() {
        return this.getCurrentMap();
    }

    public DateTime getDateTime() {
        return dateTime;
    }

}
