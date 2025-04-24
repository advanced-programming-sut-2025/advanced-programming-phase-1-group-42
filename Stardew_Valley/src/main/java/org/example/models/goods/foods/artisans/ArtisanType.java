package org.example.models.goods.foods.artisans;

import org.example.models.goods.Good;
import org.example.models.goods.GoodType;
import org.example.models.goods.craftings.CraftingType;
import org.example.models.goods.recipes.CraftingRecipeType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public enum ArtisanType implements GoodType {

    HONEY("Honey", "It's a sweet syrup produced by bees.", 75, -1,4,
            0, new ArrayList<>(), -1, 350),
    Cheese_MILK("Cheese", "It's your basic cheese.", 100, -1 , 0, 3,
            new ArrayList<>(), -1 ,230),
    Cheese_LARGE_MILK("Cheese", "It's your basic cheese.", 100, -1, 0, 3,
            new ArrayList<>(), -1, 345),
    Goat_Cheese_MILK("Goat_Cheese", "Soft cheese made from goat's milk.", 100,-1, 0, 3,
            new ArrayList<>(), -1, 400 ),
    Goat_Cheese_LARGE_MILK("Goat_Cheese", "Soft cheese made from goat's milk.", 100,-1, 0, 3,
            new ArrayList<>(), -1, 600),
    Beer("Beer", "Drink in moderation.", 50, -1, 1, 0,
            new ArrayList<>(), -1, 200),
    Vinegar("Vinegar", "An aged fermented liquid used in many cooking recipes.", 13,
            -1,0, 10, new ArrayList<>(), -1,100),
    Coffee("Coffee", "It smells delicious. This is sure to give you a boost.", 75,
            -1,0, 2, new ArrayList<>(), -1, 150),
    Juice("Juice", "A sweet, nutritious beverage.", -1 ,2, 4, 0,
            new ArrayList<>(), 2.25 , -1),
    Mead("Mead", "A fermented beverage made from honey. Drink in moderation.", 100,
            -1,0, 10, new ArrayList<>(), -1, 300),
    Pale_Ale("Pale_Ale", "Drink in moderation.", 50, 3, 0,
            -1,new ArrayList<>(), -1, 300),
    Wine("Wine", "Drink in moderation.", -1 , 1.75,7, 0,
            new ArrayList<>(), 1.75 , -1),
    Dried_Mushrooms("Dried_Mushrooms", "A package of gourmet mushrooms.", 50, 1,
            0, new ArrayList<>(), );


    private final String name;
    private final String description;
    private final int energy;
    private final double energyRatio;
    private final int processingHour;
    private final int processingDay;
    private final ArrayList<HashMap<GoodType, Integer>> ingredients;
    private final double ratio;
    private final int base;
    private final CraftingRecipeType craftingRecipeType;

    ArtisanType(String name,
                String description,
                int energy,
                double energyRatio,
                int processingDay,
                int processingHour,
                ArrayList<HashMap<GoodType, Integer>> ingredients,
                double ratio, int base) {

        this.name = name;
        this.energy = energy;
        this.energyRatio = energyRatio;
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

    @Override
    public int getSellPrice() {
        return 0;
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


