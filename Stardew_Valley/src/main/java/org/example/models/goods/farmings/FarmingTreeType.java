package org.example.models.goods.farmings;

import org.example.models.Pair;
import org.example.models.enums.Season;
import org.example.models.goods.GoodType;
import org.example.models.goods.foods.FoodType;
import org.example.models.goods.foragings.ForagingTreeType;
import org.example.models.goods.products.ProductType;

import java.util.ArrayList;
import java.util.Arrays;

public enum FarmingTreeType implements GoodType {
    APRICOT_TREE("Apricot_Tree", FarmingTreeSaplingType.APRICOT_SAPLING,
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FoodType.APRICOT, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))),
            1, new ArrayList<>(Arrays.asList(Season.SPRING))),

    CHERRY_TREE("Cherry_Tree", FarmingTreeSaplingType.CHERRY_SAPLING,
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FoodType.CHERRY, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))),
            1, new ArrayList<>(Arrays.asList(Season.SPRING))),

    BANANA_TREE("Banana_Tree", FarmingTreeSaplingType.BANANA_SAPLING,
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FoodType.BANANA, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))),
            1, new ArrayList<>(Arrays.asList(Season.SUMMER))),

    MANGO_TREE("Mango_Tree", FarmingTreeSaplingType.MANGO_SAPLING,
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FoodType.MANGO, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))),
            1, new ArrayList<>(Arrays.asList(Season.SUMMER))),

    ORANGE_TREE("Orange_Tree", FarmingTreeSaplingType.ORANGE_SAPLING,
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FoodType.ORANGE, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))),
            1, new ArrayList<>(Arrays.asList(Season.SUMMER))),

    PEACH_TREE("Peach_Tree", FarmingTreeSaplingType.PEACH_SAPLING,
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FoodType.PEACH, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))),
            1, new ArrayList<>(Arrays.asList(Season.SUMMER))),

    APPLE_TREE("Apple_Tree", FarmingTreeSaplingType.APPLE_SAPLING,
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FoodType.APPLE, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))),
            1, new ArrayList<>(Arrays.asList(Season.FALL))),

    POMEGRANATE_TREE("Pomegranate_Tree", FarmingTreeSaplingType.POMEGRANATE_SAPLING,
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FoodType.POMEGRANATE, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))),
            1, new ArrayList<>(Arrays.asList(Season.FALL))),

    OAK_TREE("Oak_Tree", ForagingTreeType.ACORNS,
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FarmingCropType.OAK_RESIN, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))), 7,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))),

    MAPLE_TREE("Maple_Tree", ForagingTreeType.MAPLE_SEEDS,
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FarmingCropType.MAPLE_SYRUP, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))), 9,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))),

    PINE_TREE("Pine_Tree", ForagingTreeType.PINE_CONES,
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FarmingCropType.PINE_TAR, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))), 5,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))),

    MAHOGANY_TREE("Mahogany_Tree", ForagingTreeType.MAHOGANY_SEEDS,
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FoodType.SAP, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))), 1,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))),

    MUSHROOM_TREE("Mushroom_Tree", ForagingTreeType.MUSHROOM_TREE_SEEDS,
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FoodType.COMMON_MUSHROOM, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))), 1,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))),

    MYSTIC_TREE("Mystic_Tree", FarmingTreeSaplingType.MYSTIC_SAPLING,
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FoodType.MYSTIC_SYRUP, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))), 7,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER)));


    private String name;
    private GoodType saplingType;
    private final ArrayList<Pair<GoodType, Integer>> products;
    private int fruitHarvestCycle;
    private ArrayList<Season> seasons;


    FarmingTreeType(String name, GoodType saplingType, ArrayList<Pair<GoodType, Integer>> products, int fruitHarvestCycle, ArrayList<Season> seasons) {
        this.name = name;
        this.saplingType = saplingType;
        this.products = products;
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

    public ArrayList<Pair<GoodType, Integer>> getProducts() {
        return products;
    }
}
