package org.example.models.goods.recipes;

import org.example.models.goods.GoodType;

public class CookingRecipe extends Recipe {
    private CookingRecipeType type;

    CookingRecipe(CookingRecipeType type) {
        this.type = type;
    }

    public GoodType getType() {
        return type;
    }

    @Override
    public String getName(){
        return type.getGoodType().getName();
    }

    @Override
    public int getSellPrice() {
        return 0;
    }

}
