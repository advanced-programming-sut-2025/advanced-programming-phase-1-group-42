package org.example.models.goods.farmings;

import org.example.models.enums.Season;
import org.example.models.goods.GoodType;
import org.example.models.goods.foods.FoodType;
import org.example.models.goods.foragings.ForagingTreeType;

import java.util.ArrayList;
import java.util.Arrays;

public enum FarmingTreeType implements GoodType {
    APRICOT_TREE("Apricot_Tree", FarmingTreeSaplingType.APRICOT_SAPLING, FoodType.APRICOT, 1,
            new ArrayList<>(Arrays.asList(Season.SPRING))),

    CHERRY_TREE("Cherry_Tree", FarmingTreeSaplingType.CHERRY_SAPLING, FoodType.CHERRY, 1,
            new ArrayList<>(Arrays.asList(Season.SPRING))),

    BANANA_TREE("Banana_Tree", FarmingTreeSaplingType.BANANA_SAPLING, FoodType.BANANA, 1,
            new ArrayList<>(Arrays.asList(Season.SUMMER))),

    MANGO_TREE("Mango_Tree", FarmingTreeSaplingType.MANGO_SAPLING, FoodType.MANGO, 1,
            new ArrayList<>(Arrays.asList(Season.SUMMER))),

    ORANGE_TREE("Orange_Tree", FarmingTreeSaplingType.ORANGE_SAPLING, FoodType.ORANGE, 1,
            new ArrayList<>(Arrays.asList(Season.SUMMER))),

    PEACH_TREE("Peach_Tree", FarmingTreeSaplingType.PEACH_SAPLING, FoodType.PEACH, 1,
            new ArrayList<>(Arrays.asList(Season.SUMMER))),

    APPLE_TREE("Apple_Tree", FarmingTreeSaplingType.APPLE_SAPLING, FoodType.APPLE, 1,
            new ArrayList<>(Arrays.asList(Season.FALL))),

    POMEGRANATE_TREE("Pomegranate_Tree", FarmingTreeSaplingType.POMEGRANATE_SAPLING, FoodType.POMEGRANATE, 1,
            new ArrayList<>(Arrays.asList(Season.FALL))),

    OAK_TREE("Oak_Tree", ForagingTreeType.ACORNS, FarmingCropType.OAK_RESIN, 7,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))),

    MAPLE_TREE("Maple_Tree", ForagingTreeType.MAPLE_SEEDS, FarmingCropType.MAPLE_SYRUP, 9,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))),

    PINE_TREE("Pine_Tree", ForagingTreeType.PINE_CONES, FarmingCropType.PINE_TAR, 5,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))),

    MAHOGANY_TREE("Mahogany_Tree", ForagingTreeType.MAHOGANY_SEEDS, FoodType.SAP, 1,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))),

    MUSHROOM_TREE("Mushroom_Tree", ForagingTreeType.MUSHROOM_TREE_SEEDS, FoodType.COMMON_MUSHROOM, 1,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))),

    MYSTIC_TREE("Mystic_Tree", FarmingTreeSaplingType.MYSTIC_SAPLING, FoodType.MYSTIC_SYRUP, 7,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER)));


    private String name;
    private GoodType saplingType;
    private GoodType goodType;
    private int fruitHarvestCycle;
    private ArrayList<Season> seasons;


    FarmingTreeType(String name, GoodType saplingType, GoodType goodType, int fruitHarvestCycle,
                    ArrayList<Season> seasons) {
        this.name = name;
        this.saplingType = saplingType;
        this.goodType = goodType;
        this.fruitHarvestCycle = fruitHarvestCycle;
        this.seasons = seasons;
    }



    @Override
    public int getSellPrice() {
        return -1;
    }

    @Override
    public int getEnergy() {
        return -1;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
