package org.example.models.game_structure;

import org.example.models.App;
import org.example.models.Pair;
import org.example.models.enums.Season;
import org.example.models.enums.TileType;
import org.example.models.goods.Good;
import org.example.models.goods.farmings.FarmingCrop;
import org.example.models.interactions.Player;

import java.util.HashMap;
import java.util.Iterator;

public class DateTime {
    private int time = 9;
    private int date = 1;
    private Season season = Season.SPRING;

    // A Function to change game base of the cycle of players and moves the game forward
    public void timeFlow() {
        time++;
        if (time >= 22) {
            App.getCurrentGame().gameFlow();
            time = 9;
            date++;
        }
        for (Player player : App.getCurrentGame().getPlayers()) {
            HashMap<Player, Pair<Integer, Integer>> friendships = player.getFriendShips();
            for (Pair<Integer, Integer> friendshipData : friendships.values()) {
                if (friendshipData.second() > (friendshipData.first() + 1) * 100) {
                    friendshipData.setFirst(friendshipData.first() + 1);
                }
            }
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

    public void seasonChange() {
        for (int i = 0; i < 140; i++) {
            for (int j = 0; j < 160; j++) {
                Coordinate coordinate = new Coordinate(i, j);
                Tile tile = App.getCurrentGame().getMap().findTile(coordinate);
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
