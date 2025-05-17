package org.example.models.game_structure;

import org.example.models.App;
import org.example.models.Pair;
import org.example.models.enums.Season;
import org.example.models.enums.TileType;
import org.example.models.goods.Good;
import org.example.models.goods.farmings.FarmingCrop;
import org.example.models.interactions.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class DateTime {
    private int time = 9;
    private int date = 0;
    private Season season = Season.SPRING;

    // A Function to change game base of the cycle of players and moves the game forward
    public void timeFlow() {
        int difTime = 1;
        time++;

        if (time >= 22) {
            App.getCurrentGame().gameFlow();
            time = 9;
            difTime = 11;
            date++;
        }

        if(App.getCurrentGame().getCurrentPlayer().getBuff() != null){
            App.getCurrentGame().getCurrentPlayer().getBuff().setRemainEffectTime();
            if(App.getCurrentGame().getCurrentPlayer().getBuff().getRemainEffectTime() == 0){
                App.getCurrentGame().getCurrentPlayer().setBuff(null);
                App.getCurrentGame().getCurrentPlayer().getEnergy().setMaxDayEnergy(200);
                App.getCurrentGame().getCurrentPlayer().getEnergy().setDayEnergyLeft(200);
            }
            else {
                App.getCurrentGame().getCurrentPlayer().getEnergy().setDayEnergyLeft(300);
                App.getCurrentGame().getCurrentPlayer().getEnergy().setMaxDayEnergy(300);
            }
        }
        for (Player player : App.getCurrentGame().getPlayers()) {
            ArrayList<Pair<Integer, Good>> newArtisanGoods = new ArrayList<>();
            for (Pair<Integer, Good> integerGoodPair : player.getArtisansGoodTime()) {
                if(integerGoodPair.first() - difTime <= 0) {
                    App.getCurrentGame().getCurrentPlayer().getInventory().addGood(new ArrayList<>(Arrays.asList(integerGoodPair.second())));
                    App.getCurrentGame().getCurrentPlayer().getNews().add(integerGoodPair.second().getName() + " has been added to the inventory");
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
        return switch ((date / 28) % 4 + 1) {
            case 1 -> Season.SPRING;
            case 2 -> Season.SUMMER;
            case 3 -> Season.FALL;
            case 4 -> Season.WINTER;
            default -> null;
        };
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

    public void farmingSeasonChange() {
        for (int i = 0 ; i < 140 ; i++) {
            for (int j = 0; j < 160; j++) {
                Coordinate coordinate = new Coordinate(i, j);
                Tile tile = App.getCurrentGame().getMap().findTile(coordinate);
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
