package org.example.models.goods.recipes;

import org.example.models.game_structure.BuffType;
import org.example.models.goods.foods.Food;

import java.util.ArrayList;

public class CookingRecipe extends Recipe {
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    CookingRecipeType type;

    CookingRecipe(CookingRecipeType type) {
        this.type = type;
    }

    public CookingRecipeType getType() {
        return type;
    }

    @Override
    public String getName(){
        return type.getFoodType().getName();
    }

    @Override
    public int getSellPrice() {
        return 0;
=======
=======
>>>>>>> Stashed changes


    @Override
    public String getName(){
        //TODO
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    }

}
