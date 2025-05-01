package org.example.models.goods.farmings;

import org.example.models.enums.Season;
import org.example.models.goods.GoodType;
import org.example.models.goods.foods.FoodType;
import org.example.models.goods.foragings.CropType;

import java.util.ArrayList;
import java.util.Arrays;

public enum TreeType implements GoodType {
    APRICOT_TREE("Apricot_Tree", TreeSaplingType.APRICOT_SAPLING, FoodType.APRICOT, 1,
            new ArrayList<>(Arrays.asList(Season.SPRING))),

    CHERRY_TREE("Cherry_Tree", TreeSaplingType.CHERRY_SAPLING, FoodType.CHERRY, 1,
            new ArrayList<>(Arrays.asList(Season.SPRING))),

    BANANA_TREE("Banana_Tree", TreeSaplingType.BANANA_SAPLING, FoodType.BANANA, 1,
            new ArrayList<>(Arrays.asList(Season.SUMMER))),

    MANGO_TREE("Mango_Tree", TreeSaplingType.MANGO_SAPLING, FoodType.MANGO, 1,
            new ArrayList<>(Arrays.asList(Season.SUMMER))),

    ORANGE_TREE("Orange_Tree", TreeSaplingType.ORANGE_SAPLING, FoodType.ORANGE, 1,
            new ArrayList<>(Arrays.asList(Season.SUMMER))),

    PEACH_TREE("Peach_Tree", TreeSaplingType.PEACH_SAPLING, FoodType.PEACH, 1,
            new ArrayList<>(Arrays.asList(Season.SUMMER))),

    APPLE_TREE("Apple_Tree", TreeSaplingType.APPLE_SAPLING, FoodType.APPLE, 1,
            new ArrayList<>(Arrays.asList(Season.FALL))),

    POMEGRANATE_TREE("Pomegranate_Tree", TreeSaplingType.POMEGRANATE_SAPLING, FoodType.POMEGRANATE, 1,
            new ArrayList<>(Arrays.asList(Season.FALL))),

    OAK_TREE("Oak_Tree", org.example.models.goods.foragings.TreeType.ACORNS, CropType.OAK_RESIN, 7,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))),

    MAPLE_TREE("Maple_Tree", org.example.models.goods.foragings.TreeType.MAPLE_SEEDS, CropType.MAPLE_SYRUP, 9,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))),

    PINE_TREE("Pine_Tree", org.example.models.goods.foragings.TreeType.PINE_CONES, CropType.PINE_TAR, 5,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))),

    MAHOGANY_TREE("Mahogany_Tree", org.example.models.goods.foragings.TreeType.MAHOGANY_SEEDS, FoodType.SAP, 1,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))),

    MUSHROOM_TREE("Mushroom_Tree", org.example.models.goods.foragings.TreeType.MUSHROOM_TREE_SEEDS, FoodType.COMMON_MUSHROOM, 1,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))),

    MYSTIC_TREE("Mystic_Tree", TreeSaplingType.MYSTIC_SAPLING, FoodType.MYSTIC_SYRUP, 7,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER)));


    private String name;
    private GoodType saplingType;
    private GoodType goodType;
    private int fruitHarvestCycle;
    private ArrayList<Season> seasons;


    TreeType(String name, GoodType saplingType, GoodType goodType, int fruitHarvestCycle,
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
}
