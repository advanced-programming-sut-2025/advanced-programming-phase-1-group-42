package com.StardewValley.models.goods.recipes;

import com.StardewValley.models.goods.GoodType;

public class CraftingRecipe extends Recipe {
    private CraftingRecipeType type;

    public CraftingRecipe(CraftingRecipeType type) {
        this.type = type;
    }

    public GoodType getType() {
        return type;
    }

    @Override
    public String getName(){
        return type.getName();
    }

    @Override
    public int getSellPrice() {
        return 0;
    }


}
