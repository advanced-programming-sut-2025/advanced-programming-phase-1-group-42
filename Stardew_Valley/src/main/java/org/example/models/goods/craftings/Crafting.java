package org.example.models.goods.craftings;

import org.example.models.goods.Good;

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
}
