package org.example.models.goods.farmings;

import org.example.models.goods.GoodType;

public class FarmingCrop extends Farming {
    private FarmingCropType type;

    public FarmingCrop(FarmingCropType type) {
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
