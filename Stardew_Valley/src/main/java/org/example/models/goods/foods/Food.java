package org.example.models.goods.foods;

import org.example.models.goods.Good;

public class Food extends Good {

    private int energy;

    public int getEnergy() {
        return energy;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSellPrice() {
        return price;
    }
}
