package org.example.models.goods.foods;

import org.example.models.goods.Good;
import org.example.models.goods.GoodType;

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
