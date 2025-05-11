package org.example.models.goods.fishs;

import org.example.models.goods.Good;
import org.example.models.goods.GoodLevel;
import org.example.models.goods.GoodType;
import org.example.models.goods.foods.FoodType;

public class Fish extends Good {
    private FishType type;
    private GoodLevel level;

    public Fish(FishType type) {
        this.type = type;
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

    public GoodLevel getLevel() {
        return level;
    }

    public void setLevel(GoodLevel level) {
        this.level = level;
    }
}
