package org.example.models.game_structure;

import org.example.models.game_structure.weathers.Weather;
import org.example.models.interactions.NPCs.NPC;
import org.example.models.interactions.Player;

import java.util.ArrayList;

public class Game {

    private DateTime dateTime;
    private Weather weather;
    private Tomorrow tomorrow;
    private final ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private Player gameAdmin;
    private Map CurrentMap = null;
    private Player currentPlayingPlayer;
    private final ArrayList<NPC> NPCs = new ArrayList<>();
    private int counter = 0;

    public void setPlayers(ArrayList<Player> players) {
        this.players.addAll(players);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setCurrentMap(Map map) {
        CurrentMap = map;
    }

    public Map getCurrentMap() {
        return CurrentMap;
    }

    public Player getGameAdmin() {
        return gameAdmin;
    }

    public void setGameAdmin(Player gameAdmin) {
        this.gameAdmin = gameAdmin;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void nextPlayer() {
        counter++;
        if (counter >= players.size()) {
            counter = 0;
            this.dateTime.timeFlow();
        }
        currentPlayer = players.get(counter);
    }

    public Map getMap() {
        return this.getCurrentMap();
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public String getWeatherName() {return weather.getName();}

    public Weather getWeather() {return weather;}

    public void cheatSetWeather(Weather weather) {
        tomorrow.setWeather(weather);
    }

    public Tomorrow getTomorrow() {
        return tomorrow;
    }

    public void gameFlow(){
        // Weather setups for next day
        this.weather = tomorrow.getWeather();
        tomorrow.setWeather(weather);

        // Check weather
    }

    public  ArrayList<NPC> getNPCs() {
        return NPCs;
    }




}
