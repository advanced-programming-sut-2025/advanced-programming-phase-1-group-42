package org.example.models.goods.recipes;

public class CraftingRecipe extends Recipe {
    CraftingRecipeType type;
<<<<<<< Updated upstream
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
<<<<<<< Updated upstream
    @Override
    public int getSellPrice() {
        return 0;
    }

=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes

}
