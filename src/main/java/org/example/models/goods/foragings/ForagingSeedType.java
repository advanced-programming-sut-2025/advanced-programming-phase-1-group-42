package org.example.models.goods.foragings;

import org.example.models.enums.Season;
import org.example.models.goods.GoodType;
import org.example.models.goods.farmings.FarmingCropType;

import java.util.ArrayList;
import java.util.Arrays;

public enum ForagingSeedType implements GoodType {

    // Spring Crops
    JAZZ_SEEDS("Jazz_Seeds", FarmingCropType.BLUE_JAZZ, new ArrayList<>(Arrays.asList(1, 2, 2, 2)), 7,
            true, -1, 30, 45, new ArrayList<>(Arrays.asList(Season.SPRING)), false),
    CARROT_SEEDS("Carrot_Seeds", FarmingCropType.CARROT, new ArrayList<>(Arrays.asList(1, 1, 1)), 3,
            true, -1, 5, -1, new ArrayList<>(Arrays.asList(Season.SPRING)), false),
    CAULIFLOWER_SEEDS("Cauliflower_Seeds", FarmingCropType.CAULIFLOWER, new ArrayList<>(Arrays.asList(1, 2, 4, 4, 1)), 12,
            true, -1, 100, -1, new ArrayList<>(Arrays.asList(Season.SPRING)), true),
    COFFEE_BEAN("Coffee_Bean", FarmingCropType.COFFEE_BEAN, new ArrayList<>(Arrays.asList(1, 2, 2, 3, 2)), 10,
            false, 2, -1, 200, new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER)), false),
    GARLIC_SEEDS("Garlic_Seeds", FarmingCropType.GARLIC, new ArrayList<>(Arrays.asList(1, 1, 1, 1)), 4,
            true, -1, 40, 60, new ArrayList<>(Arrays.asList(Season.SPRING)), false),
    BEAN_STARTER("Bean_Starter", FarmingCropType.GREEN_BEAN, new ArrayList<>(Arrays.asList(1, 1, 1, 3, 4)), 10,
            false, 3, 60, 90, new ArrayList<>(Arrays.asList(Season.SPRING)), false),
    KALE_SEEDS("Kale_Seeds", FarmingCropType.KALE, new ArrayList<>(Arrays.asList(1, 2, 2, 1)), 6,
            true, -1, 70, 105, new ArrayList<>(Arrays.asList(Season.SPRING)), false),
    PARSNIP_SEEDS("Parsnip_Seeds", FarmingCropType.PARSNIP, new ArrayList<>(Arrays.asList(1, 1, 1, 1)), 4,
            true, -1, 20, 30, new ArrayList<>(Arrays.asList(Season.SPRING)), false),
    POTATO_SEEDS("Potato_Seeds", FarmingCropType.POTATO, new ArrayList<>(Arrays.asList(1, 1, 1, 2, 1)), 6,
            true, -1, 50, 75, new ArrayList<>(Arrays.asList(Season.SPRING)), false),
    RHUBARB_SEEDS("Rhubarb_Seeds", FarmingCropType.RHUBARB, new ArrayList<>(Arrays.asList(2, 2, 2, 3, 4)), 13,
            true, -1, 100, -1, new ArrayList<>(Arrays.asList(Season.SPRING)), false),
    STRAWBERRY_SEEDS("Strawberry_Seeds", FarmingCropType.STRAWBERRY, new ArrayList<>(Arrays.asList(1, 1, 2, 2, 2)), 8,
            false, 4, 100, -1, new ArrayList<>(Arrays.asList(Season.SPRING)), false),
    TULIP_BULB("Tulip_Bulb", FarmingCropType.TULIP, new ArrayList<>(Arrays.asList(1, 1, 2, 2)), 6,
            true, -1, 25, -1, new ArrayList<>(Arrays.asList(Season.SPRING)), false),
    RICE_SHOOT("Rice_Shoot", FarmingCropType.UNMILLED_RICE, new ArrayList<>(Arrays.asList(1, 2, 2, 3)), 8,
            true, -1, 40, 60, new ArrayList<>(Arrays.asList(Season.SPRING)), false),

    // Summer Crops
    BLUEBERRY_SEEDS("Blueberry_Seeds", FarmingCropType.BLUEBERRY, new ArrayList<>(Arrays.asList(1, 3, 3, 4, 2)), 13,
            false, 4, 80, 120, new ArrayList<>(Arrays.asList(Season.SUMMER)), false),
    CORN_SEEDS("Corn_Seeds", FarmingCropType.CORN, new ArrayList<>(Arrays.asList(2, 3, 3, 3, 3)), 14,
            false, 4, 150, 225, new ArrayList<>(Arrays.asList(Season.SUMMER, Season.FALL)), false),
    HOPS_STARTER("Hops_Starter", FarmingCropType.HOPS, new ArrayList<>(Arrays.asList(1, 1, 2, 3, 4)), 11,
            false, 1, 60,90, new ArrayList<>(Arrays.asList(Season.SUMMER)), false),
    PEPPER_SEEDS("Pepper_Seeds", FarmingCropType.HOT_PEPPER, new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1)), 5,
            false, 3, 40, 60, new ArrayList<>(Arrays.asList(Season.SUMMER)), false),
    MELON_SEEDS("Melon_Seeds", FarmingCropType.MELON, new ArrayList<>(Arrays.asList(1, 2, 3, 3, 3)), 12,
            true, -1, 80, 120, new ArrayList<>(Arrays.asList(Season.SUMMER)), true),
    POPPY_SEEDS("Poppy_Seeds", FarmingCropType.POPPY, new ArrayList<>(Arrays.asList(1, 2, 2, 2)), 7,
            true, -1, 100, 150, new ArrayList<>(Arrays.asList(Season.SUMMER)), false),
    RADISH_SEEDS("Radish_Seeds", FarmingCropType.RADISH, new ArrayList<>(Arrays.asList(2, 1, 2, 1)), 6,
            true, -1, 40, 60, new ArrayList<>(Arrays.asList(Season.SUMMER)), false),
    RED_CABBAGE_SEEDS("Red_Cabbage_Seeds", FarmingCropType.RED_CABBAGE, new ArrayList<>(Arrays.asList(2, 1, 2, 2, 2)), 9,
            true, -1, 100, 150, new ArrayList<>(Arrays.asList(Season.SUMMER)), false),
    STARFRUIT_SEEDS("Starfruit_Seeds", FarmingCropType.STARFRUIT, new ArrayList<>(Arrays.asList(2, 3, 2, 3, 3)), 13,
            true, -1, 400, -1, new ArrayList<>(Arrays.asList(Season.SUMMER)), false),
    SPANGLE_SEEDS("Spangle_Seeds", FarmingCropType.SUMMER_SPANGLE, new ArrayList<>(Arrays.asList(1, 2, 3, 1)), 8,
            true, -1, 62, -1, new ArrayList<>(Arrays.asList(Season.SUMMER)), false),
    SUMMER_SQUASH_SEEDS("Summer_Squash_Seeds", FarmingCropType.SUMMER_SQUASH, new ArrayList<>(Arrays.asList(1, 1, 1, 2, 1)), 6,
            false, 3, 10, -1, new ArrayList<>(Arrays.asList(Season.SUMMER)), false),
    SUNFLOWER_SEEDS("Sunflower_Seeds", FarmingCropType.SUNFLOWER, new ArrayList<>(Arrays.asList(1, 2, 3, 2)), 8,
            true, -1, 125, -1, new ArrayList<>(Arrays.asList(Season.SUMMER, Season.FALL)), false),
    TOMATO_SEEDS("Tomato_Seeds", FarmingCropType.TOMATO, new ArrayList<>(Arrays.asList(2, 2, 2, 2, 3)), 11,
            false, 4, 62, -1, new ArrayList<>(Arrays.asList(Season.SUMMER)), false),
    WHEAT_SEEDS("Wheat_Seeds", FarmingCropType.WHEAT, new ArrayList<>(Arrays.asList(1, 1, 1, 1)), 4,
            true, -1, 125, -1, new ArrayList<>(Arrays.asList(Season.SUMMER, Season.FALL)), false),

    // Fall Crops
    AMARANTH_SEEDS("Amaranth_Seeds", FarmingCropType.AMARANTH, new ArrayList<>(Arrays.asList(1, 2, 2, 2)), 7,
            true, -1, 87, -1, new ArrayList<>(Arrays.asList(Season.FALL)), false),
    ARTICHOKE_SEEDS("Artichoke_Seeds", FarmingCropType.ARTICHOKE, new ArrayList<>(Arrays.asList(2, 2, 1, 2, 1)), 8,
            true, -1, 30, 45, new ArrayList<>(Arrays.asList(Season.FALL)), false),
    BEET_SEEDS("Beet_Seeds", FarmingCropType.BEET, new ArrayList<>(Arrays.asList(1, 1, 2, 2)), 6,
            true, -1, 20, -1, new ArrayList<>(Arrays.asList(Season.FALL)), false),
    BOK_CHOY_SEEDS("Bok_Choy_Seeds", FarmingCropType.BOK_CHOY, new ArrayList<>(Arrays.asList(1, 1, 1, 1)), 4,
            true, -1, 50, 75, new ArrayList<>(Arrays.asList(Season.FALL)), false),
    BROCCOLI_SEEDS("Broccoli_Seeds", FarmingCropType.BROCCOLI, new ArrayList<>(Arrays.asList(2, 2, 2, 2)), 8,
            false, 4, 15, -1, new ArrayList<>(Arrays.asList(Season.FALL)), false),
    CRANBERRY_SEEDS("Cranberry_Seeds", FarmingCropType.CRANBERRIES, new ArrayList<>(Arrays.asList(1, 2, 1, 1, 2)), 7,
            false, 5, 300, -1, new ArrayList<>(Arrays.asList(Season.FALL)), false),
    EGGPLANT_SEEDS("Eggplant_Seeds", FarmingCropType.EGGPLANT, new ArrayList<>(Arrays.asList(1, 1, 1, 1)), 5,
            false, 5, 25, -1, new ArrayList<>(Arrays.asList(Season.FALL)), false),
    FAIRY_SEEDS("Fairy_Seeds", FarmingCropType.FAIRY_ROSE, new ArrayList<>(Arrays.asList(1, 4, 4, 3)), 12,
            true, -1, 250, -1, new ArrayList<>(Arrays.asList(Season.FALL)), false),
    GRAPE_STARTER("Grape_Starter", FarmingCropType.GRAPE, new ArrayList<>(Arrays.asList(1, 1, 2, 3, 3)), 10,
            false, 3, 75, -1, new ArrayList<>(Arrays.asList(Season.FALL)), false),
    PUMPKIN_SEEDS("Pumpkin_Seeds", FarmingCropType.PUMPKIN, new ArrayList<>(Arrays.asList(1, 2, 3, 4, 3)), 13,
            true, -1, 125, -1, new ArrayList<>(Arrays.asList(Season.FALL)), true),
    YAM_SEEDS("Yam_Seeds", FarmingCropType.YAM, new ArrayList<>(Arrays.asList(1, 3, 3, 3)), 10,
            true, -1, 75, -1, new ArrayList<>(Arrays.asList(Season.FALL)), false),
    RARE_SEED("Rare_Seed", FarmingCropType.SWEET_GEM_BERRY, new ArrayList<>(Arrays.asList(2, 4, 6, 6, 6)), 24,
            true, -1, 1000, -1, new ArrayList<>(Arrays.asList(Season.FALL)), false),

    // Winter Crop
    POWDERMELON_SEEDS("Powdermelon_Seeds", FarmingCropType.POWDERMELON, new ArrayList<>(Arrays.asList(1, 2, 1, 2, 1)), 7,
            true, -1, 20, -1, new ArrayList<>(Arrays.asList(Season.WINTER)), true),

    // Special Crop (Grows in all seasons except Winter)
    ANCIENT_SEEDS("Ancient_Seeds", FarmingCropType.ANCIENT_FRUIT, new ArrayList<>(Arrays.asList(2, 7, 7, 7, 5)), 28,
                false, 7, 500, -1,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL)), false);

    //TODO: Mixed seed left


    ForagingSeedType(String name, FarmingCropType farmingCropType, ArrayList<Integer> stages, int totalHarvestTime, boolean oneTime, int regrowthTime, int seasonPrice, int otherSeasonPrice, ArrayList<Season> seasons, boolean canBecomeGiant) {
        this.name = name;
        this.farmingCropType = farmingCropType;
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
    private FarmingCropType farmingCropType;
    private ArrayList<Integer> stages;
    private int totalHarvestTime;
    private boolean oneTime;
    private int regrowthTime;
    private int seasonPrice;
    private int otherSeasonPrice;
    private ArrayList<Season> seasons;
    private boolean canBecomeGiant;


    @Override
    public int getSellPrice() {
        return 0;
    }

    @Override
    public int getEnergy() {
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        StringBuilder list = new StringBuilder();
        list.append("Source: ").append(name).append("\n");
        list.append("Stages: ");
        for (int i = 0; i < stages.size(); i++) {
            list.append(stages.get(i));
            if(i != stages.size() - 1)
                list.append("-");
        }
        list.append("\n");

        list.append("Total Harvest Time: ").append(totalHarvestTime).append("\n");
        list.append("One Time: ").append(oneTime).append("\n");
        list.append("Regrowth Time: ").append((regrowthTime >= 0) ? regrowthTime : "").append("\n");
        list.append("Season: ");
        for (int i = 0; i < seasons.size(); i++) {
            list.append(seasons.get(i));
            if(i != seasons.size() - 1)
                list.append(", ");
        }
        list.append("\n");

        list.append("Can Become Giant: ").append(canBecomeGiant).append("\n");
        return list.toString();
    }
}
