package org.example.models.goods.recipes;

import org.example.models.goods.GoodType;

public enum CookingRecipeType implements GoodType {
    MEOW("meow");
    //TODO

    private final String name;
    CookingRecipeType(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
