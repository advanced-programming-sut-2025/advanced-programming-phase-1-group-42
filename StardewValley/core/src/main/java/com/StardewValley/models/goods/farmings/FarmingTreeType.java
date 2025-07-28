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
    APRICOT_TREE("Apricot_Tree",
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FoodType.APRICOT, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))),
            1, new ArrayList<>(Arrays.asList(Season.SPRING))
    ,new ArrayList<>(Arrays.asList("GameAssets/Trees/Apricot_Stage_1.png",
        "GameAssets/Trees/Apricot_Stage_2.png",
        "GameAssets/Trees/Apricot_Stage_3.png",
        "GameAssets/Trees/Apricot_Stage_4.png"))),

    CHERRY_TREE("Cherry_Tree",
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FoodType.CHERRY, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))),
            1, new ArrayList<>(Arrays.asList(Season.SPRING))
        ,new ArrayList<>(Arrays.asList("GameAssets/Trees/Cherry_Stage_1.png",
        "GameAssets/Trees/Cherry_Stage_2.png",
        "GameAssets/Trees/Cherry_Stage_3.png",
        "GameAssets/Trees/Cherry_Stage_4.png"))),

    BANANA_TREE("Banana_Tree",
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FoodType.BANANA, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))),
            1, new ArrayList<>(Arrays.asList(Season.SUMMER))
        ,new ArrayList<>(Arrays.asList("GameAssets/Trees/Banana_Stage_1.png",
        "GameAssets/Trees/Banana_Stage_2.png",
        "GameAssets/Trees/Banana_Stage_3.png",
        "GameAssets/Trees/Banana_Stage_4.png"))),

    MANGO_TREE("Mango_Tree",
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FoodType.MANGO, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))),
            1, new ArrayList<>(Arrays.asList(Season.SUMMER))
        ,new ArrayList<>(Arrays.asList("GameAssets/Trees/Mango_Stage_1.png",
        "GameAssets/Trees/Mango_Stage_2.png",
        "GameAssets/Trees/Mango_Stage_3.png",
        "GameAssets/Trees/Mango_Stage_4.png",
        "GameAssets/Trees/Mango_Stage_5_Fruit.png"))),

    ORANGE_TREE("Orange_Tree",
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FoodType.ORANGE, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))),
            1, new ArrayList<>(Arrays.asList(Season.SUMMER))
        ,new ArrayList<>(Arrays.asList("GameAssets/Trees/Orange_Stage_1.png",
        "GameAssets/Trees/Orange_Stage_2.png",
        "GameAssets/Trees/Orange_Stage_3.png",
        "GameAssets/Trees/Orange_Stage_4.png",
        "GameAssets/Trees/Orange_Stage_5_Fruit.png"))),

    PEACH_TREE("Peach_Tree",
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FoodType.PEACH, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))),
            1, new ArrayList<>(Arrays.asList(Season.SUMMER))
        ,new ArrayList<>(Arrays.asList("GameAssets/Trees/Peach_Stage_1.png",
        "GameAssets/Trees/Peach_Stage_2.png",
        "GameAssets/Trees/Peach_Stage_3.png",
        "GameAssets/Trees/Peach_Stage_4.png",
        "GameAssets/Trees/Peach_Stage_5_Fruit.png"))),

    APPLE_TREE("Apple_Tree",
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FoodType.APPLE, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))),
            1, new ArrayList<>(Arrays.asList(Season.FALL))
        ,new ArrayList<>(Arrays.asList("GameAssets/Trees/Apple_Stage_1.png",
        "GameAssets/Trees/Apple_Stage_2.png",
        "GameAssets/Trees/Apple_Stage_3.png",
        "GameAssets/Trees/Apple_Stage_4.png",
        "GameAssets/Trees/Apple_Stage_5_Fruit.png"))),

    POMEGRANATE_TREE("Pomegranate_Tree",
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FoodType.POMEGRANATE, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))),
            1, new ArrayList<>(Arrays.asList(Season.FALL))
        ,new ArrayList<>(Arrays.asList("GameAssets/Trees/Pomegranate_Stage_1.png",
        "GameAssets/Trees/Pomegranate_Stage_2.png",
        "GameAssets/Trees/Pomegranate_Stage_3.png",
        "GameAssets/Trees/Pomegranate_Stage_4.png",
        "GameAssets/Trees/Pomegranate_Stage_5_Fruit.png"))),

    OAK_TREE("Oak_Tree",
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FarmingCropType.OAK_RESIN, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))), 7,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))
        ,new ArrayList<>(Arrays.asList("GameAssets/Trees/Oak_Stage_1.png",
        "GameAssets/Trees/Oak_Stage_2.png",
        "GameAssets/Trees/Oak_Stage_3.png",
        "GameAssets/Trees/Oak_Stage_4.png",
        "GameAssets/Trees/Oak_Stage_5.png"))),

    MAPLE_TREE("Maple_Tree",
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FarmingCropType.MAPLE_SYRUP, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))), 9,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))
        ,new ArrayList<>(Arrays.asList("GameAssets/Trees/Maple_Stage_1.png",
        "GameAssets/Trees/Maple_Stage_2.png",
        "GameAssets/Trees/Maple_Stage_3.png",
        "GameAssets/Trees/Maple_Stage_4.png",
        "GameAssets/Trees/Maple_Stage_5.png"))),

    PINE_TREE("Pine_Tree",
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FarmingCropType.PINE_TAR, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))), 5,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))
        ,new ArrayList<>(Arrays.asList("GameAssets/Trees/Pine_Stage_1.png",
        "GameAssets/Trees/Pine_Stage_2.png",
        "GameAssets/Trees/Pine_Stage_3.png",
        "GameAssets/Trees/Pine_Stage_4.png",
        "GameAssets/Trees/Pine_Stage_5.png"))),

    MAHOGANY_TREE("Mahogany_Tree",
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FoodType.SAP, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))), 1,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))
        ,new ArrayList<>(Arrays.asList("GameAssets/Trees/Pine_Stage_1.png",
        "GameAssets/Trees/Pine_Stage_2.png",
        "GameAssets/Trees/Pine_Stage_3.png",
        "GameAssets/Trees/Pine_Stage_4.png",
        "GameAssets/Trees/Pine_Stage_5.png"))),

    MUSHROOM_TREE("Mushroom_Tree",
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FoodType.COMMON_MUSHROOM, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))), 1,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))
        ,new ArrayList<>(Arrays.asList("GameAssets/Trees/MushroomTree_Stage_1.png",
        "GameAssets/Trees/MushroomTree_Stage_2.png",
        "GameAssets/Trees/MushroomTree_Stage_3.png",
        "GameAssets/Trees/MushroomTree_Stage_4.png",
        "GameAssets/Trees/MushroomTree_Stage_5.png"))),

    MYSTIC_TREE("Mystic_Tree",
            new ArrayList<>(Arrays.asList(
                    new Pair<>(FoodType.MYSTIC_SYRUP, 2),
                    new Pair<>(ProductType.WOOD, 16),
                    new Pair<>(FoodType.SAP, 2))), 7,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))
        ,new ArrayList<>(Arrays.asList("GameAssets/Trees/Mystic_Tree_Stage_1.png",
        "GameAssets/Trees/Mystic_Tree_Stage_2.png",
        "GameAssets/Trees/Mystic_Tree_Stage_3.png",
        "GameAssets/Trees/Mystic_Tree_Stage_4.png",
        "GameAssets/Trees/Mystic_Tree_Stage_5.png")));


    private String name;
    private final ArrayList<Pair<GoodType, Integer>> products;
    private int fruitHarvestCycle;
    private ArrayList<Season> seasons;
    private ArrayList<String> imagesPaths;

    FarmingTreeType(String name, ArrayList<Pair<GoodType, Integer>> products, int fruitHarvestCycle,
                    ArrayList<Season> seasons , ArrayList<String> imagesPaths) {
        this.name = name;
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
        return imagesPaths.getLast();
    }

    public ArrayList<Pair<GoodType, Integer>> getProducts() {
        return products;
    }

    public int getFruitHarvestCycle() {
        return fruitHarvestCycle;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }
}
