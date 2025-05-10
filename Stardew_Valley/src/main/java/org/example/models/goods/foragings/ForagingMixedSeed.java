package org.example.models.goods.foragings;

import org.example.models.goods.GoodType;

public class ForagingMixedSeed extends Foraging {
    private ForagingMixedSeedType type;

    public ForagingMixedSeed(ForagingMixedSeedType type) {
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
