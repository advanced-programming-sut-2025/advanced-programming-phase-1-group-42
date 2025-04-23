package org.example.models.goods.farmings;

<<<<<<< Updated upstream
import org.example.models.enums.Season;
import org.example.models.goods.GoodType;

import java.util.ArrayList;
import java.util.Arrays;

public enum FarmingSeedType implements GoodType {
    //TODO
    //Giant Farming seeds do not forget to implement


    // Spring Crops
    // Spring Crops
    JAZZ_SEEDS("Jazz_Seeds", FarmingGoodType.BLUE_JAZZ, new ArrayList<>(Arrays.asList(1, 2, 2, 2)), 7,
            true, -1, 30, 45, new ArrayList<>(Arrays.asList(Season.SPRING)), false),
    CARROT_SEEDS("Carrot_Seeds", FarmingGoodType.CARROT, new ArrayList<>(Arrays.asList(1, 1, 1)), 3,
            true, -1, 5, -1, new ArrayList<>(Arrays.asList(Season.SPRING)), false),
    CAULIFLOWER_SEEDS("Cauliflower_Seeds", FarmingGoodType.CAULIFLOWER, new ArrayList<>(Arrays.asList(1, 2, 4, 4, 1)), 12,
            true, -1, 100, -1, new ArrayList<>(Arrays.asList(Season.SPRING)), true),
    COFFEE_BEAN("Coffee_Bean", FarmingGoodType.COFFEE_BEAN, new ArrayList<>(Arrays.asList(1, 2, 2, 3, 2)), 10,
            false, 2, -1, 200, new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER)), false),
    GARLIC_SEEDS("Garlic_Seeds", FarmingGoodType.GARLIC, new ArrayList<>(Arrays.asList(1, 1, 1, 1)), 4,
            true, -1, 40, 60, new ArrayList<>(Arrays.asList(Season.SPRING)), false),
    BEAN_STARTER("Bean_Starter", FarmingGoodType.GREEN_BEAN, new ArrayList<>(Arrays.asList(1, 1, 1, 3, 4)), 10,
            false, 3, 60, 90, new ArrayList<>(Arrays.asList(Season.SPRING)), false),
    KALE_SEEDS("Kale_Seeds", FarmingGoodType.KALE, new ArrayList<>(Arrays.asList(1, 2, 2, 1)), 6,
            true, -1, 70, 105, new ArrayList<>(Arrays.asList(Season.SPRING)), false),
    PARSNIP_SEEDS("Parsnip_Seeds", FarmingGoodType.PARSNIP, new ArrayList<>(Arrays.asList(1, 1, 1, 1)), 4,
            true, -1, 20, 30, new ArrayList<>(Arrays.asList(Season.SPRING)), false),
    POTATO_SEEDS("Potato_Seeds", FarmingGoodType.POTATO, new ArrayList<>(Arrays.asList(1, 1, 1, 2, 1)), 6,
            true, -1, 50, 75, new ArrayList<>(Arrays.asList(Season.SPRING)), false),
    RHUBARB_SEEDS("Rhubarb_Seeds", FarmingGoodType.RHUBARB, new ArrayList<>(Arrays.asList(2, 2, 2, 3, 4)), 13,
            true, -1, 100, -1, new ArrayList<>(Arrays.asList(Season.SPRING)), false),
    STRAWBERRY_SEEDS("Strawberry_Seeds", FarmingGoodType.STRAWBERRY, new ArrayList<>(Arrays.asList(1, 1, 2, 2, 2)), 8,
            false, 4, 100, -1, new ArrayList<>(Arrays.asList(Season.SPRING)), false),
    TULIP_BULB("Tulip_Bulb", FarmingGoodType.TULIP, new ArrayList<>(Arrays.asList(1, 1, 2, 2)), 6,
            true, -1, 25, -1, new ArrayList<>(Arrays.asList(Season.SPRING)), false),
    RICE_SHOOT("Rice_Shoot", FarmingGoodType.UNMILLED_RICE, new ArrayList<>(Arrays.asList(1, 2, 2, 3)), 8,
            true, -1, 40, 60, new ArrayList<>(Arrays.asList(Season.SPRING)), false),

    // Summer Crops
    BLUEBERRY_SEEDS("Blueberry_Seeds", FarmingGoodType.BLUEBERRY, new ArrayList<>(Arrays.asList(1, 3, 3, 4, 2)), 13,
            false, 4, 80, 120, new ArrayList<>(Arrays.asList(Season.SUMMER)), false),
    CORN_SEEDS("Corn_Seeds", FarmingGoodType.CORN, new ArrayList<>(Arrays.asList(2, 3, 3, 3, 3)), 14,
            false, 4, 150, 225, new ArrayList<>(Arrays.asList(Season.SUMMER, Season.FALL)), false),
    HOPS_STARTER("Hops_Starter", FarmingGoodType.HOPS, new ArrayList<>(Arrays.asList(1, 1, 2, 3, 4)), 11,
            false, 1, 60,90, new ArrayList<>(Arrays.asList(Season.SUMMER)), false),
    PEPPER_SEEDS("Pepper_Seeds", FarmingGoodType.HOT_PEPPER, new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1)), 5,
            false, 3, 40, 60, new ArrayList<>(Arrays.asList(Season.SUMMER)), false),
    MELON_SEEDS("Melon_Seeds", FarmingGoodType.MELON, new ArrayList<>(Arrays.asList(1, 2, 3, 3, 3)), 12,
            true, -1, 80, 120, new ArrayList<>(Arrays.asList(Season.SUMMER)), true),
    POPPY_SEEDS("Poppy_Seeds", FarmingGoodType.POPPY, new ArrayList<>(Arrays.asList(1, 2, 2, 2)), 7,
            true, -1, 100, 150, new ArrayList<>(Arrays.asList(Season.SUMMER)), false),
    RADISH_SEEDS("Radish_Seeds", FarmingGoodType.RADISH, new ArrayList<>(Arrays.asList(2, 1, 2, 1)), 6,
            true, -1, 40, 60, new ArrayList<>(Arrays.asList(Season.SUMMER)), false),
    RED_CABBAGE_SEEDS("Red_Cabbage_Seeds", FarmingGoodType.RED_CABBAGE, new ArrayList<>(Arrays.asList(2, 1, 2, 2, 2)), 9,
            true, -1, 100, 150, new ArrayList<>(Arrays.asList(Season.SUMMER)), false),
    STARFRUIT_SEEDS("Starfruit_Seeds", FarmingGoodType.STARFRUIT, new ArrayList<>(Arrays.asList(2, 3, 2, 3, 3)), 13,
            true, -1, 400, -1, new ArrayList<>(Arrays.asList(Season.SUMMER)), false),
    SPANGLE_SEEDS("Spangle_Seeds", FarmingGoodType.SUMMER_SPANGLE, new ArrayList<>(Arrays.asList(1, 2, 3, 1)), 8,
            true, -1, 62, -1, new ArrayList<>(Arrays.asList(Season.SUMMER)), false),
    SUMMER_SQUASH_SEEDS("Summer_Squash_Seeds", FarmingGoodType.SUMMER_SQUASH, new ArrayList<>(Arrays.asList(1, 1, 1, 2, 1)), 6,
            false, 3, 10, -1, new ArrayList<>(Arrays.asList(Season.SUMMER)), false),
    SUNFLOWER_SEEDS("Sunflower_Seeds", FarmingGoodType.SUNFLOWER, new ArrayList<>(Arrays.asList(1, 2, 3, 2)), 8,
            true, -1, 125, -1, new ArrayList<>(Arrays.asList(Season.SUMMER, Season.FALL)), false),
    TOMATO_SEEDS("Tomato_Seeds", FarmingGoodType.TOMATO, new ArrayList<>(Arrays.asList(2, 2, 2, 2, 3)), 11,
            false, 4, 62, -1, new ArrayList<>(Arrays.asList(Season.SUMMER)), false),
    WHEAT_SEEDS("Wheat_Seeds", FarmingGoodType.WHEAT, new ArrayList<>(Arrays.asList(1, 1, 1, 1)), 4,
            true, -1, 125, -1, new ArrayList<>(Arrays.asList(Season.SUMMER, Season.FALL)), false),

    // Fall Crops
    AMARANTH_SEEDS("Amaranth_Seeds", FarmingGoodType.AMARANTH, new ArrayList<>(Arrays.asList(1, 2, 2, 2)), 7,
            true, -1, 87, -1, new ArrayList<>(Arrays.asList(Season.FALL)), false),
    ARTICHOKE_SEEDS("Artichoke_Seeds", FarmingGoodType.ARTICHOKE, new ArrayList<>(Arrays.asList(2, 2, 1, 2, 1)), 8,
            true, -1, 30, 45, new ArrayList<>(Arrays.asList(Season.FALL)), false),
    BEET_SEEDS("Beet_Seeds", FarmingGoodType.BEET, new ArrayList<>(Arrays.asList(1, 1, 2, 2)), 6,
            true, -1, 20, -1, new ArrayList<>(Arrays.asList(Season.FALL)), false),
    BOK_CHOY_SEEDS("Bok_Choy_Seeds", FarmingGoodType.BOK_CHOY, new ArrayList<>(Arrays.asList(1, 1, 1, 1)), 4,
            true, -1, 50, 75, new ArrayList<>(Arrays.asList(Season.FALL)), false),
    BROCCOLI_SEEDS("Broccoli_Seeds", FarmingGoodType.BROCCOLI, new ArrayList<>(Arrays.asList(2, 2, 2, 2)), 8,
            false, 4, 15, -1, new ArrayList<>(Arrays.asList(Season.FALL)), false),
    CRANBERRY_SEEDS("Cranberry_Seeds", FarmingGoodType.CRANBERRIES, new ArrayList<>(Arrays.asList(1, 2, 1, 1, 2)), 7,
            false, 5, 300, -1, new ArrayList<>(Arrays.asList(Season.FALL)), false),
    EGGPLANT_SEEDS("Eggplant_Seeds", FarmingGoodType.EGGPLANT, new ArrayList<>(Arrays.asList(1, 1, 1, 1)), 5,
            false, 5, 25, -1, new ArrayList<>(Arrays.asList(Season.FALL)), false),
    FAIRY_SEEDS("Fairy_Seeds", FarmingGoodType.FAIRY_ROSE, new ArrayList<>(Arrays.asList(1, 4, 4, 3)), 12,
            true, -1, 250, -1, new ArrayList<>(Arrays.asList(Season.FALL)), false),
    GRAPE_STARTER("Grape_Starter", FarmingGoodType.GRAPE, new ArrayList<>(Arrays.asList(1, 1, 2, 3, 3)), 10,
            false, 3, 75, -1, new ArrayList<>(Arrays.asList(Season.FALL)), false),
    PUMPKIN_SEEDS("Pumpkin_Seeds", FarmingGoodType.PUMPKIN, new ArrayList<>(Arrays.asList(1, 2, 3, 4, 3)), 13,
            true, -1, 125, -1, new ArrayList<>(Arrays.asList(Season.FALL)), true),
    YAM_SEEDS("Yam_Seeds", FarmingGoodType.YAM, new ArrayList<>(Arrays.asList(1, 3, 3, 3)), 10,
            true, -1, 75, -1, new ArrayList<>(Arrays.asList(Season.FALL)), false),
    RARE_SEED("Rare_Seed", FarmingGoodType.SWEET_GEM_BERRY, new ArrayList<>(Arrays.asList(2, 4, 6, 6, 6)), 24,
            true, -1, 1000, -1, new ArrayList<>(Arrays.asList(Season.FALL)), false),

    // Winter Crop
    POWDERMELON_SEEDS("Powdermelon_Seeds", FarmingGoodType.POWDERMELON, new ArrayList<>(Arrays.asList(1, 2, 1, 2, 1)), 7,
            true, -1, 20, -1, new ArrayList<>(Arrays.asList(Season.WINTER)), true),

    // Special Crop (Grows in all seasons except Winter)
    ANCIENT_SEEDS("Ancient_Seeds", FarmingGoodType.ANCIENT_FRUIT, new ArrayList<>(Arrays.asList(2, 7, 7, 7, 5)), 28,
            false, 7, 500, -1, new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL)), false);


    FarmingSeedType(String name, FarmingGoodType farmingGoodType, ArrayList<Integer> stages, int totalHarvestTime, boolean oneTime, int regrowthTime, int seasonPrice, int otherSeasonPrice, ArrayList<Season> seasons, boolean canBecomeGiant) {
        this.name = name;
        this.farmingGoodType = farmingGoodType;
        this.stages = stages;
        this.totalHarvestTime = totalHarvestTime;
        this.oneTime = oneTime;
        this.regrowthTime = regrowthTime;
        this.seasonPrice = seasonPrice;
        this.otherSeasonPrice = otherSeasonPrice;
        this.seasons = seasons;
        this.canBecomeGiant = canBecomeGiant;
    }

    private String name;
    private FarmingGoodType farmingGoodType;
    private ArrayList<Integer> stages;
    private int totalHarvestTime;
    private boolean oneTime;
    private int regrowthTime;
    private int seasonPrice;
    private int otherSeasonPrice;
    private ArrayList<Season> seasons;
    private boolean canBecomeGiant;
=======
public enum FarmingSeedType {
    //TODO
    //Giant Farming seeds do not forget to implement
>>>>>>> Stashed changes
}
