package org.example.models.goods.recipes;

import org.example.models.goods.GoodType;
import org.example.models.goods.farmings.FarmingGoodType;
import org.example.models.goods.fishs.FishType;
import org.example.models.goods.foods.FoodType;
import org.example.models.goods.foragings.CropType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public enum CookingRecipeType implements GoodType {

    FRIED_EGG(new ArrayList<>(Arrays.asList(
            new HashMap<FoodType, Integer>() {{
                put(FoodType.EGG, 1);
            }}
    )), FoodType.FRIED_EGG),

    BAKED_FISH(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(FishType.SARDINE, 1);
                put(FishType.SALMON, 1);
                put(FarmingGoodType.WHEAT, 1);
            }}
    )), FoodType.BAKED_FISH),

    SALAD(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(CropType.LEEK, 1);
                put(CropType.DANDELION, 1);
            }}
    )), FoodType.SALAD),

    OMELET(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(GoodType.EGG, 1);
                put(GoodType.MILK, 1);
            }}
    )), FoodType.OMELET),

    PUMPKIN_PIE(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(FarmingGoodType.PUMPKIN, 1);
                put(GoodType.WHEAT_FLOUR, 1);
                put(GoodType.MILK, 1);
                put(GoodType.SUGAR, 1);
            }}
    )), FoodType.PUMPKIN_PIE),

    SPAGHETTI(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(GoodType.WHEAT_FLOUR, 1);
                put(FarmingGoodType.TOMATO, 1);
            }}
    )), FoodType.SPAGHETTI),

    PIZZA(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(GoodType.WHEAT_FLOUR, 1);
                put(FarmingGoodType.TOMATO, 1);
                put(GoodType.CHEESE, 1);
            }}
    )), FoodType.PIZZA),

    TORTILLA(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(FarmingGoodType.CORN, 1);
            }}
    )), FoodType.TORTILLA),

    MAKI_ROLL(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(GoodType.ANY_FISH, 1);
                put(GoodType.RICE, 1);
                put(GoodType.FIBER, 1);
            }}
    )), FoodType.MAKI_ROLL),

    TRIPLE_SHOT_ESPRESSO(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(FoodType.COFFEE, 3);
            }}
    )), FoodType.TRIPLE_SHOT_ESPRESSO),

    COOKIE(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(GoodType.WHEAT_FLOUR, 1);
                put(GoodType.SUGAR, 1);
                put(GoodType.EGG, 1);
            }}
    )), FoodType.COOKIE),

    HASH_BROWNS(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(FarmingGoodType.POTATO, 1);
                put(GoodType.OIL, 1);
            }}
    )), FoodType.HASH_BROWNS),

    PANCAKES(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(GoodType.WHEAT_FLOUR, 1);
                put(GoodType.EGG, 1);
            }}
    )), FoodType.PANCAKES),

    FRUIT_SALAD(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(FarmingGoodType.BLUEBERRY, 1);
                put(FarmingGoodType.MELON, 1);
                put(FoodType.APRICOT, 1);
            }}
    )), FoodType.FRUIT_SALAD),

    RED_PLATE(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(FarmingGoodType.RED_CABBAGE, 1);
                put(FarmingGoodType.RADISH, 1);
            }}
    )), FoodType.RED_PLATE),

    BREAD(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(GoodType.WHEAT_FLOUR, 1);
            }}
    )), FoodType.BREAD),

    SALMON_DINNER(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(FishType.SALMON, 1);
                put(FarmingGoodType.AMARANTH, 1);
                put(FarmingGoodType.KALE, 1);
            }}
    )), FoodType.SALMON_DINNER),

    VEGETABLE_MEDLEY(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(FarmingGoodType.TOMATO, 1);
                put(FarmingGoodType.BEET, 1);
            }}
    )), FoodType.VEGETABLE_MEDLEY),

    FARMERS_LUNCH(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(GoodType.OMELET, 1);
                put(FarmingGoodType.PARSNIP, 1);
            }}
    )), FoodType.FARMERS_LUNCH),

    SURVIVAL_BURGER(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(FoodType.BREAD, 1);
                put(FarmingGoodType.CARROT, 1);
                put(FarmingGoodType.EGGPLANT, 1);
            }}
    )), FoodType.SURVIVAL_BURGER),

    DISH_O_THE_SEA(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(FishType.SARDINE, 2);
                put(GoodType.HASH_BROWNS, 1);
            }}
    )), FoodType.DISH_O_THE_SEA),

    SEAFOAM_PUDDING(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(FishType.FLOUNDER, 1);
                put(FishType.MIDNIGHT_CARP, 1);
            }}
    )), FoodType.SEAFORM_PUDDING),

    MINERS_TREAT(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(FarmingGoodType.CARROT, 2);
                put(GoodType.SUGAR, 1);
                put(GoodType.MILK, 1);
            }}
    )), FoodType.MINERS_TREAT);

    private final ArrayList<HashMap<GoodType, Integer>> ingredients;
    private final GoodType goodType;

    CookingRecipeType(ArrayList<HashMap<GoodType, Integer>> ingredients, GoodType goodType) {
        this.ingredients = ingredients;
        this.goodType = goodType;
    }

    public ArrayList<HashMap<GoodType, Integer>> getIngredients() {
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
}
