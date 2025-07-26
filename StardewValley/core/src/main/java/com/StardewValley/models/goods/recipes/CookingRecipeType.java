package com.StardewValley.models.goods.recipes;

import com.StardewValley.models.Pair;
import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.goods.farmings.FarmingCropType;
import com.StardewValley.models.goods.fishs.FishType;
import com.StardewValley.models.goods.foods.FoodType;
import com.StardewValley.models.goods.foragings.ForagingCropType;
import com.StardewValley.models.goods.products.ProductType;
import com.StardewValley.models.interactions.Animals.AnimalProductsType;

import java.util.ArrayList;
import java.util.Arrays;


public enum CookingRecipeType implements GoodType {

    FRIED_EGG(new ArrayList<>(Arrays.asList(
        new Pair<>(AnimalProductsType.CHICKEN_EGG, 1)
    )), FoodType.FRIED_EGG,"GameAssets/Recipe/Fried_Egg.png"),

    BAKED_FISH(new ArrayList<>(Arrays.asList(
        new Pair<>(FishType.SARDINE, 1),
        new Pair<>(FishType.SALMON, 1),
        new Pair<>(FarmingCropType.WHEAT, 1)
    )), FoodType.BAKED_FISH,"GameAssets/Recipe/Baked_Fish.png"),

    SALAD(new ArrayList<>(Arrays.asList(
        new Pair<>(ForagingCropType.LEEK, 1),
        new Pair<>(ForagingCropType.DANDELION, 1)
    )), FoodType.SALAD,"GameAssets/Recipe/Salad.png"),

    OMELET(new ArrayList<>(Arrays.asList(
        new Pair<>(AnimalProductsType.CHICKEN_EGG, 1),
        new Pair<>(AnimalProductsType.COW_MILK, 1)
    )), FoodType.OMELET,"GameAssets/Recipe/Omelet.png"),

    PUMPKIN_PIE(new ArrayList<>(Arrays.asList(
        new Pair<>(FarmingCropType.PUMPKIN, 1),
        new Pair<>(FoodType.WHEAT_FLOUR, 1),
        new Pair<>(AnimalProductsType.COW_MILK, 1),
        new Pair<>(FoodType.SUGAR, 1)
    )), FoodType.PUMPKIN_PIE,"GameAssets/Recipe/Pumpkin_Pie.png"),

    SPAGHETTI(new ArrayList<>(Arrays.asList(
        new Pair<>(FoodType.WHEAT_FLOUR, 1),
        new Pair<>(FarmingCropType.TOMATO, 1)
    )), FoodType.SPAGHETTI,"GameAssets/Recipe/Spaghetti.png"),

    PIZZA(new ArrayList<>(Arrays.asList(
        new Pair<>(FoodType.WHEAT_FLOUR, 1),
        new Pair<>(FarmingCropType.TOMATO, 1),
        new Pair<>(FoodType.CHEESE, 1)
    )), FoodType.PIZZA,"GameAssets/Recipe/Pizza.png"),

    TORTILLA(new ArrayList<>(Arrays.asList(
        new Pair<>(FarmingCropType.CORN, 1)
    )), FoodType.TORTILLA,"GameAssets/Recipe/Tortilla.png"),

    MAKI_ROLL(new ArrayList<>(Arrays.asList(
        new Pair<>(FishType.ANONYMOUS, 1),
        new Pair<>(FoodType.RICE, 1),
        new Pair<>(ProductType.FIBER, 1)
    )), FoodType.MAKI_ROLL,"GameAssets/Recipe/Maki_Roll.png"),

    TRIPLE_SHOT_ESPRESSO(new ArrayList<>(Arrays.asList(
        new Pair<>(FoodType.COFFEE, 3)
    )), FoodType.TRIPLE_SHOT_ESPRESSO,"GameAssets/Recipe/Triple_Shot_Espresso.png"),

    COOKIE(new ArrayList<>(Arrays.asList(
        new Pair<>(FoodType.WHEAT_FLOUR, 1),
        new Pair<>(FoodType.SUGAR, 1),
        new Pair<>(AnimalProductsType.CHICKEN_EGG, 1)
    )), FoodType.COOKIE,"GameAssets/Recipe/Cookie.png"),

    HASH_BROWNS(new ArrayList<>(Arrays.asList(
        new Pair<>(FarmingCropType.POTATO, 1),
        new Pair<>(ProductType.OIL, 1)
    )), FoodType.HASH_BROWNS,"GameAssets/Recipe/Hash_Browns.png"),

