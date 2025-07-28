package com.StardewValley.models.goods.foragings;

import com.StardewValley.models.goods.GoodType;

public class ForagingMineral extends Foraging{
    private ForagingMineralType type;

    public ForagingMineral(ForagingMineralType type) {
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
