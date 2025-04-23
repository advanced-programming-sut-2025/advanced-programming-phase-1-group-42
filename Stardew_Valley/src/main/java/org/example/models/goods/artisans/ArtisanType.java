package org.example.models.goods.artisans;

import org.example.models.goods.Good;
import org.example.models.goods.GoodType;
import org.example.models.goods.recipes.CraftingRecipeType;

import java.util.ArrayList;

public enum ArtisanType implements GoodType {

    HONEY("Honey","It's a sweet syrup produced by bees.", 75, 4,0,
            {},350,CraftingRecipeType.BEE_HOUSE),
    Cheese_MILK("Cheese","It's your basic cheese.",100,0,3,
            {},230,CraftingRecipeType.CHEESE_PRESS),
    Cheese_LARGE_MILK("Cheese","It's your basic cheese.",100,0,3,
            {},345,CraftingRecipeType.CHEESE_PRESS),
    Goat_Cheese("Cheese","Soft cheese made from goat's milk.",100,0,3,
            {},230,CraftingRecipeType.CHEESE_PRESS),
    



    private final String name;
    private final String description;
    private final int energy;
    private final int processingHour;
    private final int processingDay;
    private final ArrayList<GoodType> ingredients;
    private final int sellPrice;
    private final CraftingRecipeType craftingRecipeType;

    ArtisanType(String name,
                String description,
                int energy,
                int processingDay,
                int processingHour,
                ArrayList<GoodType> ingredients,
                int sellPrice,
                CraftingRecipeType craftingRecipeType) {

        this.name = name;
        this.energy = energy;
        this.processingHour = processingHour;
        this.processingDay = processingDay;
        this.description = description;
        this.ingredients = ingredients;
        this.sellPrice = sellPrice;
        this.craftingRecipeType = craftingRecipeType;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public String getIngredients() {
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
}
