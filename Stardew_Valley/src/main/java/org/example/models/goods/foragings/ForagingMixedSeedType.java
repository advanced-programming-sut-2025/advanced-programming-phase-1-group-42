package org.example.models.goods.foragings;

import org.example.models.enums.Season;
import org.example.models.goods.GoodType;
import org.example.models.goods.farmings.FarmingCropType;

import java.util.ArrayList;
import java.util.Arrays;

public enum ForagingMixedSeedType implements GoodType {
    SPRING_SEED("Spring_Mixed_Seed", Season.SPRING,
            new ArrayList<>(Arrays.asList(FarmingCropType.CAULIFLOWER, FarmingCropType.PARSNIP,
                    FarmingCropType.POTATO, FarmingCropType.BLUE_JAZZ, FarmingCropType.TULIP))),

    SUMMER_SEED("Summer_Mixed_Seed", Season.SUMMER,
            new ArrayList<>(Arrays.asList(FarmingCropType.CORN, FarmingCropType.HOT_PEPPER, FarmingCropType.RADISH,
                    FarmingCropType.WHEAT, FarmingCropType.POPPY, FarmingCropType.SUNFLOWER, FarmingCropType.SUMMER_SPANGLE))),

    FALL_SEED("Fall_Mixed_Seed", Season.FALL,
            new ArrayList<>(Arrays.asList(FarmingCropType.ARTICHOKE, FarmingCropType.CORN, FarmingCropType.EGGPLANT,
                    FarmingCropType.PUMPKIN, FarmingCropType.SUNFLOWER, FarmingCropType.FAIRY_ROSE))),

    WINTER_SEED("Winter_Mixed_Seed", Season.WINTER,
            new ArrayList<>(Arrays.asList(FarmingCropType.POWDERMELON)));

    private String name;
    private Season season;
    private final ArrayList<GoodType> possibleCrops;

    ForagingMixedSeedType(String name, Season season, ArrayList<GoodType> possibleCrops) {
        this.name = name;
        this.season = season;
        this.possibleCrops = possibleCrops;
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


}
