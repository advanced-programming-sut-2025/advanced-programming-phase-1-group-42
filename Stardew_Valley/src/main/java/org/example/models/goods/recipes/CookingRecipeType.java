package org.example.models.goods.recipes;

public enum CookingRecipeType {
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
