package org.example.models.goods.foragings;

import org.example.models.enums.Season;
import org.example.models.goods.GoodType;
import org.example.models.goods.farmings.FarmingCropType;
import org.example.models.goods.farmings.FarmingTreeType;
import org.example.models.goods.foods.FoodType;

import java.util.ArrayList;
import java.util.Arrays;

public enum ForagingTreeType implements GoodType {
    ACORNS("Acorns", FarmingTreeType.OAK_TREE, FarmingCropType.OAK_RESIN,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER)), -1),
    MAPLE_SEEDS("Maple_Seeds", FarmingTreeType.MAPLE_TREE, FarmingCropType.MAPLE_SYRUP,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER)), -1),
    PINE_CONES("Pine_Cones", FarmingTreeType.PINE_TREE, FarmingCropType.PINE_TAR,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER)), -1),
    MAHOGANY_SEEDS("Mahogany_Seeds", FarmingTreeType.MAHOGANY_TREE, FoodType.SAP,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER)), -1),
    MUSHROOM_TREE_SEEDS("Mushroom_Tree_Seeds", FarmingTreeType.MUSHROOM_TREE, FoodType.COMMON_MUSHROOM,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER)), -1);

    private String name;
    private FarmingTreeType farmingTreeType;
    private ArrayList<Integer> stages;
    private int totalHarvestTime;
    private GoodType goodType;
    private ArrayList<Season> seasons;
    private int price;

    ForagingTreeType(String name, FarmingTreeType farmingTreeType, GoodType goodType,
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
}
