package org.example.models.game_structure;

import org.example.models.App;
import org.example.models.enums.Season;

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


}
