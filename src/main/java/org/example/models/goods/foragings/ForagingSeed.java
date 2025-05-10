package org.example.models.goods.foragings;

import org.example.models.goods.GoodType;

public class ForagingSeed extends Foraging {
    private ForagingSeedType type;

    public ForagingSeed(ForagingSeedType type) {
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
