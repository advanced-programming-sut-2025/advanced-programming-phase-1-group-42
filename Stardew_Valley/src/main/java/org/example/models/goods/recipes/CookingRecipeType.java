package org.example.models.goods.recipes;

import org.example.models.goods.Good;
import org.example.models.goods.GoodType;
import org.example.models.goods.foods.Food;
import org.example.models.goods.foods.FoodType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static javax.swing.UIManager.put;


public enum CookingRecipeType implements GoodType {

    FRIED_EGG(new ArrayList<>(Arrays.asList(
            new HashMap<FoodType, Integer>() {{
                put(FoodType.EGG, 1);
            }}
    )), FoodType.FRIED_EGG),

    BAKED_FISH(new ArrayList<>(Arrays.asList(
            new HashMap<FoodType, Integer>() {{
                put(FoodType.SARDINE, 1);
                put(FoodType.SALMON, 1);
                put(FoodType.WHEAT, 1);
            }}
    )), FoodType.BAKED_FISH),

    SALAD(new ArrayList<>(Arrays.asList(
            new HashMap<FoodType, Integer>() {{
                put(FoodType.LEEK, 1);
                put(FoodType.DANDELION, 1);
            }}
    )), FoodType.SALAD),

    OMELET(new ArrayList<>(Arrays.asList(
            new HashMap<FoodType, Integer>() {{
                put(FoodType.EGG, 1);
                put(FoodType.MILK, 1);
            }}
    )), FoodType.OMELET),

    PUMPKIN_PIE(new ArrayList<>(Arrays.asList(
            new HashMap<FoodType, Integer>() {{
                put(FoodType.PUMPKIN, 1);
                put(FoodType.WHEAT_FLOUR, 1);
                put(FoodType.MILK, 1);
                put(FoodType.SUGAR, 1);
            }}
    )), FoodType.PUMPKIN_PIE),

    SPAGHETTI(new ArrayList<>(Arrays.asList(
            new HashMap<FoodType, Integer>() {{
                put(FoodType.WHEAT_FLOUR, 1);
                put(FoodType.TOMATO, 1);
            }}
    )), FoodType.SPAGHETTI),

    PIZZA(new ArrayList<>(Arrays.asList(
            new HashMap<FoodType, Integer>() {{
                put(FoodType.WHEAT_FLOUR, 1);
                put(FoodType.TOMATO, 1);
                put(FoodType.CHEESE, 1);
            }}
    )), FoodType.PIZZA),

    TORTILLA(new ArrayList<>(Arrays.asList(
            new HashMap<FoodType, Integer>() {{
                put(FoodType.CORN, 1);
            }}
    )), FoodType.TORTILLA),

    MAKI_ROLL(new ArrayList<>(Arrays.asList(
            new HashMap<FoodType, Integer>() {{
                put(FoodType.ANY_FISH, 1);
                put(FoodType.RICE, 1);
                put(FoodType.FIBER, 1);
            }}
    )), FoodType.MAKI_ROLL),

    TRIPLE_SHOT_ESPRESSO(new ArrayList<>(Arrays.asList(
            new HashMap<FoodType, Integer>() {{
                put(FoodType.COFFEE, 3);
            }}
    )), FoodType.TRIPLE_SHOT_ESPRESSO),

    COOKIE(new ArrayList<>(Arrays.asList(
            new HashMap<FoodType, Integer>() {{
                put(FoodType.WHEAT_FLOUR, 1);
                put(FoodType.SUGAR, 1);
                put(FoodType.EGG, 1);
            }}
    )), FoodType.COOKIE),

    HASH_BROWNS(new ArrayList<>(Arrays.asList(
            new HashMap<FoodType, Integer>() {{
                put(FoodType.POTATO, 1);
                put(FoodType.OIL, 1);
            }}
    )), FoodType.HASH_BROWNS),

    PANCAKES(new ArrayList<>(Arrays.asList(
            new HashMap<FoodType, Integer>() {{
                put(FoodType.WHEAT_FLOUR, 1);
                put(FoodType.EGG, 1);
            }}
    )), FoodType.PANCAKES),

    FRUIT_SALAD(new ArrayList<>(Arrays.asList(
            new HashMap<FoodType, Integer>() {{
                put(FoodType.BLUEBERRY, 1);
                put(FoodType.MELON, 1);
                put(FoodType.APRICOT, 1);
            }}
    )), FoodType.FRUIT_SALAD),

    RED_PLATE(new ArrayList<>(Arrays.asList(
            new HashMap<FoodType, Integer>() {{
                put(FoodType.RED_CABBAGE, 1);
                put(FoodType.RADISH, 1);
            }}
    )), FoodType.RED_PLATE),

    BREAD(new ArrayList<>(Arrays.asList(
            new HashMap<FoodType, Integer>() {{
                put(FoodType.WHEAT_FLOUR, 1);
            }}
    )), FoodType.BREAD),

    SALMON_DINNER(new ArrayList<>(Arrays.asList(
            new HashMap<FoodType, Integer>() {{
                put(FoodType.SALMON, 1);
                put(FoodType.AMARANTH, 1);
                put(FoodType.KALE, 1);
            }}
    )), FoodType.SALMON_DINNER),

    VEGETABLE_MEDLEY(new ArrayList<>(Arrays.asList(
            new HashMap<FoodType, Integer>() {{
                put(FoodType.TOMATO, 1);
                put(FoodType.BEET, 1);
            }}
    )), FoodType.VEGETABLE_MEDLEY),

    FARMERS_LUNCH(new ArrayList<>(Arrays.asList(
            new HashMap<FoodType, Integer>() {{
                put(FoodType.OMELET, 1);
                put(FoodType.PARSNIP, 1);
            }}
    )), FoodType.FARMERS_LUNCH),

    SURVIVAL_BURGER(new ArrayList<>(Arrays.asList(
            new HashMap<FoodType, Integer>() {{
                put(FoodType.BREAD, 1);
                put(FoodType.CARROT, 1);
                put(FoodType.EGGPLANT, 1);
            }}
    )), FoodType.SURVIVAL_BURGER),

    DISH_O_THE_SEA(new ArrayList<>(Arrays.asList(
            new HashMap<FoodType, Integer>() {{
                put(FoodType.SARDINE, 2);
                put(FoodType.HASH_BROWNS, 1);
            }}
    )), FoodType.DISH_O_THE_SEA),

    SEAFOAM_PUDDING(new ArrayList<>(Arrays.asList(
            new HashMap<FoodType, Integer>() {{
                put(FoodType.FLOUNDER, 1);
                put(FoodType.MIDNIGHT_CARP, 1);
            }}
    )), FoodType.SEAFORM_PUDDING),

    MINERS_TREAT(new ArrayList<>(Arrays.asList(
            new HashMap<FoodType, Integer>() {{
                put(FoodType.CARROT, 2);
                put(FoodType.SUGAR, 1);
                put(FoodType.MILK, 1);
            }}
    )), FoodType.MINERS_TREAT);

    private final ArrayList<HashMap<FoodType, Integer>> ingredients;
    private final FoodType foodType;

    CookingRecipeType(ArrayList<HashMap<FoodType, Integer>> ingredients, FoodType foodType) {
        this.ingredients = ingredients;
        this.foodType = foodType;
    }

    public ArrayList<HashMap<FoodType, Integer>> getIngredients() {
        return ingredients;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public static CookingRecipeType getRecipeForFood(FoodType foodType) {
        for (CookingRecipeType recipe : values()) {
            if (recipe.getFoodType() == foodType) {
                return recipe;
            }
        }
        return null;
    }

}
