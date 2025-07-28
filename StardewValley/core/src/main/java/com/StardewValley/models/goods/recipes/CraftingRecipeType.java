package com.StardewValley.models.goods.recipes;

import com.StardewValley.models.Pair;
import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.goods.craftings.CraftingType;
import com.StardewValley.models.goods.foragings.ForagingMineralType;
import com.StardewValley.models.goods.foragings.ForagingSeedType;
import com.StardewValley.models.goods.foragings.ForagingTreeType;
import com.StardewValley.models.goods.products.ProductType;

import java.util.ArrayList;
import java.util.Arrays;

public enum CraftingRecipeType implements GoodType {
    CHERRY_BOMB(new ArrayList<>(Arrays.asList(
        new Pair<>(ForagingMineralType.COPPER_ORE, 4),
        new Pair<>(ForagingMineralType.COAL, 1)
    )), CraftingType.CHERRY_BOMB, "Mining Level 1",
        "GameAssets/Crafting/Cherry_Bomb.png"),

    BOMB(new ArrayList<>(Arrays.asList(
        new Pair<>(ForagingMineralType.IRON_ORE, 4),
        new Pair<>(ForagingMineralType.COAL, 1)
    )), CraftingType.BOMB, "Mining Level 2",
        "GameAssets/Crafting/Bomb.png"),

    MEGA_BOMB(new ArrayList<>(Arrays.asList(
        new Pair<>(ForagingMineralType.GOLD_ORE, 4),
        new Pair<>(ForagingMineralType.COAL, 1)
    )), CraftingType.MEGA_BOMB, "Mining Level 3",
        "GameAssets/Crafting/Mega_Bomb.png"),

    SPRINKLER(new ArrayList<>(Arrays.asList(
        new Pair<>(ForagingMineralType.COPPER_ORE, 1),
        new Pair<>(ProductType.IRON_BAR, 1)
    )), CraftingType.SPRINKLER, "Farming Level 1",
        "GameAssets/Crafting/Sprinkler.png"),

    QUALITY_SPRINKLER(new ArrayList<>(Arrays.asList(
        new Pair<>(ProductType.IRON_BAR, 1),
        new Pair<>(ProductType.GOLD_BAR, 1)
    )), CraftingType.QUALITY_SPRINKLER, "Farming Level 2",
        "GameAssets/Crafting/Quality_Sprinkler.png"),

    IRIDIUM_SPRINKLER(new ArrayList<>(Arrays.asList(
        new Pair<>(ProductType.GOLD_BAR, 1),
        new Pair<>(ProductType.IRIDIUM_BAR, 1)
    )), CraftingType.IRIDIUM_SPRINKLER, "Farming Level 3",
        "GameAssets/Crafting/Iridium_Sprinkler.png"),

    CHARCOAL_KILN(new ArrayList<>(Arrays.asList(
        new Pair<>(ProductType.WOOD, 20),
        new Pair<>(ProductType.COPPER_BAR, 2)
    )), CraftingType.CHARCOAL_KILN, "Foraging Level 1",
        "GameAssets/Crafting/Charcoal_Kiln.png"),

    FURNACE(new ArrayList<>(Arrays.asList(
        new Pair<>(ProductType.COPPER_BAR, 20),
        new Pair<>(ProductType.STONE, 25)
    )), CraftingType.FURNACE, null,
        "GameAssets/Crafting/Furnace.png"),

    SCARECROW(new ArrayList<>(Arrays.asList(
        new Pair<>(ProductType.WOOD, 50),
        new Pair<>(ProductType.COAL, 1),
        new Pair<>(ProductType.FIBER, 20)
    )), CraftingType.SCARECROW, null,
        "GameAssets/Crafting/Scarecrow.png"),

    DELUXE_SCARECROW(new ArrayList<>(Arrays.asList(
        new Pair<>(ProductType.WOOD, 50),
        new Pair<>(ProductType.COAL, 1),
        new Pair<>(ProductType.FIBER, 20),
        new Pair<>(ProductType.IRIDIUM_BAR, 1)
    )), CraftingType.DELUXE_SCARECROW, "Farming Level 2",
        "GameAssets/Crafting/Deluxe_Scarecrow.png"),

    BEE_HOUSE(new ArrayList<>(Arrays.asList(
        new Pair<>(ProductType.WOOD, 40),
        new Pair<>(ProductType.COAL, 8),
        new Pair<>(ProductType.IRON_BAR, 1)
    )), CraftingType.BEE_HOUSE, "Farming Level 1",
        "GameAssets/Crafting/Bee_House.png"),

