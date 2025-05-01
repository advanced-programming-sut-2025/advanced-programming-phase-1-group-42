package org.example.models.goods.foragings;

import org.example.models.goods.GoodType;
import org.example.models.goods.foods.Food;

public class ForagingCrop extends Foraging {
    private ForagingCropType type;


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
