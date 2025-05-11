package org.example.models.game_structure;

import org.example.models.App;
import org.example.models.enums.Season;

public class DateTime {
    private int time = 9;
    private int dayOfWeek = 1;
    private int seasonOfYear = 1;
    private Season season = Season.SPRING;
    private int year = 1;
    private int dayOfSeason = 1;

    // A Function to change game base of the cycle of players and moves the game forward
    public void timeFlow() {
        time++;
        if (time >= 24) {
            App.getCurrentGame().gameFlow();
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

    public int getDayOfSeason() {
        return dayOfSeason;
    }


    public int getSeasonOfYearInt() {
        return seasonOfYear;
    }

    public Season getSeasonOfYear() {
        return switch (seasonOfYear) {
            case 1 -> Season.SPRING;
            case 2 -> Season.SUMMER;
            case 3 -> Season.FALL;
            case 4 -> Season.WINTER;
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


}
