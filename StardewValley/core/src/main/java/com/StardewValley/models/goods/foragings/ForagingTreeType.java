package com.StardewValley.models.goods.foragings;

import com.StardewValley.models.Pair;
import com.StardewValley.models.enums.Season;
import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.goods.farmings.FarmingCropType;
import com.StardewValley.models.goods.farmings.FarmingTreeType;
import com.StardewValley.models.goods.foods.FoodType;
import com.StardewValley.models.goods.products.ProductType;

import java.util.ArrayList;
import java.util.Arrays;

public enum ForagingTreeType implements GoodType {
    ACORNS("Acorns", FarmingTreeType.OAK_TREE, new ArrayList<>(Arrays.asList(
            new Pair<>(FoodType.COMMON_MUSHROOM, 2),
            new Pair<>(ProductType.WOOD, 16),
            new Pair<>(FoodType.SAP, 2))),
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER)), -1),

    MAPLE_SEEDS("Maple_Seeds", FarmingTreeType.MAPLE_TREE, new ArrayList<>(Arrays.asList(
            new Pair<>(FarmingCropType.MAPLE_SYRUP, 2),
            new Pair<>(ProductType.WOOD, 16),
            new Pair<>(FoodType.SAP, 2))),
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER)), -1),

    PINE_CONES("Pine_Cones", FarmingTreeType.PINE_TREE, new ArrayList<>(Arrays.asList(
            new Pair<>(FarmingCropType.PINE_TAR, 2),
            new Pair<>(ProductType.WOOD, 16),
            new Pair<>(FoodType.SAP, 2))),
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER)), -1),

    MAHOGANY_SEEDS("Mahogany_Seeds", FarmingTreeType.MAHOGANY_TREE, new ArrayList<>(Arrays.asList(
            new Pair<>(FoodType.SAP, 2),
            new Pair<>(ProductType.WOOD, 16),
            new Pair<>(FoodType.SAP, 2))),
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER)), -1),

    MUSHROOM_TREE_SEEDS("Mushroom_Tree_Seeds", FarmingTreeType.MUSHROOM_TREE, new ArrayList<>(Arrays.asList(
            new Pair<>(FoodType.COMMON_MUSHROOM, 2),
            new Pair<>(ProductType.WOOD, 16),
            new Pair<>(FoodType.SAP, 2))),
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER)), -1);

    private String name;
    private FarmingTreeType farmingTreeType;
    private ArrayList<Integer> stages;
    private int totalHarvestTime;
    private final ArrayList<Pair<GoodType, Integer>> products;
    private ArrayList<Season> seasons;
    private int price;

    ForagingTreeType(String name, FarmingTreeType farmingTreeType, ArrayList<Pair<GoodType, Integer>> products,
                     ArrayList<Season> seasons, int price) {
        this.name = name;
        this.farmingTreeType = farmingTreeType;
        this.stages = new ArrayList<>(Arrays.asList(7, 7, 7, 7));
        this.totalHarvestTime = 28;
        this.products = products;
        this.seasons = seasons;
        this.price = price;
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
        list.append("Season: ");
        for (int i = 0; i < seasons.size(); i++) {
            list.append(seasons.get(i));
            if(i != seasons.size() - 1)
                list.append(", ");
        }
        list.append("\n");

        return list.toString();
    }

    public ArrayList<Pair<GoodType, Integer>> getProducts() {
        return products;
    }
}
