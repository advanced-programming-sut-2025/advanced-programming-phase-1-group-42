package com.StardewValley.models.goods.craftings;

import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.GoodType;

public class Crafting extends Good {

    private final CraftingType craftingType;

    public Crafting(CraftingType craftingType) {
        this.craftingType = craftingType;
    }


    @Override
    public String getName() {
        return craftingType.getName();
    }

    @Override
    public int getSellPrice() {
        return craftingType.getSellPrice();
    }

    @Override
    public GoodType getType() {
        return this.craftingType;
    }

}
