package org.example.models.goods.foragings;

import org.example.models.enums.Season;
import org.example.models.game_structure.Farm;
import org.example.models.goods.Good;
import org.example.models.goods.GoodType;
import org.example.models.goods.farmings.Farming;
import org.example.models.goods.farmings.FarmingGoodType;

import java.util.ArrayList;
import java.util.Arrays;

public enum MixedSeedType implements GoodType {
    SPRING_SEED(Season.SPRING,
            new ArrayList<>(Arrays.asList(FarmingGoodType.CAULIFLOWER, FarmingGoodType.PARSNIP,
                    FarmingGoodType.POTATO, FarmingGoodType.BLUE_JAZZ, FarmingGoodType.TULIP))),

    SUMMER_SEED(Season.SUMMER,
            new ArrayList<>(Arrays.asList(FarmingGoodType.CORN, FarmingGoodType.HOT_PEPPER, FarmingGoodType.RADISH,
                    FarmingGoodType.WHEAT, FarmingGoodType.POPPY, FarmingGoodType.SUNFLOWER, FarmingGoodType.SUMMER_SPANGLE))),

    FALL_SEED(Season.FALL,
            new ArrayList<>(Arrays.asList(FarmingGoodType.ARTICHOKE, FarmingGoodType.CORN, FarmingGoodType.EGGPLANT,
                    FarmingGoodType.PUMPKIN, FarmingGoodType.SUNFLOWER, FarmingGoodType.FAIRY_ROSE))),

    WINTER_SEED(Season.WINTER,
            new ArrayList<>(Arrays.asList(FarmingGoodType.POWDERMELON)));

    private Season season;
    private final ArrayList<GoodType> possibleCrops;

    MixedSeedType(Season season, ArrayList<GoodType> possibleCrops) {
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
        return "";
    }
}
