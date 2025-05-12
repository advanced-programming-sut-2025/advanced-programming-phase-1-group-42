package org.example.models.game_structure;

import org.example.models.game_structure.weathers.Weather;
import org.example.models.goods.Good;
import org.example.models.goods.farmings.FarmingCropType;
import org.example.models.goods.farmings.FarmingTreeSapling;
import org.example.models.goods.foragings.ForagingCrop;
import org.example.models.goods.foragings.ForagingMixedSeed;
import org.example.models.goods.foragings.ForagingSeed;
import org.example.models.goods.foragings.ForagingSeedType;
import org.example.models.interactions.Animals.Animal;
import org.example.models.interactions.NPCs.NPC;
import org.example.models.interactions.Player;
import org.example.models.interactions.PlayerBuildings.FarmBuilding;

import java.util.ArrayList;
import java.util.Iterator;

public class Game {

    private DateTime dateTime;
    private Weather weather;
    private Tomorrow tomorrow;
    private final ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private Player gameAdmin;
    private Map map = null;
    private final ArrayList<NPC> NPCs = new ArrayList<>();
    private int counter = 0;

    public void setPlayers(ArrayList<Player> players) {
        this.players.addAll(players);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setMap(Map map) {
        this.map = map;
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


        //for animals
        for (Player player : players) {
            for (FarmBuilding building: player.getFarm().getFarmBuildings()){
                for (Animal animal: building.getAnimals()){
                    animal.animalDayResult();
                }
            }
        }

        //for foraging seed
        for (Tile tile : map.getTiles()) {
            Iterator<Good> iterator = tile.getGoods().iterator();
            while (iterator.hasNext()) {
                Good good = iterator.next();
                if (good instanceof ForagingSeed) {
                    ForagingSeed seed = (ForagingSeed) good;
                    seed.dailyChange();
                    if (seed.isCrop()) {
                        iterator.remove();
                        tile.getGoods().add(Good.newGood(seed.getCropType()));
                    }
                }
            }
        }

        //for farming tree sapling
        for (Tile tile : map.getTiles()) {
            Iterator<Good> iterator = tile.getGoods().iterator();
            while (iterator.hasNext()) {
                Good good = iterator.next();
                if (good instanceof FarmingTreeSapling) {
                    FarmingTreeSapling sapling = (FarmingTreeSapling) good;
                    sapling.dailyChange();
                    if (sapling.isTree()) {
                        iterator.remove();
                        tile.getGoods().add(Good.newGood(sapling.getTreeType()));
                    }
                }
            }
        }


        //for mixed seed

        for (Tile tile : map.getTiles()) {
            Iterator<Good> iterator = tile.getGoods().iterator();
            while (iterator.hasNext()) {
                Good good = iterator.next();
                if (good instanceof ForagingMixedSeed) {
                    ForagingMixedSeed seed = (ForagingMixedSeed) good;
                    seed.dailyChange();
                    if (seed.isCrop()) {
                        iterator.remove();
                        tile.getGoods().add(Good.newGood(seed.getCropType()));
                    }
                }
            }
        }




        // Check weather
    }

    public Player findPlayer(String playerName) {
        for (Player player : players) {
            if(player.getUser().getUsername().equals(playerName))
                return player;
        }
        return null;
    }

    public ArrayList<NPC> getNPCs() {
        return NPCs;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public void setTomorrow(Tomorrow tomorrow) {
        this.tomorrow = tomorrow;
    }

    public void setNPCs(ArrayList<NPC> npcs) {
        this.NPCs.addAll(npcs);
    }
}
