package org.example.models.goods.recipes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CraftingRecipeType {
    CHERRY_BOMB("cherry Bomb", "4 copper ore + 1 coal"),
    BOMB("bomb" , "4 iron ore + 1 coal"),
    MEGA_BOMB("Mega Bomb","4 gold ore + 1 coal"),
    SPRINKLER("Sprinkler","1 copper bar + 1 iron bar"),
    QUALITY_SPRINKLER("Quality Sprinkler","1 Iron bar + 1 Gold bar"),
    IRIDIUM_SPRINKLER("Iridium Sprinkler","1 gold bar + 1 iridium bar"),
    CHARCOAL_KILN("Charcoal Kiln","20 wood + 2 Copper bar"),
    FURNACE("furnace","20 Copper ore + 25 Stone"),
    SCARECROW("Scarecrow","50 wood + 1 coal + 20 Fiber"),
    DELUXE_SCARECROW("Deluxe Scarecrow","50 wood + 1 coal + 20 Fiber + 1 iridium ore"),
    BEE_HOUSE("Bee House","40 wood + 8 coal + 1 iron bar"),
    CHEESE_PRESS("Cheese Press","45 wood + 45 stone + 1 copper bar"),
    KEG("Keg","30 wood + 1 copper bar + 1 iron bar"),
    LOOM("Loom","60 wood + 30 fiber"),
    MAYONNAISE_MACHINE("Mayonnaise Machine","15 wood + 15 stone + 1 copper bar"),
    OIL_MAKER("Oil Maker","100 wood + 1 gold bar + 1 iron bar"),
    PRESERVES_JAR("Preserves Jar","50 wood + 40 stone + 8 coal"),
    DEHYDRATOR("Dehydrator","30 wood + 20 stone + 30 fiber"),
    FISH_SMOKER("Fish Smoker","50 wood + 3 iron bar + 10 coal"),
    MYSTIC_TREE_SEED("Mystic Tree Seed","5 acorn + 5 maple see + 5 pine cone + 5 mahogany seed");

    private final String name;
    private final String ingredients;

    CraftingRecipeType(String name, String ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }
    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }

}
