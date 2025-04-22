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
    private int time = 9;
    private int dayOfWeek = 1;
    private int seasonOfYear = 1;
    private int year = 1;
    private int dayOfSeason = 1;

    // A Function to change game base of the cycle of players and moves the game forward
    public void gameFlow() {
        time++;
        if (time >= 23) {
            time = 9;
            dayOfWeek++;
            if (dayOfWeek >= 7) {
                dayOfWeek = 1;
            }
            dayOfSeason++;
            if (dayOfSeason >= 28) {
                seasonOfYear++;
                dayOfSeason = 1;
            }
            if (seasonOfYear >= 4) {
                seasonOfYear = 1;
                year++;
            }
        }
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

    // time and date getter

    public int getDayOfSeason() {
        return dayOfSeason;
    }


    public int getSeasonOfYearInt() {
        return seasonOfYear;
    }

    public String getSeasonOfYear() {
        return switch (seasonOfYear) {
            case 1 -> "Spring";
            case 2 -> "Summer";
            case 3 -> "Autumn";
            case 4 -> "Winter";
            default -> null;
        };
    }

    public String getDayOfWeek() {
        return switch (dayOfWeek) {
            case 1 -> "Saturday";
            case 2 -> "Sunday";
            case 3 -> "Monday";
            case 4 -> "Tuesday";
            case 5 -> "Wednesday";
            case 6 -> "Thursday";
            case 7 -> "Friday";
            default -> null;
        };
    }

    public int getTime() {
        return time;
    }

    public int getYear() {
        return year;
    }

    public Map getMap() {
        return map;
    }

}
