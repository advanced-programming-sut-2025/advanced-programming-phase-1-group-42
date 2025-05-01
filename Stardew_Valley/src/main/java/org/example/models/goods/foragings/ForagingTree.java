package org.example.models.goods.foragings;

import org.example.models.goods.GoodType;

public class ForagingTree extends Foraging{
    private ForagingTreeType type;
    private boolean isWatered;

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

    public boolean isWatered() {
        return isWatered;
    }

    public void setWatered(boolean watered) {
        isWatered = watered;
    }
}
