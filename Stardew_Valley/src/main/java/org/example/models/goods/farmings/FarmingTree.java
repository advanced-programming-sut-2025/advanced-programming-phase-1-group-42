package org.example.models.goods.farmings;

import org.example.models.goods.GoodType;

public class FarmingTree extends Farming {
    private FarmingTreeType type;

    public FarmingTree(FarmingTreeType type) {
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
