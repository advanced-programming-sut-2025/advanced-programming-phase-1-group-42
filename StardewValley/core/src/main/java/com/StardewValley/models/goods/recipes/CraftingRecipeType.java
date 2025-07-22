package com.StardewValley.models.goods.recipes;

import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.goods.craftings.CraftingType;

public enum CraftingRecipeType implements GoodType {
    CHERRY_BOMB(CraftingType.CHERRY_BOMB, "4 copper ore + 1 coal", "Mining Level 1",
        "GameAssets/Crafting/Cherry_Bomb.png"),
    BOMB(CraftingType.BOMB, "4 iron ore + 1 coal", "Mining Level 2",
        "GameAssets/Crafting/Bomb.png"),
    MEGA_BOMB(CraftingType.MEGA_BOMB, "4 gold ore + 1 coal", "Mining Level 3",
        "GameAssets/Crafting/Mega_Bomb.png"),
    SPRINKLER(CraftingType.SPRINKLER, "1 copper bar + 1 iron bar", "Farming Level 1",
        "GameAssets/Crafting/Sprinkler.png"),
    QUALITY_SPRINKLER(CraftingType.QUALITY_SPRINKLER, "1 Iron bar + 1 Gold bar", "Farming Level 2",
        "GameAssets/Crafting/Quality_Sprinkler.png"),
    IRIDIUM_SPRINKLER(CraftingType.IRIDIUM_SPRINKLER, "1 gold bar + 1 iridium bar", "Farming Level 3",
        "GameAssets/Crafting/Iridium_Sprinkler.png"),
    CHARCOAL_KILN(CraftingType.CHARCOAL_KILN, "20 wood + 2 Copper bar", "Foraging Level 1",
        "GameAssets/Crafting/Charcoal_Kiln.png"),
    FURNACE(CraftingType.FURNACE, "20 Copper ore + 25 Stone", null,
        "GameAssets/Crafting/Furnace.png"),
    SCARECROW(CraftingType.SCARECROW, "50 wood + 1 coal + 20 Fiber", null,
        "GameAssets/Crafting/Scarecrow.png"),
    DELUXE_SCARECROW(CraftingType.DELUXE_SCARECROW, "50 wood + 1 coal + 20 Fiber + 1 iridium ore", "Farming Level 2",
        "GameAssets/Crafting/Deluxe_Scarecrow.png"),
    BEE_HOUSE(CraftingType.BEE_HOUSE, "40 wood + 8 coal + 1 iron bar", "Farming Level 1",
        "GameAssets/Crafting/Bee_House.png"),
    CHEESE_PRESS(CraftingType.CHEESE_PRESS, "45 wood + 45 stone + 1 copper bar", "Farming Level 2",
        "GameAssets/Crafting/Cheese_Press.png"),
    KEG(CraftingType.KEG, "30 wood + 1 copper bar + 1 iron bar", "Farming Level 3",
        "GameAssets/Crafting/Keg.png"),
    LOOM(CraftingType.LOOM, "60 wood + 30 fiber", "Farming Level 3",
        "GameAssets/Crafting/Loom.png"),
    MAYONNAISE_MACHINE(CraftingType.MAYONNAISE_MACHINE, "15 wood + 15 stone + 1 copper bar", null,
        "GameAssets/Crafting/Mayonnaise_Machine.png"),
    OIL_MAKER(CraftingType.OIL_MAKER, "100 wood + 1 gold bar + 1 iron bar", "Farming Level 3",
        "GameAssets/Crafting/OIL_Maker.png"),
    PRESERVES_JAR(CraftingType.PRESERVES_JAR, "50 wood + 40 stone + 8 coal", "Farming Level 2",
        "GameAssets/Crafting/Preserves_Jar.png"),
    DEHYDRATOR(CraftingType.DEHYDRATOR, "30 wood + 20 stone + 30 fiber", "Pierre's General Store",
        "GameAssets/Crafting/Dehydrator.png"),
    GRASS_STARTER(CraftingType.GRASS_STARTER, "1 wood + 1 fiber", "Pierre's General Store",
        "GameAssets/Crafting/Grass_Starter.png"),
    FISH_SMOKER(CraftingType.FISH_SMOKER, "50 wood + 3 iron bar + 10 coal", "Fish Shop",
        "GameAssets/Crafting/Fish_Smoker.png"),
    MYSTIC_TREE_SEED(CraftingType.MYSTIC_TREE_SEED, "5 acorn + 5 maple seed + 5 pine cone + 5 mahogany seed", "Foraging Level 4",
        "GameAssets/Crafting/Mystic_Tree_Seed.png"),;

    private final CraftingType craftingType;
    private final String ingredients;
    private final String source;
    private final String image;

    CraftingRecipeType(CraftingType craftingType, String ingredients , String source, String image) {
        this.craftingType = craftingType;
        this.ingredients = ingredients;
        this.source = source;
        this.image = image;
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

    @Override
    public String imagePath() {
        return image;
    }


    public String getSource() {
        return source;
    }

    public String getIngredients() {
        return ingredients;
    }

}
