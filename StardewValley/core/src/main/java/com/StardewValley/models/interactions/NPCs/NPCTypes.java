package com.StardewValley.models.interactions.NPCs;

import com.StardewValley.models.Pair;
import com.StardewValley.models.enums.Season;
import com.StardewValley.models.game_structure.Coordinate;
import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.goods.artisans.ArtisanType;
import com.StardewValley.models.goods.farmings.FarmingCropType;
import com.StardewValley.models.goods.fishs.FishType;
import com.StardewValley.models.goods.foods.FoodType;
import com.StardewValley.models.goods.foragings.ForagingMineralType;
import com.StardewValley.models.goods.products.ProductType;

import java.util.ArrayList;
import java.util.List;

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
            new ArrayList<>(List.of(
                    new Pair<>(ForagingMineralType.IRON_ORE, 50),
                    new Pair<>(FoodType.PUMPKIN_PIE,1),
                    new Pair<>(ProductType.STONE,150)
            )),
            "Home",
            new Pair<>(Season.FALL,12),
            new Coordinate(10, 75), "GameAssets/NPCs/SEBASTIAN.png","GameAssets/Villagers/Sebastian.png"),
    ABIGAIL("Abigail",
            new ArrayList<>(List.of(ArtisanType.COFFEE, FoodType.PUMPKIN_PIE)),
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
            new ArrayList<>(List.of(
                    new Pair<>(ForagingMineralType.COPPER_ORE,1),
                    new Pair<>(FarmingCropType.PUMPKIN,1),
                    new Pair<>(FarmingCropType.WHEAT,50)
            )),
            "Home",
            new Pair<>(Season.SPRING,2),
            new Coordinate(110, 95), "GameAssets/NPCs/ABIGAIL.png" , "GameAssets/Villagers/Abigail.png"),

    HARVEY("Harvey",
            new ArrayList<>(List.of(ArtisanType.COFFEE, ArtisanType.WINE)),
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
            new ArrayList<>(List.of(
                    new Pair<>(FarmingCropType.ANY,12),
                    new Pair<>(FishType.SALMON,1),
                    new Pair<>(ArtisanType.WINE, 1)

            )),
            "Home",
            new Pair<>(Season.SUMMER,21),
            new Coordinate(40, 75), "GameAssets/NPCs/HARVEY.png" , "GameAssets/Villagers/Harvey.png"
    ),

    LEAH("Leah",
            new ArrayList<>(List.of(ArtisanType.WINE)),
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
            new ArrayList<>(List.of(
                    new Pair<>(ProductType.HARD_WOOD,10),
                    new Pair<>(FishType.SALMON,1),
                    new Pair<>(ProductType.WOOD,200)
            )),
            "Home",
            new Pair<>(Season.FALL,19),
            new Coordinate(120, 75), "GameAssets/NPCs/LEAH.png", "GameAssets/Villagers/Leah.png"
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
            new ArrayList<>(List.of(
                    new Pair<>(ProductType.WOOD,80),
                    new Pair<>(ForagingMineralType.IRON_ORE,10),
                    new Pair<>(ProductType.WOOD,1000)
            )),
            "Home",
            new Pair<>(Season.WINTER, 27),
            new Coordinate(15, 90), "GameAssets/NPCs/ROBIN.png", "GameAssets/Villagers/Robin.png"
    ),

    CLINT("Clint",
            new ArrayList<>(),
            null,
            new ArrayList<>(),
            "Blacksmith",
            null,
            new Coordinate(15, 60), "GameAssets/NPCs/CLINT.png" , "GameAssets/Villagers/Clint.png"),

    MORRIS("Morris",
            new ArrayList<>(),
            null,
            new ArrayList<>(),
            "JojaMart",
            null,
            new Coordinate(55, 60), "GameAssets/NPCs/MORRIS.png", "GameAssets/Villagers/Morris.png"),

    PIERRE("Pierre",
            new ArrayList<>(),
            null,
            new ArrayList<>(),
            "Pierre_General_Store",
            null,
            new Coordinate(85, 60), "GameAssets/NPCs/PIERRE.png", "GameAssets/Villagers/Pierre.png"),

    WILLY("Willy",
            new ArrayList<>(),
            null,
            new ArrayList<>(),
            "Fish_Shop",
            null,
            new Coordinate(125, 60), "GameAssets/NPCs/WILLY.png", "GameAssets/Villagers/Willy.png"),

    MARNIE("Marnie",
            new ArrayList<>(),
            null,
            new ArrayList<>(),
            "Marnie_Ranch",
            null,
            new Coordinate(55, 90), "GameAssets/NPCs/MARNIE.png", "GameAssets/Villagers/Marnie.png"),

    GUS("Gus",
            null,
            null,
            new ArrayList<>(),
            "The_Stardrop_Saloon",
            null,
            new Coordinate(85, 90), "GameAssets/NPCs/GUS.png", "GameAssets/Villagers/Gus.png"),;



    private final String name;
    private final ArrayList<GoodType> favorites;
    private final ArrayList<String> dialogs;
    private final ArrayList<Pair<GoodType, Integer>> requests;
    private final String place;
    private Pair<Season, Integer> birthday;
    private final Coordinate coordinate;
    private final String imagePath;
    private final String avatarPath;

    NPCTypes(String name,
             ArrayList<GoodType> favorites,
             ArrayList<String> dialogs,
             ArrayList<Pair<GoodType, Integer>>requests,
             String place,
             Pair<Season, Integer> birthday,
             Coordinate coordinate,
             String imagePath ,
             String avatarPath ) {
        this.name = name;
        this.favorites = favorites;
        this.dialogs = dialogs;
        this.requests = requests;
        this.place = place;
        this.birthday = birthday;
        this.coordinate = coordinate;
        this.imagePath = imagePath;
        this.avatarPath = avatarPath;
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

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getAvatarPath() {
        return avatarPath;
    }
}
