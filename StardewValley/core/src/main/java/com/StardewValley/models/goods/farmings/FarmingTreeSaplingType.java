package com.StardewValley.models.goods.farmings;

import com.StardewValley.models.enums.Season;
import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.goods.foods.FoodType;

import java.util.ArrayList;
import java.util.Arrays;

public enum FarmingTreeSaplingType implements GoodType {
    // Fruit Trees
    APRICOT_SAPLING("Apricot_Sapling", FarmingTreeType.APRICOT_TREE, FoodType.APRICOT,
            new ArrayList<>(Arrays.asList(Season.SPRING)), 2000),
    CHERRY_SAPLING("Cherry_Sapling", FarmingTreeType.CHERRY_TREE, FoodType.CHERRY,
            new ArrayList<>(Arrays.asList(Season.SPRING)), 3400),
    BANANA_SAPLING("Banana_Sapling", FarmingTreeType.BANANA_TREE, FoodType.BANANA,
            new ArrayList<>(Arrays.asList(Season.SUMMER)), -1),
    MANGO_SAPLING("Mango_Sapling", FarmingTreeType.MANGO_TREE, FoodType.MANGO,
            new ArrayList<>(Arrays.asList(Season.SUMMER)), -1),
    ORANGE_SAPLING("Orange_Sapling", FarmingTreeType.ORANGE_TREE, FoodType.ORANGE,
            new ArrayList<>(Arrays.asList(Season.SUMMER)), 4000),
    PEACH_SAPLING("Peach_Sapling", FarmingTreeType.PEACH_TREE, FoodType.PEACH,
            new ArrayList<>(Arrays.asList(Season.SUMMER)), 6000),
    APPLE_SAPLING("Apple_Sapling", FarmingTreeType.APPLE_TREE, FoodType.APPLE,
            new ArrayList<>(Arrays.asList(Season.FALL)), 4000),
    POMEGRANATE_SAPLING("Pomegranate_Sapling", FarmingTreeType.POMEGRANATE_TREE, FoodType.POMEGRANATE,
            new ArrayList<>(Arrays.asList(Season.FALL)), 6000),

    // Special Trees
    MYSTIC_SAPLING("Mystic_Sapling", FarmingTreeType.MYSTIC_TREE, FoodType.MYSTIC_SYRUP,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER)), -1);

    private String name;
    private FarmingTreeType farmingTreeType;
    private ArrayList<Integer> stages;
    private int totalHarvestTime;
    private GoodType goodType;
    private ArrayList<Season> seasons;
    private int price;

    FarmingTreeSaplingType(String name, FarmingTreeType farmingTreeType, GoodType goodType,
                           ArrayList<Season> seasons, int price) {
        this.name = name;
        this.farmingTreeType = farmingTreeType;
        this.stages = new ArrayList<>(Arrays.asList(7, 7, 7, 7));
        this.totalHarvestTime = 28;
        this.goodType = goodType;
        this.seasons = seasons;
        this.price = price;
    }

    @Override
    public int getSellPrice() {
        return price;
    }

    //Saplings can't be eaten
    @Override
    public int getEnergy() {
        return -1;
    }

    @Override
    public String getName() {
        return name;
    }

    public ArrayList<Integer> getStages() {
        return stages;
    }

    public FarmingTreeType getFarmingTreeType() {
        return farmingTreeType;
    }


}
