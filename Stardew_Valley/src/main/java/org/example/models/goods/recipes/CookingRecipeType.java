package org.example.models.goods.recipes;

import org.example.models.goods.GoodType;
import org.example.models.goods.farmings.FarmingCropType;
import org.example.models.goods.fishs.FishType;
import org.example.models.goods.foods.FoodType;
import org.example.models.goods.foragings.ForagingCropType;
import org.example.models.goods.product.ProductType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public enum CookingRecipeType implements GoodType {

    FRIED_EGG(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(FoodType.EGG, 1);
            }}
    )), FoodType.FRIED_EGG),

    BAKED_FISH(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(FishType.SARDINE, 1);
                put(FishType.SALMON, 1);
                put(FarmingCropType.WHEAT, 1);
            }}
    )), FoodType.BAKED_FISH),

    SALAD(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(ForagingCropType.LEEK, 1);
                put(ForagingCropType.DANDELION, 1);
            }}
    )), FoodType.SALAD),

    OMELET(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(FoodType.EGG, 1);
                put(FoodType.MILK, 1);
            }}
    )), FoodType.OMELET),

    PUMPKIN_PIE(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(FarmingCropType.PUMPKIN, 1);
                put(FoodType.WHEAT_FLOUR, 1);
                put(FoodType.MILK, 1);
                put(FoodType.SUGAR, 1);
            }}
    )), FoodType.PUMPKIN_PIE),

    SPAGHETTI(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(FoodType.WHEAT_FLOUR, 1);
                put(FarmingCropType.TOMATO, 1);
            }}
    )), FoodType.SPAGHETTI),

    PIZZA(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(FoodType.WHEAT_FLOUR, 1);
                put(FarmingCropType.TOMATO, 1);
                put(FoodType.CHEESE, 1);
            }}
    )), FoodType.PIZZA),

    TORTILLA(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(FarmingCropType.CORN, 1);
            }}
    )), FoodType.TORTILLA),

    MAKI_ROLL(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(FishType.ANONYMOUS, 1);
                put(FoodType.RICE, 1);
                put(ProductType.FIBER, 1);
            }}
    )), FoodType.MAKI_ROLL),

    TRIPLE_SHOT_ESPRESSO(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(FoodType.COFFEE, 3);
            }}
    )), FoodType.TRIPLE_SHOT_ESPRESSO),

    COOKIE(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(FoodType.WHEAT_FLOUR, 1);
                put(FoodType.SUGAR, 1);
                put(FoodType.EGG, 1);
            }}
    )), FoodType.COOKIE),

    HASH_BROWNS(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(FarmingCropType.POTATO, 1);
                put(ProductType.OIL, 1);
            }}
    )), FoodType.HASH_BROWNS),

    PANCAKES(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(FoodType.WHEAT_FLOUR, 1);
                put(FoodType.EGG, 1);
            }}
    )), FoodType.PANCAKES),

    FRUIT_SALAD(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(FarmingCropType.BLUEBERRY, 1);
                put(FarmingCropType.MELON, 1);
                put(FoodType.APRICOT, 1);
            }}
    )), FoodType.FRUIT_SALAD),

    RED_PLATE(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(FarmingCropType.RED_CABBAGE, 1);
                put(FarmingCropType.RADISH, 1);
            }}
    )), FoodType.RED_PLATE),

    BREAD(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(FoodType.WHEAT_FLOUR, 1);
            }}
    )), FoodType.BREAD),

    SALMON_DINNER(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(FishType.SALMON, 1);
                put(FarmingCropType.AMARANTH, 1);
                put(FarmingCropType.KALE, 1);
            }}
    )), FoodType.SALMON_DINNER),

    VEGETABLE_MEDLEY(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(FarmingCropType.TOMATO, 1);
                put(FarmingCropType.BEET, 1);
            }}
    )), FoodType.VEGETABLE_MEDLEY),

    FARMERS_LUNCH(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(FoodType.OMELET, 1);
                put(FarmingCropType.PARSNIP, 1);
            }}
    )), FoodType.FARMERS_LUNCH),

    SURVIVAL_BURGER(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(FoodType.BREAD, 1);
                put(FarmingCropType.CARROT, 1);
                put(FarmingCropType.EGGPLANT, 1);
            }}
    )), FoodType.SURVIVAL_BURGER),

    DISH_O_THE_SEA(new ArrayList<>(Arrays.asList(
            new HashMap<GoodType, Integer>() {{
                put(FishType.SARDINE, 2);
                put(FoodType.HASH_BROWNS, 1);
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
                put(FarmingCropType.CARROT, 2);
                put(FoodType.SUGAR, 1);
                put(FoodType.MILK, 1);
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

    @Override
    public String getName() {
        return "";
    }


}
