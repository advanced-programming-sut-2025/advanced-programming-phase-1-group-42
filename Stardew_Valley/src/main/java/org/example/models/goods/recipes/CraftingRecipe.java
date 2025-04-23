package org.example.models.goods.recipes;

public class CraftingRecipe extends Recipe {
    CraftingRecipeType type;
    CraftingRecipe(CraftingRecipeType type) {
        this.type = type;
    }

    public CraftingRecipeType getType() {
        return type;
    }

    @Override
    public String getName(){
        return type.getName();
    }


}
