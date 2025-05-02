package org.example.models.goods.recipes;

import org.example.models.goods.GoodType;
import org.example.models.goods.craftings.CraftingType;

public enum CraftingRecipeType implements GoodType {

    CHERRY_BOMB(CraftingType.CHERRY_BOMB, "4 copper ore + 1 coal"),
    BOMB(CraftingType.BOMB , "4 iron ore + 1 coal"),
    MEGA_BOMB(CraftingType.MEGA_BOMB,"4 gold ore + 1 coal"),
    SPRINKLER(CraftingType.SPRINKLER,"1 copper bar + 1 iron bar"),
    QUALITY_SPRINKLER(CraftingType.QUALITY_SPRINKLER,"1 Iron bar + 1 GOLD bar"),
    IRIDIUM_SPRINKLER(CraftingType.IRIDIUM_SPRINKLER,"1 gold bar + 1 iridium bar"),
    CHARCOAL_KILN(CraftingType.CHARCOAL_KILN,"20 wood + 2 Copper bar"),
    FURNACE(CraftingType.FURNACE,"20 Copper ore + 25 Stone"),
    SCARECROW(CraftingType.SCARECROW,"50 wood + 1 coal + 20 Fiber"),
    DELUXE_SCARECROW(CraftingType.DELUXE_SCARECROW,"50 wood + 1 coal + 20 Fiber + 1 iridium ore"),
    BEE_HOUSE(CraftingType.BEE_HOUSE,"40 wood + 8 coal + 1 iron bar"),
    CHEESE_PRESS(CraftingType.CHEESE_PRESS,"45 wood + 45 stone + 1 copper bar"),
    KEG(CraftingType.KEG,"30 wood + 1 copper bar + 1 iron bar"),
    LOOM(CraftingType.LOOM,"60 wood + 30 fiber"),
    MAYONNAISE_MACHINE(CraftingType.MAYONNAISE_MACHINE,"15 wood + 15 stone + 1 copper bar"),
    OIL_MAKER(CraftingType.OIL_MAKER,"100 wood + 1 gold bar + 1 iron bar"),
    PRESERVES_JAR(CraftingType.PRESERVES_JAR,"50 wood + 40 stone + 8 coal"),
    DEHYDRATOR(CraftingType.DEHYDRATOR,"30 wood + 20 stone + 30 fiber"),
    FISH_SMOKER(CraftingType.FISH_SMOKER,"50 wood + 3 iron bar + 10 coal"),
    MYSTIC_TREE_SEED(CraftingType.MYSTIC_TREE_SEED,"5 acorn + 5 maple see + 5 pine cone + 5 mahogany seed");

    private final CraftingType craftingType;
    private final String ingredients;

    CraftingRecipeType(CraftingType craftingType, String ingredients) {
        this.craftingType = craftingType;
        this.ingredients = ingredients;
    }

    @Override
    public int getSellPrice() {
        return 0;
    }

    @Override
    public int getEnergy() {
        return 0;
    }

    public String getName() {
        return craftingType.getName();
    }



    public String getIngredients() {
        return ingredients;
    }

}
