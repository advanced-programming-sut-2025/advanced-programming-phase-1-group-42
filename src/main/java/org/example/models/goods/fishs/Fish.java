package org.example.models.goods.fishs;

import org.example.models.goods.Good;
import org.example.models.goods.GoodType;

public class Fish extends Good {
    private FishType type;
    private int level;

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
}
