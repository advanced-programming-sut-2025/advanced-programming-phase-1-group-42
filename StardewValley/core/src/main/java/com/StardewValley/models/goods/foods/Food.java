package com.StardewValley.models.goods.foods;

import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.GoodType;

public class Food extends Good {

    private FoodType type;
    private int energy;

    public Food(FoodType type) {
        this.type = type;
    }

    public int getEnergy() {
        return type.getEnergy();
    }

    @Override
    public String getName() {
        return type.getName();
    }

    @Override
    public int getSellPrice() {
        return type.getSellPrice();
    }

    @Override
    public GoodType getType() {
        return type;
    }

}
