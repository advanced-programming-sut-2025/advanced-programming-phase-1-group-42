package org.example.models.goods.craftings;

import org.example.models.goods.Good;
import org.example.models.goods.GoodType;

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
        return craftingType;
    }

}
