package com.StardewValley.models.goods.farmings;

import com.StardewValley.models.Pair;
import com.StardewValley.models.enums.Season;
import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.goods.foods.FoodType;
import com.StardewValley.models.goods.foragings.ForagingTreeType;
import com.StardewValley.models.goods.products.ProductType;

import java.util.ArrayList;
import java.util.Arrays;

public enum FarmingTreeType implements GoodType {
    APRICOT_TREE("Apricot_Tree", FarmingTreeSaplingType.APRICOT_SAPLING,
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FoodType.APRICOT, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))),
            1, new ArrayList<>(Arrays.asList(Season.SPRING))
    ,new ArrayList<>(Arrays.asList("\\assets\\GameAssets\\Trees\\Apricot_Stage_1.png",
        "\\assets\\GameAssets\\Trees\\Apricot_Stage_2.png",
        "\\assets\\GameAssets\\Trees\\Apricot_Stage_3.png",
        "\\assets\\GameAssets\\Trees\\Apricot_Stage_4.png"))),

    CHERRY_TREE("Cherry_Tree", FarmingTreeSaplingType.CHERRY_SAPLING,
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FoodType.CHERRY, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))),
            1, new ArrayList<>(Arrays.asList(Season.SPRING))
        ,new ArrayList<>(Arrays.asList("\\assets\\GameAssets\\Trees\\Cherry_Stage_1.png",
        "\\assets\\GameAssets\\Trees\\Cherry_Stage_2.png",
        "\\assets\\GameAssets\\Trees\\Cherry_Stage_3.png",
        "\\assets\\GameAssets\\Trees\\Cherry_Stage_4.png"))),

    BANANA_TREE("Banana_Tree", FarmingTreeSaplingType.BANANA_SAPLING,
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FoodType.BANANA, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))),
            1, new ArrayList<>(Arrays.asList(Season.SUMMER))
        ,new ArrayList<>(Arrays.asList("\\assets\\GameAssets\\Trees\\Banana_Stage_1.png",
        "\\assets\\GameAssets\\Trees\\Banana_Stage_2.png",
        "\\assets\\GameAssets\\Trees\\Banana_Stage_3.png",
        "\\assets\\GameAssets\\Trees\\Banana_Stage_4.png"))),

    MANGO_TREE("Mango_Tree", FarmingTreeSaplingType.MANGO_SAPLING,
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FoodType.MANGO, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))),
            1, new ArrayList<>(Arrays.asList(Season.SUMMER))
        ,new ArrayList<>(Arrays.asList("\\assets\\GameAssets\\Trees\\Mango_Stage_1.png",
        "\\assets\\GameAssets\\Trees\\Mango_Stage_2.png",
        "\\assets\\GameAssets\\Trees\\Mango_Stage_3.png",
        "\\assets\\GameAssets\\Trees\\Mango_Stage_4.png"))),

    ORANGE_TREE("Orange_Tree", FarmingTreeSaplingType.ORANGE_SAPLING,
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FoodType.ORANGE, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))),
            1, new ArrayList<>(Arrays.asList(Season.SUMMER))
        ,new ArrayList<>(Arrays.asList("\\assets\\GameAssets\\Trees\\Orange_Stage_1.png",
        "\\assets\\GameAssets\\Trees\\Orange_Stage_2.png",
        "\\assets\\GameAssets\\Trees\\Orange_Stage_3.png",
        "\\assets\\GameAssets\\Trees\\Orange_Stage_4.png"))),

    PEACH_TREE("Peach_Tree", FarmingTreeSaplingType.PEACH_SAPLING,
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FoodType.PEACH, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))),
            1, new ArrayList<>(Arrays.asList(Season.SUMMER))
        ,new ArrayList<>(Arrays.asList("\\assets\\GameAssets\\Trees\\Peach_Stage_1.png",
        "\\assets\\GameAssets\\Trees\\Peach_Stage_2.png",
        "\\assets\\GameAssets\\Trees\\Peach_Stage_3.png",
        "\\assets\\GameAssets\\Trees\\Peach_Stage_4.png"))),

    APPLE_TREE("Apple_Tree", FarmingTreeSaplingType.APPLE_SAPLING,
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FoodType.APPLE, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))),
            1, new ArrayList<>(Arrays.asList(Season.FALL))
        ,new ArrayList<>(Arrays.asList("\\assets\\GameAssets\\Trees\\Apple_Stage_1.png",
        "\\assets\\GameAssets\\Trees\\Apple_Stage_2.png",
        "\\assets\\GameAssets\\Trees\\Apple_Stage_3.png",
        "\\assets\\GameAssets\\Trees\\Apple_Stage_4.png"))),

    POMEGRANATE_TREE("Pomegranate_Tree", FarmingTreeSaplingType.POMEGRANATE_SAPLING,
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FoodType.POMEGRANATE, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))),
            1, new ArrayList<>(Arrays.asList(Season.FALL))
        ,new ArrayList<>(Arrays.asList("\\assets\\GameAssets\\Trees\\Pomegranate_Stage_1.png",
        "\\assets\\GameAssets\\Trees\\Pomegranate_Stage_2.png",
        "\\assets\\GameAssets\\Trees\\Pomegranate_Stage_3.png",
        "\\assets\\GameAssets\\Trees\\Pomegranate_Stage_4.png"))),

    OAK_TREE("Oak_Tree", ForagingTreeType.ACORNS,
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FarmingCropType.OAK_RESIN, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))), 7,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))
        ,new ArrayList<>(Arrays.asList("\\assets\\GameAssets\\Trees\\Oak_Stage_1.png",
        "\\assets\\GameAssets\\Trees\\Oak_Stage_2.png",
        "\\assets\\GameAssets\\Trees\\Oak_Stage_3.png",
        "\\assets\\GameAssets\\Trees\\Oak_Stage_4.png"))),

    MAPLE_TREE("Maple_Tree", ForagingTreeType.MAPLE_SEEDS,
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FarmingCropType.MAPLE_SYRUP, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))), 9,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))
        ,new ArrayList<>(Arrays.asList("\\assets\\GameAssets\\Trees\\Maple_Stage_1.png",
        "\\assets\\GameAssets\\Trees\\Maple_Stage_2.png",
        "\\assets\\GameAssets\\Trees\\Maple_Stage_3.png",
        "\\assets\\GameAssets\\Trees\\Maple_Stage_4.png"))),

    PINE_TREE("Pine_Tree", ForagingTreeType.PINE_CONES,
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FarmingCropType.PINE_TAR, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))), 5,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))
        ,new ArrayList<>(Arrays.asList("\\assets\\GameAssets\\Trees\\Pine_Stage_1.png",
        "\\assets\\GameAssets\\Trees\\Pine_Stage_2.png",
        "\\assets\\GameAssets\\Trees\\Pine_Stage_3.png",
        "\\assets\\GameAssets\\Trees\\Pine_Stage_4.png"))),

    MAHOGANY_TREE("Mahogany_Tree", ForagingTreeType.MAHOGANY_SEEDS,
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FoodType.SAP, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))), 1,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))
        ,new ArrayList<>(Arrays.asList("\\assets\\GameAssets\\Trees\\Pine_Stage_1.png",
        "\\assets\\GameAssets\\Trees\\Pine_Stage_2.png",
        "\\assets\\GameAssets\\Trees\\Pine_Stage_3.png",
        "\\assets\\GameAssets\\Trees\\Pine_Stage_4.png"))),

    MUSHROOM_TREE("Mushroom_Tree", ForagingTreeType.MUSHROOM_TREE_SEEDS,
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FoodType.COMMON_MUSHROOM, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))), 1,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))
        ,new ArrayList<>(Arrays.asList("\\assets\\GameAssets\\Trees\\MushroomTree_Stage_1.png",
        "\\assets\\GameAssets\\Trees\\MushroomTree_Stage_2.png",
        "\\assets\\GameAssets\\Trees\\MushroomTree_Stage_3.png",
        "\\assets\\GameAssets\\Trees\\MushroomTree_Stage_4.png",
        "\\assets\\GameAssets\\Trees\\MushroomTree_Stage_5.png"))),

    MYSTIC_TREE("Mystic_Tree", FarmingTreeSaplingType.MYSTIC_SAPLING,
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FoodType.MYSTIC_SYRUP, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))), 7,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))
        ,new ArrayList<>(Arrays.asList("\\assets\\GameAssets\\Trees\\Mystic_Tree_Stage_1.png",
        "\\assets\\GameAssets\\Trees\\Mystic_Tree_Stage_2.png",
        "\\assets\\GameAssets\\Trees\\Mystic_Tree_Stage_3.png",
        "\\assets\\GameAssets\\Trees\\Mystic_Tree_Stage_4.png",
        "\\assets\\GameAssets\\Trees\\Mystic_Tree_Stage_5.png")));


    private String name;
    private GoodType saplingType;
    private final ArrayList<Pair<GoodType, Integer>> products;
    private int fruitHarvestCycle;
    private ArrayList<Season> seasons;
    private ArrayList<String> imagesPaths;

    FarmingTreeType(String name, GoodType saplingType, ArrayList<Pair<GoodType, Integer>> products, int fruitHarvestCycle,
                    ArrayList<Season> seasons , ArrayList<String> imagesPaths) {
        this.name = name;
        this.saplingType = saplingType;
        this.products = products;
        this.fruitHarvestCycle = fruitHarvestCycle;
        this.seasons = seasons;
        this.imagesPaths = imagesPaths;
    }



    //Trees can't be eaten or Sold
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

    @Override
    public String imagePath() {
        return "";
    }

    public ArrayList<Pair<GoodType, Integer>> getProducts() {
        return products;
    }

    public GoodType getSaplingType() {
        return saplingType;
    }

    public int getFruitHarvestCycle() {
        return fruitHarvestCycle;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }
}
