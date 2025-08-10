package com.StardewValley.models.game_structure;

import com.StardewValley.client.AppClient;
import com.StardewValley.models.Pair;
import com.StardewValley.models.enums.Season;
import com.StardewValley.models.enums.TileType;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.farmings.FarmingCrop;
import com.StardewValley.models.interactions.Player;
import com.StardewValley.server.ClientHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class DateTime {
    private int time = 9;
    private int date = 0;
    private Season season = Season.SPRING;

    // A Function to change game base of the cycle of players and moves the game forward
    public void timeFlow(ClientHandler clientHandler) {
        int difTime = 1;
        time++;

        if (time >= 22) {
            AppClient.getCurrentGame().gameFlow(clientHandler);
            time = 9;
            difTime = 11;
            date++;
        }

        if(clientHandler.getClientPlayer().getBuff() != null){
            clientHandler.getClientPlayer().getBuff().setRemainEffectTime();
            if(clientHandler.getClientPlayer().getBuff().getRemainEffectTime() == 0){
                clientHandler.getClientPlayer().setBuff(null);
                clientHandler.getClientPlayer().getEnergy().setMaxDayEnergy(200);
                clientHandler.getClientPlayer().getEnergy().setDayEnergyLeft(200);
            }
            else {
                clientHandler.getClientPlayer().getEnergy().setDayEnergyLeft(300);
                clientHandler.getClientPlayer().getEnergy().setMaxDayEnergy(300);
            }
        }
        for (Player player : clientHandler.getClientGame().getPlayers()) {
            ArrayList<Pair<Integer, Good>> newArtisanGoods = new ArrayList<>();
            for (Pair<Integer, Good> integerGoodPair : player.getArtisansGoodTime()) {
                if(integerGoodPair.first() - difTime <= 0) {
                    clientHandler.getClientPlayer().getInventory().addGood(new ArrayList<>(Arrays.asList(integerGoodPair.second())),
                        clientHandler.getClientGame(), clientHandler.getClientPlayer());
                    clientHandler.getClientPlayer().getNews().add(integerGoodPair.second().getName() + " has been added to the inventory");
                }
                else
                    newArtisanGoods.add(new Pair<>(integerGoodPair.first() - difTime, integerGoodPair.second()));
            }
            player.setArtisansGoodTime(newArtisanGoods);
        }

    }

    public int getDayOfSeason() {
        return date % 28 + 1;
    }


    public int getSeasonOfYearInt() {
        return (date / 28) % 4 + 1;
    }

    public Season getSeasonOfYear() {
        switch ((date / 28) % 4 + 1) {
            case 1 -> {
                return Season.SPRING;
            }
            case 2 -> {
                return Season.SUMMER;
            }
            case 3 -> {
                return Season.FALL;
            }
            case 4 -> {
                return Season.WINTER;
            }
            default -> {
                return null;
            }
        }
    }

    public String getDayOfWeek() {
        return switch (date % 7 + 1) {
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
        return (date / (28 * 4)) + 1;
    }

    public int getDays() {
        return date;
    }

    public Season getSeason() {
        return season;
    }

    public void farmingSeasonChange(Game game) {
        for (int i = 0 ; i < 140 ; i++) {
            for (int j = 0; j < 160; j++) {
                Coordinate coordinate = new Coordinate(i, j);
                Tile tile = AppClient.getCurrentGame().getMap().findTile(coordinate, game);
                if(!tile.getTileType().equals(TileType.GREEN_HOUSE)) {
                    Iterator<Good> iterator = tile.getGoods().iterator();
                    while (iterator.hasNext()) {
                        Good good = iterator.next();
                        if (good instanceof FarmingCrop) {
                            iterator.remove();
                        }
                    }
                }
            }
        }
    }

    public void setTime(int time) {
        this.time = time;
    }
}