    CHEESE_PRESS(new ArrayList<>(Arrays.asList(
        new Pair<>(ProductType.WOOD, 45),
        new Pair<>(ProductType.STONE, 45),
        new Pair<>(ProductType.COPPER_BAR, 1)
    )), CraftingType.CHEESE_PRESS, "Farming Level 2",
        "GameAssets/Crafting/Cheese_Press.png"),

    KEG(new ArrayList<>(Arrays.asList(
        new Pair<>(ProductType.WOOD, 30),
        new Pair<>(ProductType.COPPER_BAR, 1),
        new Pair<>(ProductType.IRON_BAR, 1)
    )), CraftingType.KEG, "Farming Level 3",
        "GameAssets/Crafting/Keg.png"),

    LOOM(new ArrayList<>(Arrays.asList(
        new Pair<>(ProductType.WOOD, 60),
        new Pair<>(ProductType.FIBER, 30)
    )), CraftingType.LOOM, "Farming Level 3",
        "GameAssets/Crafting/Loom.png"),

    MAYONNAISE_MACHINE(new ArrayList<>(Arrays.asList(
        new Pair<>(ProductType.WOOD, 15),
        new Pair<>(ProductType.STONE, 15),
        new Pair<>(ProductType.COPPER_BAR, 1)
    )), CraftingType.MAYONNAISE_MACHINE, null,
        "GameAssets/Crafting/Mayonnaise_Machine.png"),

    OIL_MAKER(new ArrayList<>(Arrays.asList(
        new Pair<>(ProductType.WOOD, 100),
        new Pair<>(ProductType.GOLD_BAR, 1),
        new Pair<>(ProductType.IRON_BAR, 1)
    )), CraftingType.OIL_MAKER, "Farming Level 3",
        "GameAssets/Crafting/OIL_Maker.png"),

    PRESERVES_JAR(new ArrayList<>(Arrays.asList(
        new Pair<>(ProductType.WOOD, 50),
        new Pair<>(ProductType.STONE, 40),
        new Pair<>(ProductType.COAL, 8)
    )), CraftingType.PRESERVES_JAR, "Farming Level 2",
        "GameAssets/Crafting/Preserves_Jar.png"),

    DEHYDRATOR(new ArrayList<>(Arrays.asList(
        new Pair<>(ProductType.WOOD, 30),
        new Pair<>(ProductType.STONE, 20),
        new Pair<>(ProductType.FIBER, 30)
    )), CraftingType.DEHYDRATOR, "Pierre's General Store",
        "GameAssets/Crafting/Dehydrator.png"),

    GRASS_STARTER(new ArrayList<>(Arrays.asList(
        new Pair<>(ProductType.WOOD, 1),
        new Pair<>(ProductType.FIBER, 1)
    )), CraftingType.GRASS_STARTER, "Pierre's General Store",
        "GameAssets/Crafting/Grass_Starter.png"),

    FISH_SMOKER(new ArrayList<>(Arrays.asList(
        new Pair<>(ProductType.WOOD, 50),
        new Pair<>(ProductType.IRON_BAR, 3),
        new Pair<>(ProductType.COAL, 10)
    )), CraftingType.FISH_SMOKER, "Fish Shop",
        "GameAssets/Crafting/Fish_Smoker.png"),

    MYSTIC_TREE_SEED(new ArrayList<>(Arrays.asList(
        new Pair<>(ForagingTreeType.ACORNS, 5),
        new Pair<>(ForagingTreeType.MAPLE_SEEDS, 5),
        new Pair<>(ForagingTreeType.PINE_CONES, 5),
        new Pair<>(ForagingTreeType.MAHOGANY_SEEDS, 5)
    )), CraftingType.MYSTIC_TREE_SEED, "Foraging Level 4",
        "GameAssets/Crafting/Mystic_Tree_Seed.png");

    private final ArrayList<Pair<GoodType, Integer>> ingredients;
    private final CraftingType craftingType;
    private final String source;
    private final String image;

    CraftingRecipeType(ArrayList<Pair<GoodType, Integer>> ingredients, CraftingType craftingType, String source, String image) {
        this.ingredients = ingredients;
        this.craftingType = craftingType;
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

    public CraftingType getCraftingType() {
        return craftingType;
    }

    public String getSource() {
        return source;
    }

    public ArrayList<Pair<GoodType, Integer>> getIngredients() {
        return ingredients;
    }
}
