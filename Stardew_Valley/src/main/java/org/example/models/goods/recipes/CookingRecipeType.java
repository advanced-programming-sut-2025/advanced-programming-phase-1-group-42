package org.example.models.goods.recipes;

import org.example.models.Pair;
import org.example.models.goods.foods.Food;
import org.example.models.goods.foods.FoodType;

import java.util.ArrayList;
import java.util.Arrays;

import static javax.swing.UIManager.put;


public enum CookingRecipeType {

    FRIED_EGG(new ArrayList<>(Arrays.asList(
            new Pair<>(FoodType.Egg),
            new Pair<>()
    )), FoodType.FRIED_EGG),

    BAKED_FISH(new ArrayList<>(Arrays.asList(
            new Pair<>(FoodType.SARDINE, 1),
            new Pair<>(FoodType.SALMON, 1),
            new Pair<>(FoodType.WHEAT, 1)
    )), FoodType.BAKED_FISH),

    SALAD(new ArrayList<>(Arrays.asList(
            new Pair<>(FoodType.LEEK, 1),
            new Pair<>(FoodType.DANDELION, 1)
    )), FoodType.SALAD),

    OMELET(new ArrayList<>(Arrays.asList(
            new Pair<>(FoodType.EGG, 1),
            new Pair<>(FoodType.MILK, 1)
    )), FoodType.OMELET),

    PUMPKIN_PIE(new ArrayList<>(Arrays.asList(
            new Pair<>(FoodType.PUMPKIN, 1),
            new Pair<>(FoodType.WHEAT_FLOUR, 1),
            new Pair<>(FoodType.MILK, 1),
            new Pair<>(FoodType.SUGAR, 1)
    )), FoodType.PUMPKIN_PIE),

    SPAGHETTI(new ArrayList<>(Arrays.asList(
            new Pair<>(FoodType.WHEAT_FLOUR, 1),
            new Pair<>(FoodType.TOMATO, 1)
    )), FoodType.SPAGHETTI),

    PIZZA(new ArrayList<>(Arrays.asList(
            new Pair<>(FoodType.WHEAT_FLOUR, 1),
            new Pair<>(FoodType.TOMATO, 1),
            new Pair<>(FoodType.CHEESE, 1)
    )), FoodType.PIZZA),

    TORTILLA(new ArrayList<>(Arrays.asList(
            new Pair<>(FoodType.CORN, 1)
    )), FoodType.TORTILLA),

    MAKI_ROLL(new ArrayList<>(Arrays.asList(
            new Pair<>(FoodType.ANY_FISH, 1),
            new Pair<>(FoodType.RICE, 1),
            new Pair<>(FoodType.FIBER, 1)
    )), FoodType.MAKI_ROLL),

    TRIPLE_SHOT_ESPRESSO(new ArrayList<>(Arrays.asList(
            new Pair<>(FoodType.COFFEE, 3)
    )), FoodType.TRIPLE_SHOT_ESPRESSO),

    COOKIE(new ArrayList<>(Arrays.asList(
            new Pair<>(FoodType.WHEAT_FLOUR, 1),
            new Pair<>(FoodType.SUGAR, 1),
            new Pair<>(FoodType.EGG, 1)
    )), FoodType.COOKIE),

    HASH_BROWNS(new ArrayList<>(Arrays.asList(
            new Pair<>(FoodType.POTATO, 1),
            new Pair<>(FoodType.OIL, 1)
    )), FoodType.HASH_BROWNS),

    PANCAKES(new ArrayList<>(Arrays.asList(
            new Pair<>(FoodType.WHEAT_FLOUR, 1),
            new Pair<>(FoodType.EGG, 1)
    )), FoodType.PANCAKES),

    FRUIT_SALAD(new ArrayList<>(Arrays.asList(
            new Pair<>(FoodType.BLUEBERRY, 1),
            new Pair<>(FoodType.MELON, 1),
            new Pair<>(FoodType.APRICOT, 1)
    )), FoodType.FRUIT_SALAD),

    RED_PLATE(new ArrayList<>(Arrays.asList(
            new Pair<>(FoodType.RED_CABBAGE, 1),
            new Pair<>(FoodType.RADISH, 1)
    )), FoodType.RED_PLATE),

    BREAD(new ArrayList<>(Arrays.asList(
            new Pair<>(FoodType.WHEAT_FLOUR, 1)
    )), FoodType.BREAD),

    SALMON_DINNER(new ArrayList<>(Arrays.asList(
            new Pair<>(FoodType.SALMON, 1),
            new Pair<>(FoodType.AMARANTH, 1),
            new Pair<>(FoodType.KALE, 1)
    )), FoodType.SALMON_DINNER),

    VEGETABLE_MEDLEY(new ArrayList<>(Arrays.asList(
            new Pair<>(FoodType.TOMATO, 1),
            new Pair<>(FoodType.BEET, 1)
    )), FoodType.VEGETABLE_MEDLEY),

    FARMERS_LUNCH(new ArrayList<>(Arrays.asList(
            new Pair<>(FoodType.OMELET, 1),
            new Pair<>(FoodType.PARSNIP, 1)
    )), FoodType.FARMERS_LUNCH),

    SURVIVAL_BURGER(new ArrayList<>(Arrays.asList(
            new Pair<>(FoodType.BREAD, 1),
            new Pair<>(FoodType.CARROT, 1),
            new Pair<>(FoodType.EGGPLANT, 1)
    )), FoodType.SURVIVAL_BURGER),

    DISH_O_THE_SEA(new ArrayList<>(Arrays.asList(
            new Pair<>(FoodType.SARDINE, 2),
            new Pair<>(FoodType.HASH_BROWNS, 1)
    )), FoodType.DISH_O_THE_SEA),

    SEAFOAM_PUDDING(new ArrayList<>(Arrays.asList(
            new Pair<>(FoodType.FLOUNDER, 1),
            new Pair<>(FoodType.MIDNIGHT_CARP, 1)
    )), FoodType.SEAFORM_PUDDING),

    MINERS_TREAT(new ArrayList<>(Arrays.asList(
            new Pair<>(FoodType.CARROT, 2),
            new Pair<>(FoodType.SUGAR, 1),
            new Pair<>(FoodType.MILK, 1)
    )), FoodType.MINERS_TREAT);


    private final ArrayList<Pair<FoodType, Integer>> ingredients;
    private final FoodType foodType;

    CookingRecipeType(ArrayList<Pair<FoodType, Integer>> ingredients, FoodType foodType) {
        this.ingredients = ingredients;
        this.foodType = foodType;
    }

    public ArrayList<Pair<FoodType, Integer>> getIngredients() {
        return ingredients;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public String getName(){
        return foodType.getName()+" recipe";
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
