package org.example.models.goods.foragings;

import org.example.models.enums.Season;
import org.example.models.goods.GoodType;
import org.example.models.goods.foods.FoodType;

import java.util.ArrayList;
import java.util.Arrays;

public enum TreeType implements GoodType {
    ACORNS("Acorns", org.example.models.goods.farmings.TreeType.OAK_TREE, CropType.OAK_RESIN,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER)), -1),
    MAPLE_SEEDS("Maple_Seeds", org.example.models.goods.farmings.TreeType.MAPLE_TREE, CropType.MAPLE_SYRUP,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER)), -1),
    PINE_CONES("Pine_Cones", org.example.models.goods.farmings.TreeType.PINE_TREE, CropType.PINE_TAR,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER)), -1),
    MAHOGANY_SEEDS("Mahogany_Seeds", org.example.models.goods.farmings.TreeType.MAHOGANY_TREE, FoodType.SAP,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER)), -1),
    MUSHROOM_TREE_SEEDS("Mushroom_Tree_Seeds", org.example.models.goods.farmings.TreeType.MUSHROOM_TREE, FoodType.COMMON_MUSHROOM,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER)), -1);

    private String name;
    private org.example.models.goods.farmings.TreeType treeType;
    private ArrayList<Integer> stages;
    private int totalHarvestTime;
    private GoodType goodType;
    private ArrayList<Season> seasons;
    private int price;

    TreeType(String name, org.example.models.goods.farmings.TreeType treeType, GoodType goodType,
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
        return -1;
    }

    @Override
    public int getEnergy() {
        return -1;
    }
}
