package org.example.models.game_structure;

import org.example.models.enums.Season;

public class DateTime {
    private int time = 9;
    private int dayOfWeek = 1;
    private int seasonOfYear = 1;
    private int year = 1;
    private int dayOfSeason = 1;

    // A Function to change game base of the cycle of players and moves the game forward
    public void timeFlow() {
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


}
