package org.example.models.interactions.NPCs;

import org.example.models.Pair;
import org.example.models.enums.Season;
import org.example.models.goods.GoodType;
import org.example.models.goods.foods.FoodType;
import org.example.models.goods.foods.artisans.ArtisanType;

import java.util.*;

public enum NPCTypes {
    SEBASTIAN("Sebastian",
            new ArrayList<>(List.of(FoodType.PIZZA, FoodType.PUMPKIN_PIE)),
            new ArrayList<>(List.of(
                    "Well, well, today’s the day—I officially survived another year! It’s my birthday!",
                    "What a great day it is!",
                    //season
                    "Spring feels too lively for my taste... I prefer something quieter.",
                    "Summer heat is annoying. I just want to stay indoors with my music.",
                    "Fall is perfect—it’s calm, cool, and just the right vibe for me.",
                    "Winter makes everything seem so still… kind of peaceful, honestly.",
                    // weather
                    "It’s sunny today, but I’d rather stick to the shade—it’s too bright out.",
                    "Rain is my favorite weather—it calms me down and makes everything feel alive.",
                    "Foggy days feel almost mystical, don’t they? Love walking outside in the mist.",
                    "Snow has its charm, but cold weather isn’t really my thing.",
                    // favorites
                    "Pizza is definitely the best food ever. Don’t you think?",
                    "Pumpkin pie reminds me of fall—a cozy treat for chilly days."
            )),
            new ArrayList<>(List.of()),
            "Home",
            new Pair<>(Season.FALL,12)),
    ABIGAIL("Abigail",
            new ArrayList<>(List.of(ArtisanType.Coffee, FoodType.PUMPKIN_PIE)),
            new ArrayList<>(List.of(
                    "So… it’s my birthday today, in case anyone cares.",
                    "What a great day... or not.",
                    // season
                    "Spring is alright, I guess... but it feels way too bubbly for me.",
                    "Summer’s energy is exhausting—I think I’ll pass on all the excitement.",
                    "Fall matches my mood most days... maybe that’s why it’s my favorite.",
                    "Winter is cold and cozy—a great excuse to stay indoors.",
                    // weather
                    "Sunny days are overrated—too flashy for my taste.",
                    "Rainy days are perfect for thinking... or just doing nothing.",
                    "Foggy mornings make the world feel eerie and beautiful. Love it.",
                    "Snow brings some peace to the world, but ugh... it’s also freezing.",
                    // favorites
                    "Coffee is the only thing keeping me sane through these hectic mornings.",
                    "Pumpkin pie tastes amazing—it’s the highlight of fall for me."
            )),
            new ArrayList<>(List.of()),
            "Home",
            new Pair<>(Season.SPRING,2)),

    HARVEY("Harvey",
            new ArrayList<>(List.of(ArtisanType.Coffee, ArtisanType.Wine)),
            new ArrayList<>(List.of(
                    "I just wanted to let you know that today is my birthday.",
                    "What a nice day to celebrate!",
                    // season
                    "Spring feels full of life—it’s refreshing, isn’t it?",
                    "Summer keeps me busy at the clinic, but I enjoy its liveliness.",
                    "Fall has such a calm atmosphere—you can almost feel nature winding down.",
                    "Winter is peaceful and quiet. Perfect for cozy evenings.",
                    // weather
                    "Sunny days brighten my mood. Nothing beats a cheerful day like this.",
                    "Rainy days can be tough on my patients, but I enjoy the soft sound of raindrops.",
                    "Foggy weather reminds me of home—there’s something nostalgic about it.",
                    "Snow-covered streets feel magical, but dangerous! Don’t forget to stay warm.",
                    // favorites
                    "Coffee always gives me the energy I need to take care of my patients.",
                    "A glass of good wine is perfect for unwinding after a long day."
            )),
            new ArrayList<>(List.of()),
            "Home",
            new Pair<>(Season.SUMMER,21)
    ),

    LEAH("Leah",
            new ArrayList<>(List.of(ArtisanType.Wine)),
            new ArrayList<>(List.of(
                    "Woo-hoo! It’s my birthday today!! Send me a little something, pretty please!",
                    "What a wonderful day!",
                    // seasons
                    "Spring feels like a rebirth—it’s inspiring for my art.",
                    "Summer brings such vibrant colors—I love painting outdoors at this time.",
                    "Fall is magical with its golden hues. Every leaf tells a story.",
                    "Winter’s chilly embrace makes me want to create warm and cozy artwork.",
                    // weather
                    "Sunny days are full of energy—a great time to work on something bold.",
                    "Rain is soothing—it’s the perfect time to sit back and sketch new ideas.",
                    "I love foggy days—they’re like a dream world. Creativity flows naturally then.",
                    "Snow turns everything into a blank canvas—it’s inspiring!",
                    // favorites
                    "A good glass of wine enhances the creative process—it’s my little tradition.",
                    "I can't live without my art—it’s the center of everything I do!"
            )),
            new ArrayList<>(List.of()),
            "Home",
            new Pair<>(Season.FALL,19)
    ),

    ROBIN("Robin",
            new ArrayList<>(List.of(FoodType.SPAGHETTI)),
            new ArrayList<>(List.of(
                    "Hey! Just thought I’d share—today’s my birthday! Don’t forget to send me a gift!",
                    "What a lovely day!",
                    // season
                    "Spring is when everything starts growing again—makes me excited for new projects!",
                    "Summer is fun, but I prefer the cooler weather for working harder.",
                    "Fall is ideal for collecting materials—you can find so many great resources outdoors.",
                    "Winter is great for indoor crafting—being cozy makes everything better.",
                    // weather
                    "Sunny weather means outdoor work! Time to build and create under the warmth.",
                    "Rainy days keep me indoors, but it’s a chance to perfect my designs.",
                    "Fog gives the forest such an eerie vibe—makes collecting wood feel like an adventure.",
                    "Snowy landscapes are breathtaking, but challenging for work—need to stay warm and focused!",
                    // favorites
                    "Spaghetti is my go-to comfort food—it’s hearty and satisfying.",
                    "Crafting new furniture or tools is my ultimate passion."
            )),
            new ArrayList<>(List.of()),
            "Home",
            new Pair<>(Season.WINTER,27)
    ),

    CLINT("Clint",
            null,
            null,
            null,
            "Blacksmith",
            null),

    MORRIS("Morris",
            null,
            null,
            null,
            "JojaMart",
            null),

    PIERRE("Pierre",
            null,
            null,
            null,
            "Pierre_General_Store",
            null),

    WILLY("Willy",
            null,
            null,
            null,
            "Fish_Shop",
            null),

    MARNIE("Marnie",
            null,
            null,
            null,
            "Marnie_Ranch",
            null),

    GUS("Gus",
            null,
            null,
            null,
            "The_Stardrop_Saloon",
            null);



    private final String name;
    private final ArrayList<GoodType> favorites;
    private final ArrayList<String> dialogs;
    private final ArrayList<Pair<GoodType, Integer>> requests;
    private final String place;
    private Pair<Season, Integer> birthday;

    NPCTypes(String name,
             ArrayList<GoodType> favorites,
             ArrayList<String> dialogs,
             ArrayList<Pair<GoodType, Integer>>requests,
             String place,
             Pair<Season, Integer> birthday) {
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

    public ArrayList<Pair<GoodType, Integer>> getRequests() {
        return requests;
    }

    public String getPlace() {
        return place;
    }

    public Pair<Season, Integer> getBirthday() {
        return birthday;
    }
}