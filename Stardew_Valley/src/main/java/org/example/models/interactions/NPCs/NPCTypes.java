package org.example.models.interactions.NPCs;

import org.example.models.enums.Season;
import org.example.models.goods.GoodType;
import org.example.models.goods.foods.FoodType;
import org.example.models.goods.foods.artisans.ArtisanType;

import java.util.*;

public enum NPCTypes {
    SEBASTIAN("Sebastian",
            new ArrayList<>(List.of(FoodType.PIZZA, FoodType.PUMPKIN_PIE)),
            new ArrayList<>(List.of("Woo-hoo! It’s my birthday today!! Send me a little something, pretty please!")),
            new ArrayList<>(List.of( //rewards
                    new HashMap<>(Map.of(FoodType.PANCAKES, 100))
            )),
            "Home" ,
            new HashMap<Season, Integer>() {{
                put(Season.FALL, 12);
    }}),
    ABIGAIL("Abigail",
            new ArrayList<>(List.of(ArtisanType.Coffee, FoodType.PUMPKIN_PIE)),
            new ArrayList<>(List.of("So… it’s my birthday today, in case anyone cares.")),
            new ArrayList<>(List.of(
                    new HashMap<>(Map.of(FoodType.PANCAKES, 100))
            )),
            "Home" ,
            new HashMap<Season, Integer>() {{
                put(Season.SPRING, 2);
            }}),
    HARVEY("Harvey",
            new ArrayList<>(List.of(ArtisanType.Coffee, ArtisanType.Wine)),
            new ArrayList<>(List.of("I just wanted to let you know that today is my birthday.")),
            new ArrayList<>(List.of(
                    new HashMap<>(Map.of(FoodType.PANCAKES, 100))
            )),
            "Home" ,
            new HashMap<Season, Integer>() {{
                put(Season.SUMMER, 21);
            }}),
    LEAH("Leah",
            new ArrayList<>(List.of(ArtisanType.Wine)),
            new ArrayList<>(List.of("Well, well, today’s the day—I officially survived another year! It’s my birthday!")),
            new ArrayList<>(List.of(
                    new HashMap<>(Map.of(FoodType.PANCAKES, 100))
            )),
            "Home" ,
            new HashMap<Season, Integer>() {{
                put(Season.FALL, 19);
            }}),
    ROBIN("Robin",
                 new ArrayList<>(List.of(FoodType.SPAGHETTI)),
            new ArrayList<>(List.of("Hey! Just thought I’d share—today’s my birthday!, don't forget to send me a gift!")),
            new ArrayList<>(List.of(
            new HashMap<>(Map.of(FoodType.PANCAKES, 100))
            )),
            "Home" ,
            new HashMap<Season, Integer>() {{
                put(Season.WINTER, 27);
            }}),;


    private final String name;
    private final ArrayList<GoodType> favorites;
    private final ArrayList<String> dialogs;
    private final ArrayList<HashMap<GoodType, Integer>> requests;
    private final String place;
    private HashMap<Season, Integer> birthday;
    NPCTypes(String name,
             ArrayList<GoodType> favorites,
             ArrayList<String> dialogs,
             ArrayList<HashMap<GoodType, Integer>> requests,
             String place ,
             HashMap<Season, Integer> birthday) {
        this.name = name;
        this.favorites = favorites;
        this.dialogs = dialogs;
        this.requests = requests;
        this.place = place;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public ArrayList<GoodType> getFavorites() {
        return favorites;
    }

    public ArrayList<String> getDialogs() {
        return dialogs;
    }

    public ArrayList<HashMap<GoodType, Integer>> getRequests() {
        return requests;
    }

    public String getPlace() {
        return place;
    }

    public HashMap<Season, Integer> getBirthday() {
        return birthday;
    }
}