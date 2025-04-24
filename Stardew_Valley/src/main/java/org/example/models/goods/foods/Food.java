package org.example.models.goods.foods;

import org.example.models.goods.Good;
import org.example.models.goods.GoodType;

public class Food extends Good {

    FoodType foodType;
    private int energy;

    public Food(FoodType foodType) {
        this.foodType = foodType;
    }

    public int getEnergy() {
        return foodType.getEnergy();
    }

    @Override
    public String getName() {
        return foodType.getName();
    }

    @Override
    public int getSellPrice() {
        return foodType.getSellPrice();
    }

    @Override
    public GoodType getType() {
        return foodType;
    }


}
