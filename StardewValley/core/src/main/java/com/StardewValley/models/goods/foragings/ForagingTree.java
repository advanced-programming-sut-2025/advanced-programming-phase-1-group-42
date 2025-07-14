package com.StardewValley.models.goods.foragings;

import com.StardewValley.models.goods.GoodType;

public class ForagingTree extends Foraging{
    private ForagingTreeType type;


    public ForagingTree(ForagingTreeType type) {
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

    @Override
    public String toString() {
        return "ForagingTree: \n" +
                "type: " + type +
                "\nlevel: " + level + "\n";
    }

}
