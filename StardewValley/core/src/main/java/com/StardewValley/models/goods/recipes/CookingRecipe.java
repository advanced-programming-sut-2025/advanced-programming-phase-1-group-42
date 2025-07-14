package com.StardewValley.models.goods.recipes;

public class CookingRecipe extends Recipe {
    private CookingRecipeType type;

    public CookingRecipe(CookingRecipeType type) {
        this.type = type;
    }

    public CookingRecipeType getType() {
        return type;
    }

    @Override
    public String getName(){
        return type.getName() ;
    }

    @Override
    public int getSellPrice() {
        return 0;
    }

}
