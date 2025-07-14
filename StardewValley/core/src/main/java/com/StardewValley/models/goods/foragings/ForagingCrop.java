package com.StardewValley.models.goods.foragings;

import com.StardewValley.models.goods.GoodType;

public class ForagingCrop extends Foraging {
    private ForagingCropType type;

    public ForagingCrop(ForagingCropType type) {
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
