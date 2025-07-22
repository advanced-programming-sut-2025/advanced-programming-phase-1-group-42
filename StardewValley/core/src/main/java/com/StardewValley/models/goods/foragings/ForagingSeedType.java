package com.StardewValley.models.goods.foragings;

import com.StardewValley.models.enums.Season;
import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.goods.farmings.FarmingCropType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ForagingSeedType implements GoodType {

    // Spring Crops
    JAZZ_SEEDS("Jazz_Seeds", FarmingCropType.BLUE_JAZZ, new ArrayList<>(Arrays.asList(1, 2, 2, 2, 0)), 7,
        true, -1, 30, 45, new ArrayList<>(List.of(Season.SPRING)), false,
        "/assets/GameAssets/Crops/Jazz_Seeds.png"),
    CARROT_SEEDS("Carrot_Seeds", FarmingCropType.CARROT, new ArrayList<>(Arrays.asList(1, 1, 1, 0, 0)), 3,
        true, -1, 5, -1, new ArrayList<>(List.of(Season.SPRING)), false,
        "/assets/GameAssets/Crops/Carrot_Seeds.png"),
    CAULIFLOWER_SEEDS("Cauliflower_Seeds", FarmingCropType.CAULIFLOWER, new ArrayList<>(Arrays.asList(1, 2, 4, 4, 1)), 12,
        true, -1, 100, -1, new ArrayList<>(List.of(Season.SPRING)), true,
        "/assets/GameAssets/Crops/Cauliflower_Seeds.png"),
    COFFEE_BEAN_SEEDS("Coffee_Bean", FarmingCropType.COFFEE_BEAN, new ArrayList<>(Arrays.asList(1, 2, 2, 3, 2)), 10,
        false, 2, -1, 200, new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER)), false,
        "/assets/GameAssets/Crops/Coffee_Bean.png"),
    GARLIC_SEEDS("Garlic_Seeds", FarmingCropType.GARLIC, new ArrayList<>(Arrays.asList(1, 1, 1, 1, 0)), 4,
        true, -1, 40, 60, new ArrayList<>(List.of(Season.SPRING)), false,
        "/assets/GameAssets/Crops/Garlic_Seeds.png"),
    BEAN_STARTER("Bean_Starter", FarmingCropType.GREEN_BEAN, new ArrayList<>(Arrays.asList(1, 1, 1, 3, 4)), 10,
        false, 3, 60, 90, new ArrayList<>(List.of(Season.SPRING)), false,
        "/assets/GameAssets/Crops/Bean_Starter.png"),
    KALE_SEEDS("Kale_Seeds", FarmingCropType.KALE, new ArrayList<>(Arrays.asList(1, 2, 2, 1, 0)), 6,
        true, -1, 70, 105, new ArrayList<>(List.of(Season.SPRING)), false,
        "/assets/GameAssets/Crops/Kale_Seeds.png"),
    PARSNIP_SEEDS("Parsnip_Seeds", FarmingCropType.PARSNIP, new ArrayList<>(Arrays.asList(1, 1, 1, 1, 0)), 4,
        true, -1, 20, 30, new ArrayList<>(List.of(Season.SPRING)), false,
        "/assets/GameAssets/Crops/Parsnip_Seeds.png"),
    POTATO_SEEDS("Potato_Seeds", FarmingCropType.POTATO, new ArrayList<>(Arrays.asList(1, 1, 1, 2, 1)), 6,
        true, -1, 50, 75, new ArrayList<>(List.of(Season.SPRING)), false,
        "/assets/GameAssets/Crops/Potato_Seeds.png"),
    RHUBARB_SEEDS("Rhubarb_Seeds", FarmingCropType.RHUBARB, new ArrayList<>(Arrays.asList(2, 2, 2, 3, 4)), 13,
        true, -1, 100, -1, new ArrayList<>(List.of(Season.SPRING)), false,
        "/assets/GameAssets/Crops/Rhubarb_Seeds.png"),
    STRAWBERRY_SEEDS("Strawberry_Seeds", FarmingCropType.STRAWBERRY, new ArrayList<>(Arrays.asList(1, 1, 2, 2, 2)), 8,
        false, 4, 100, -1, new ArrayList<>(List.of(Season.SPRING)), false,
        "/assets/GameAssets/Crops/Strawberry_Seeds.png"),
    TULIP_BULB("Tulip_Bulb", FarmingCropType.TULIP, new ArrayList<>(Arrays.asList(1, 1, 2, 2, 0)), 6,
        true, -1, 25, -1, new ArrayList<>(List.of(Season.SPRING)), false,
        "/assets/GameAssets/Crops/Tulip_Bulb.png"),
    RICE_SHOOT("Rice_Shoot", FarmingCropType.UNMILLED_RICE, new ArrayList<>(Arrays.asList(1, 2, 2, 3, 0)), 8,
        true, -1, 40, 60, new ArrayList<>(List.of(Season.SPRING)), false,
        "/assets/GameAssets/Crops/Rice_Shoot.png"),

    // Summer Crops
    BLUEBERRY_SEEDS("Blueberry_Seeds", FarmingCropType.BLUEBERRY, new ArrayList<>(Arrays.asList(1, 3, 3, 4, 2)), 13,
        false, 4, 80, 120, new ArrayList<>(List.of(Season.SUMMER)), false,
        "/assets/GameAssets/Crops/Blueberry_Seeds.png"),
    CORN_SEEDS("Corn_Seeds", FarmingCropType.CORN, new ArrayList<>(Arrays.asList(2, 3, 3, 3, 3)), 14,
        false, 4, 150, 225, new ArrayList<>(Arrays.asList(Season.SUMMER, Season.FALL)), false,
        "/assets/GameAssets/Crops/Corn_Seeds.png"),
    HOPS_STARTER("Hops_Starter", FarmingCropType.HOPS, new ArrayList<>(Arrays.asList(1, 1, 2, 3, 4)), 11,
        false, 1, 60, 90, new ArrayList<>(List.of(Season.SUMMER)), false,
        "/assets/GameAssets/Crops/Hops_Starter.png"),
    PEPPER_SEEDS("Pepper_Seeds", FarmingCropType.HOT_PEPPER, new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1)), 5,
        false, 3, 40, 60, new ArrayList<>(List.of(Season.SUMMER)), false,
        "/assets/GameAssets/Crops/Pepper_Seeds.png"),
    MELON_SEEDS("Melon_Seeds", FarmingCropType.MELON, new ArrayList<>(Arrays.asList(1, 2, 3, 3, 3)), 12,
        true, -1, 80, 120, new ArrayList<>(List.of(Season.SUMMER)), true,
        "/assets/GameAssets/Crops/Melon_Seeds.png"),
    POPPY_SEEDS("Poppy_Seeds", FarmingCropType.POPPY, new ArrayList<>(Arrays.asList(1, 2, 2, 2, 0)), 7,
        true, -1, 100, 150, new ArrayList<>(List.of(Season.SUMMER)), false,
        "/assets/GameAssets/Crops/Poppy_Seeds.png"),
    RADISH_SEEDS("Radish_Seeds", FarmingCropType.RADISH, new ArrayList<>(Arrays.asList(2, 1, 2, 1, 0)), 6,
        true, -1, 40, 60, new ArrayList<>(Arrays.asList(Season.SUMMER)), false,
        "/assets/GameAssets/Crops/Radish_Seeds.png"),
    RED_CABBAGE_SEEDS("Red_Cabbage_Seeds", FarmingCropType.RED_CABBAGE, new ArrayList<>(Arrays.asList(2, 1, 2, 2, 2)), 9,
        true, -1, 100, 150, new ArrayList<>(Arrays.asList(Season.SUMMER)), false,
        "/assets/GameAssets/Crops/Red_Cabbage_Seeds.png"),
    STARFRUIT_SEEDS("Starfruit_Seeds", FarmingCropType.STARFRUIT, new ArrayList<>(Arrays.asList(2, 3, 2, 3, 3)), 13,
        true, -1, 400, -1, new ArrayList<>(Arrays.asList(Season.SUMMER)), false,
        "/assets/GameAssets/Crops/Starfruit_Seeds.png"),
    SPANGLE_SEEDS("Spangle_Seeds", FarmingCropType.SUMMER_SPANGLE, new ArrayList<>(Arrays.asList(1, 2, 3, 1, 0)), 8,
        true, -1, 62, -1, new ArrayList<>(Arrays.asList(Season.SUMMER)), false,
        "/assets/GameAssets/Crops/Spangle_Seeds.png"),
    SUMMER_SQUASH_SEEDS("Summer_Squash_Seeds", FarmingCropType.SUMMER_SQUASH, new ArrayList<>(Arrays.asList(1, 1, 1, 2, 1)), 6,
        false, 3, 10, -1, new ArrayList<>(Arrays.asList(Season.SUMMER)), false,
        "/assets/GameAssets/Crops/Summer_Squash_Seeds.png"),
    SUNFLOWER_SEEDS("Sunflower_Seeds", FarmingCropType.SUNFLOWER, new ArrayList<>(Arrays.asList(1, 2, 3, 2, 0)), 8,
        true, -1, 125, -1, new ArrayList<>(Arrays.asList(Season.SUMMER, Season.FALL)), false,
        "/assets/GameAssets/Crops/Sunflower_Seeds.png"),
    TOMATO_SEEDS("Tomato_Seeds", FarmingCropType.TOMATO, new ArrayList<>(Arrays.asList(2, 2, 2, 2, 3)), 11,
        false, 4, 62, -1, new ArrayList<>(Arrays.asList(Season.SUMMER)), false,
        "/assets/GameAssets/Crops/Tomato_Seeds.png"),
    WHEAT_SEEDS("Wheat_Seeds", FarmingCropType.WHEAT, new ArrayList<>(Arrays.asList(1, 1, 1, 1, 0)), 4,
        true, -1, 125, -1, new ArrayList<>(Arrays.asList(Season.SUMMER, Season.FALL)), false,
        "/assets/GameAssets/Crops/Wheat_Seeds.png"),

    // Fall Crops
    AMARANTH_SEEDS("Amaranth_Seeds", FarmingCropType.AMARANTH, new ArrayList<>(Arrays.asList(1, 2, 2, 2, 0)), 7,
        true, -1, 87, -1, new ArrayList<>(List.of(Season.FALL)), false,
        "/assets/GameAssets/Crops/Amaranth_Seeds.png"),
    ARTICHOKE_SEEDS("Artichoke_Seeds", FarmingCropType.ARTICHOKE, new ArrayList<>(Arrays.asList(2, 2, 1, 2, 1)), 8,
        true, -1, 30, 45, new ArrayList<>(Arrays.asList(Season.FALL)), false,
        "/assets/GameAssets/Crops/Artichoke_Seeds.png"),
    BEET_SEEDS("Beet_Seeds", FarmingCropType.BEET, new ArrayList<>(Arrays.asList(1, 1, 2, 2, 0)), 6,
        true, -1, 20, -1, new ArrayList<>(Arrays.asList(Season.FALL)), false,
        "/assets/GameAssets/Crops/Beet_Seeds.png"),
    BOK_CHOY_SEEDS("Bok_Choy_Seeds", FarmingCropType.BOK_CHOY, new ArrayList<>(Arrays.asList(1, 1, 1, 1, 0)), 4,
        true, -1, 50, 75, new ArrayList<>(Arrays.asList(Season.FALL)), false,
        "/assets/GameAssets/Crops/Bok_Choy_Seeds.png"),
    BROCCOLI_SEEDS("Broccoli_Seeds", FarmingCropType.BROCCOLI, new ArrayList<>(Arrays.asList(2, 2, 2, 2, 0)), 8,
        false, 4, 15, -1, new ArrayList<>(Arrays.asList(Season.FALL)), false,
        "/assets/GameAssets/Crops/Broccoli_Seeds.png"),
    CRANBERRY_SEEDS("Cranberry_Seeds", FarmingCropType.CRANBERRY, new ArrayList<>(Arrays.asList(1, 2, 1, 1, 2)), 7,
        false, 5, 300, -1, new ArrayList<>(Arrays.asList(Season.FALL)), false,
        "/assets/GameAssets/Crops/Cranberry_Seeds.png"),
    EGGPLANT_SEEDS("Eggplant_Seeds", FarmingCropType.EGGPLANT, new ArrayList<>(Arrays.asList(1, 1, 1, 1, 0)), 5,
        false, 5, 25, -1, new ArrayList<>(Arrays.asList(Season.FALL)), false,
        "/assets/GameAssets/Crops/Eggplant_Seeds.png"),
    FAIRY_SEEDS("Fairy_Seeds", FarmingCropType.FAIRY_ROSE, new ArrayList<>(Arrays.asList(1, 4, 4, 3, 0)), 12,
        true, -1, 250, -1, new ArrayList<>(Arrays.asList(Season.FALL)), false,
        "/assets/GameAssets/Crops/Fairy_Seeds.png"),
    GRAPE_STARTER("Grape_Starter", FarmingCropType.GRAPE, new ArrayList<>(Arrays.asList(1, 1, 2, 3, 3)), 10,
        false, 3, 75, -1, new ArrayList<>(Arrays.asList(Season.FALL)), false,
        "/assets/GameAssets/Crops/Grape_Starter.png"),
    PUMPKIN_SEEDS("Pumpkin_Seeds", FarmingCropType.PUMPKIN, new ArrayList<>(Arrays.asList(1, 2, 3, 4, 3)), 13,
        true, -1, 125, -1, new ArrayList<>(Arrays.asList(Season.FALL)), true,
        "/assets/GameAssets/Crops/Pumpkin_Seeds.png"),
    YAM_SEEDS("Yam_Seeds", FarmingCropType.YAM, new ArrayList<>(Arrays.asList(1, 3, 3, 3, 0)), 10,
        true, -1, 75, -1, new ArrayList<>(Arrays.asList(Season.FALL)), false,
        "/assets/GameAssets/Crops/Yam_Seeds.png"),
    RARE_SEED("Rare_Seed", FarmingCropType.SWEET_GEM_BERRY, new ArrayList<>(Arrays.asList(2, 4, 6, 6, 6)), 24,
        true, -1, 1000, -1, new ArrayList<>(Arrays.asList(Season.FALL)), false,
        "/assets/GameAssets/Crops/Rare_Seed.png"),

    // Winter Crop
    POWDERMELON_SEEDS("Powdermelon_Seeds", FarmingCropType.POWDERMELON, new ArrayList<>(Arrays.asList(1, 2, 1, 2, 1)), 7,
        true, -1, 20, -1, new ArrayList<>(Arrays.asList(Season.WINTER)), true,
        "/assets/GameAssets/Crops/Powdermelon_Seeds.png"),

    // Special Crop (Grows in all seasons except Winter)
    ANCIENT_SEEDS("Ancient_Seeds", FarmingCropType.ANCIENT_FRUIT, new ArrayList<>(Arrays.asList(2, 7, 7, 7, 5)), 28,
        false, 7, 500, -1,
        new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL)), false,
        "/assets/GameAssets/Crops/Ancient_Seeds.png");

    //TODO: Mixed seed left

    private final String name;
    public final FarmingCropType farmingCropType;
    private final ArrayList<Integer> stages;
    private final int totalHarvestTime;
    private final boolean oneTime;
    private final int regrowthTime;
    private final int seasonPrice;
    private final int otherSeasonPrice;
    private final ArrayList<Season> seasons;
    private final boolean canBecomeGiant;
    private final String imagePath;

    ForagingSeedType(String name, FarmingCropType farmingCropType, ArrayList<Integer> stages, int totalHarvestTime, boolean oneTime, int regrowthTime, int seasonPrice, int otherSeasonPrice, ArrayList<Season> seasons, boolean canBecomeGiant , String imagePath) {
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
        this.imagePath = imagePath;
    }



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
    public String imagePath() {
        return "";
    }

    public ArrayList<Integer> getStages() {
        return stages;
    }

    public FarmingCropType getFarmingCropType() {
        return farmingCropType;
    }

    public ArrayList<Season> getSeason() {return seasons;}

    public int getTotalHarvestTime() {
        return totalHarvestTime;
    }
    public boolean isOneTime() {
        return oneTime;
    }
    public int getRegrowthTime() {
        return regrowthTime;
    }
    public int getSeasonPrice() {
        return seasonPrice;
    }
    public int getOtherSeasonPrice() {
        return otherSeasonPrice;
    }



    @Override
    public String toString() {
        StringBuilder list = new StringBuilder();
        list.append("Source: ").append(name).append("\n");
        list.append("Stages: ");
        for (int i = 0; i < stages.size(); i++) {
            list.append(stages.get(i));
            if (i != stages.size() - 1)
                list.append("-");
        }
        list.append("\n");

        list.append("Total Harvest Time: ").append(totalHarvestTime).append("\n");
        list.append("One Time: ").append(oneTime).append("\n");
        list.append("Regrowth Time: ").append((regrowthTime >= 0) ? regrowthTime : "").append("\n");
        list.append("Season: ");
        for (int i = 0; i < seasons.size(); i++) {
            list.append(seasons.get(i));
            if (i != seasons.size() - 1)
                list.append(", ");
        }
        list.append("\n");

        list.append("Can Become Giant: ").append(canBecomeGiant).append("\n");
        return list.toString();
    }
}
