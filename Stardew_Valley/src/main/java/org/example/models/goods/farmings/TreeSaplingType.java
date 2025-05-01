package org.example.models.goods.farmings;

import org.example.models.enums.Season;
import org.example.models.goods.GoodType;
import org.example.models.goods.foods.FoodType;
import org.example.models.goods.foragings.CropType;

import java.util.ArrayList;
import java.util.Arrays;

public enum TreeSaplingType implements GoodType {
    // Fruit Trees
    APRICOT_SAPLING("Apricot_Sapling", TreeType.APRICOT_TREE, FoodType.APRICOT,
            new ArrayList<>(Arrays.asList(Season.SPRING)), 2000),
    CHERRY_SAPLING("Cherry_Sapling", TreeType.CHERRY_TREE, FoodType.CHERRY,
            new ArrayList<>(Arrays.asList(Season.SPRING)), 3400),
    BANANA_SAPLING("Banana_Sapling", TreeType.BANANA_TREE, FoodType.BANANA,
            new ArrayList<>(Arrays.asList(Season.SUMMER)), -1),
    MANGO_SAPLING("Mango_Sapling", TreeType.MANGO_TREE, FoodType.MANGO,
            new ArrayList<>(Arrays.asList(Season.SUMMER)), -1),
    ORANGE_SAPLING("Orange_Sapling", TreeType.ORANGE_TREE, FoodType.ORANGE,
            new ArrayList<>(Arrays.asList(Season.SUMMER)), 4000),
    PEACH_SAPLING("Peach_Sapling", TreeType.PEACH_TREE, FoodType.PEACH,
            new ArrayList<>(Arrays.asList(Season.SUMMER)), 6000),
    APPLE_SAPLING("Apple_Sapling", TreeType.APPLE_TREE, FoodType.APPLE,
            new ArrayList<>(Arrays.asList(Season.FALL)), 4000),
    POMEGRANATE_SAPLING("Pomegranate_Sapling", TreeType.POMEGRANATE_TREE, FoodType.POMEGRANATE,
            new ArrayList<>(Arrays.asList(Season.FALL)), 6000),

    // Special Trees
    MYSTIC_SAPLING("Mystic_Sapling", TreeType.MYSTIC_TREE, FoodType.MYSTIC_SYRUP,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER)), -1);

    private String name;
    private TreeType treeType;
    private ArrayList<Integer> stages;
    private int totalHarvestTime;
    private GoodType goodType;
    private ArrayList<Season> seasons;
    private int price;

    TreeSaplingType(String name, TreeType treeType, GoodType goodType,
                    ArrayList<Season> seasons, int price) {
        this.name = name;
        this.treeType = treeType;
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

    @Override
    public int getEnergy() {
        return 0;
    }
}
