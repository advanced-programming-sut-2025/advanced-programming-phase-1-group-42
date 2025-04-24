package org.example.models.goods.recipes;

public class CookingRecipe extends Recipe {
    CookingRecipeType type;

    public CookingRecipe(CookingRecipeType type) {
        this.type = type;
    }

    public CookingRecipeType getType() {
        return type;
    }

    @Override
    public String getName(){
        return type.getFoodType().getName() ;
    }

    @Override
    public int getSellPrice() {
        return 0;
    }

}
