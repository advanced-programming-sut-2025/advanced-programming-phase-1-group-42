package org.example.models.game_structure;

import org.example.models.App;
import org.example.models.enums.WeatherType;
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
    private Map map;
    private User gameCreator;
    private Map CurrentMap = null;
    private Player currentPlayingPlayer;
    private int counter = 0;


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
            this.dateTime.timeFlow();
        }
        currentPlayingPlayer = players.get(counter);
    }

    public Map getMap() {
        return map;
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
}
