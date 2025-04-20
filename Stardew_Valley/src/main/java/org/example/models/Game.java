package org.example.models;

import org.example.models.game_structure.Map;
import org.example.models.interactions.Player;
import org.example.models.interactions.User;

import java.util.ArrayList;

public class Game {
    private final ArrayList<Player> players = new ArrayList<>();
    private User gameCreator;
    private Map CurrentMap;
    private Player currentPlayingPlayer;
    private int counter = 0;

    public Game(User gameCreator) {
        this.gameCreator = gameCreator;
    }

    public void setCurrentMap(Map map) {
        CurrentMap = map;
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
