package org.example.models.goods.foods;

import org.example.models.goods.Good;

public class Food extends Good {

<<<<<<< Updated upstream
<<<<<<< Updated upstream
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
=======

>>>>>>> Stashed changes
=======

>>>>>>> Stashed changes
}
