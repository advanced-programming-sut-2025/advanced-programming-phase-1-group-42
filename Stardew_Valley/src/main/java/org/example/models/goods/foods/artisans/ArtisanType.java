package org.example.models.goods.foods.artisans;

import org.example.models.goods.GoodType;
import org.example.models.goods.recipes.CraftingRecipeType;

import java.util.ArrayList;
import java.util.HashMap;

public enum ArtisanType implements GoodType {

    HONEY("Honey", "It's a sweet syrup produced by bees.", 75, 4, 0,
            new ArrayList<>(), -1, 350, CraftingRecipeType.BEE_HOUSE),
    Cheese_MILK("Cheese", "It's your basic cheese.", 100, 0, 3,
            new ArrayList<>(), 230, CraftingRecipeType.CHEESE_PRESS),
    Cheese_LARGE_MILK("Cheese", "It's your basic cheese.", 100, 0, 3,
            new ArrayList<>(), 345, CraftingRecipeType.CHEESE_PRESS),
    Goat_Cheese("Cheese", "Soft cheese made from goat's milk.", 100, 0, 3,
            new ArrayList<>(), 230, CraftingRecipeType.CHEESE_PRESS),
    Beer("Beer", "Drink in moderation.", 50, 1, 0,
            new ArrayList<>(), 200, CraftingRecipeType.KEG),
    Vinegar("Vinegar", "An aged fermented liquid used in many cooking recipes.", 13,
            0, 10, new ArrayList<>(), 100, CraftingRecipeType.KEG),
    Coffee("Coffee", "It smells delicious. This is sure to give you a boost.", 75,
            0, 2, new ArrayList<>(), 150, CraftingRecipeType.KEG),
    //??
    Juice("Juice", "A sweet, nutritious beverage.", , 4, 0, new ArrayList<>(),
            ),
    Mead("Mead", "A fermented beverage made from honey. Drink in moderation.", 100,
            0, 10, new ArrayList<>(), 300, CraftingRecipeType.KEG),
    Pale_Ale("Pale_Ale", "Drink in moderation.", 50, 3, 0,
            new ArrayList<>(), 300, CraftingRecipeType.KEG),
    //?
    Wine("Wine", "Drink in moderation.", , 7, 0, new ArrayList<>(), ),
    Dried_Mashrooms("Dried_Mashrooms", "A package of gourmet mushrooms.", 50, 1,
            0, new ArrayList<>(), )


    private final String name;
    private final String description;
    private final int energy;
    private final int processingHour;
    private final int processingDay;
    private final ArrayList<HashMap<GoodType, Integer>> ingredients;
    private final int ratio;
    private final int base;
    private final CraftingRecipeType craftingRecipeType;

    ArtisanType(String name,
                String description,
                int energy,
                int processingDay,
                int processingHour,
                ArrayList<HashMap<GoodType, Integer>> ingredients,
                int ratio, int base
                CraftingRecipeType craftingRecipeType) {

        this.name = name;
        this.energy = energy;
        this.processingHour = processingHour;
        this.processingDay = processingDay;
        this.description = description;
        this.ingredients = ingredients;
        this.ratio = ratio;
        this.base = base;
        this.craftingRecipeType = craftingRecipeType;
    }

    public int getSellPrice(GoodType ingredient) {
        if (ratio == -1) {
            return ratio * ingredient.getSellPrice() + base;
        }
        else {
            return base;
        }
    }



    public ArrayList<HashMap<GoodType, Integer>> getIngredients() {
        return ingredients;
    }

    public String getDescription() {
        return description;
    }

    public int getProcessingDay() {
        return processingDay;
    }

    public int getProcessingHour() {
        return processingHour;
    }

    public int getEnergy() {
        return energy;
    }

    public String getName() {
        return name;
    }

    public static class calculations {

        public static int calculatePrice(String artisanType) {

            switch (artisanType) {
                case "Honey":
                    return 150;
                case
            }

        }
        public static int getJuicePrice (GoodType goodType){
            return goodType.getSellPrice() * 2;
        }

        public static double getJuiceEnergy (GoodType goodType){
            return goodType.getEnergy() * 2.25;
        }

    }

    public static


    }