    PANCAKES(new ArrayList<>(Arrays.asList(
        new Pair<>(FoodType.WHEAT_FLOUR, 1),
        new Pair<>(AnimalProductsType.CHICKEN_EGG, 1)
    )), FoodType.PANCAKES,"GameAssets/Recipe/Pancakes.png"),

    FRUIT_SALAD(new ArrayList<>(Arrays.asList(
        new Pair<>(FarmingCropType.BLUEBERRY, 1),
        new Pair<>(FarmingCropType.MELON, 1),
        new Pair<>(FoodType.APRICOT, 1)
    )), FoodType.FRUIT_SALAD,"GameAssets/Recipe/Fruit_Salad.png"),

    RED_PLATE(new ArrayList<>(Arrays.asList(
        new Pair<>(FarmingCropType.RED_CABBAGE, 1),
        new Pair<>(FarmingCropType.RADISH, 1)
    )), FoodType.RED_PLATE,"GameAssets/Recipe/Red_Plate.png"),

    BREAD(new ArrayList<>(Arrays.asList(
        new Pair<>(FoodType.WHEAT_FLOUR, 1)
    )), FoodType.BREAD,"GameAssets/Recipe/Bread.png"),

    SALMON_DINNER(new ArrayList<>(Arrays.asList(
        new Pair<>(FishType.SALMON, 1),
        new Pair<>(FarmingCropType.AMARANTH, 1),
        new Pair<>(FarmingCropType.KALE, 1)
    )), FoodType.SALMON_DINNER,"GameAssets/Recipe/Salmon_Dinner.png"),

    VEGETABLE_MEDLEY(new ArrayList<>(Arrays.asList(
        new Pair<>(FarmingCropType.TOMATO, 1),
        new Pair<>(FarmingCropType.BEET, 1)
    )), FoodType.VEGETABLE_MEDLEY,"GameAssets/Recipe/Vegetable_Medley.png"),

    FARMERS_LUNCH(new ArrayList<>(Arrays.asList(
        new Pair<>(FoodType.OMELET, 1),
        new Pair<>(FarmingCropType.PARSNIP, 1)
    )), FoodType.FARMERS_LUNCH,"GameAssets/Recipe/Farmers_Lunch.png"),

    SURVIVAL_BURGER(new ArrayList<>(Arrays.asList(
        new Pair<>(FoodType.BREAD, 1),
        new Pair<>(FarmingCropType.CARROT, 1),
        new Pair<>(FarmingCropType.EGGPLANT, 1)
    )), FoodType.SURVIVAL_BURGER,"GameAssets/Recipe/Survival_Burger.png"),

    DISH_O_THE_SEA(new ArrayList<>(Arrays.asList(
        new Pair<>(FishType.SARDINE, 2),
        new Pair<>(FoodType.HASH_BROWNS, 1)
    )), FoodType.DISH_O_THE_SEA,"GameAssets/Recipe/Dish_O_The_Sea.png"),

    SEAFOAM_PUDDING(new ArrayList<>(Arrays.asList(
        new Pair<>(FishType.FLOUNDER, 1),
        new Pair<>(FishType.MIDNIGHT_CARP, 1)
    )), FoodType.SEAFORM_PUDDING,"GameAssets/Recipe/Seafoam_Pudding.png"),

    MINERS_TREAT(new ArrayList<>(Arrays.asList(
        new Pair<>(FarmingCropType.CARROT, 2),
        new Pair<>(FoodType.SUGAR, 1),
        new Pair<>(AnimalProductsType.COW_MILK, 1)
    )), FoodType.MINERS_TREAT,"GameAssets/Recipe/Miners_Treat.png");

    private final ArrayList<Pair<GoodType, Integer>> ingredients;
    private final GoodType goodType;
    private final String imagePath;

    CookingRecipeType(ArrayList<Pair<GoodType, Integer>> ingredients, GoodType goodType, String imagePath) {
        this.ingredients = ingredients;
        this.goodType = goodType;
        this.imagePath = imagePath;
    }

    public ArrayList<Pair<GoodType, Integer>> getIngredients() {
        return ingredients;
    }

    public GoodType getGoodType() {
        return goodType;
    }

    public static CookingRecipeType getRecipeForFood(FoodType foodType) {
        for (CookingRecipeType recipe : values()) {
            if (recipe.getGoodType() instanceof FoodType && recipe.getGoodType() == foodType) {
                return recipe;
            }
        }
        return null;
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
        return goodType.getName();
    }

    @Override
    public String imagePath() {
        return imagePath;
    }
}
