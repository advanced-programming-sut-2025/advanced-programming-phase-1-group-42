package com.StardewValley.models.goods.foragings;

import com.StardewValley.models.enums.Season;
import com.StardewValley.models.goods.GoodType;

import java.util.ArrayList;
import java.util.Arrays;

public enum ForagingMixedSeedType implements GoodType {
    SPRING_SEED("Spring_Mixed_Seed", Season.SPRING,
            new ArrayList<>(Arrays.asList(ForagingSeedType.CAULIFLOWER_SEEDS,ForagingSeedType.PARSNIP_SEEDS,
                    ForagingSeedType.POTATO_SEEDS, ForagingSeedType.JAZZ_SEEDS, ForagingSeedType.TULIP_BULB))),

    SUMMER_SEED("Summer_Mixed_Seed", Season.SUMMER,
            new ArrayList<>(Arrays.asList(ForagingSeedType.CORN_SEEDS, ForagingSeedType.PEPPER_SEEDS,
                    ForagingSeedType.RADISH_SEEDS, ForagingSeedType.WHEAT_SEEDS, ForagingSeedType.POPPY_SEEDS,
                    ForagingSeedType.SUNFLOWER_SEEDS, ForagingSeedType.SPANGLE_SEEDS))),

    FALL_SEED("Fall_Mixed_Seed", Season.FALL,
            new ArrayList<>(Arrays.asList(ForagingSeedType.ARTICHOKE_SEEDS, ForagingSeedType.CORN_SEEDS,
                    ForagingSeedType.EGGPLANT_SEEDS, ForagingSeedType.PUMPKIN_SEEDS, ForagingSeedType.SUNFLOWER_SEEDS,
                    ForagingSeedType.FAIRY_SEEDS))),

    WINTER_SEED("Winter_Mixed_Seed", Season.WINTER,
            new ArrayList<>(Arrays.asList(ForagingSeedType.POWDERMELON_SEEDS)));

    private String name;
    private Season season;
    private final ArrayList<GoodType> possibleCrops;

    ForagingMixedSeedType(String name, Season season, ArrayList<GoodType> possibleCrops) {
        this.name = name;
        this.season = season;
        this.possibleCrops = possibleCrops;
    }

    public ArrayList<GoodType> getPossibleCrops() {
        return possibleCrops;
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
    public String toString() {
        StringBuilder list =  new StringBuilder();
        list.append("Name: ").append(name).append("\n");
        list.append("Season: ").append(season).append("\n");
        list.append("Possible Crops: ").append(possibleCrops).append("\n");
        for (GoodType goodType : possibleCrops) {
            list.append(goodType.getName()).append("\n");
        }
        list.append("\n");

        return list.toString();
    }


}
