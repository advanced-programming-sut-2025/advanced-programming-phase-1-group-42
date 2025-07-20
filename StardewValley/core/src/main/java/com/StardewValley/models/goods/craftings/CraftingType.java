package com.StardewValley.models.goods.craftings;

import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.goods.artisans.ArtisanType;

import java.util.ArrayList;
import java.util.Arrays;

public enum CraftingType implements GoodType
{
    CHERRY_BOMB("Cherry_Bomb", 50, new ArrayList<>(), "/assets/GameAssets/Crafting/Cherry_Bomb.png"),

    BOMB("Bomb", 50, new ArrayList<>(), "/assets/GameAssets/Crafting/Bomb.png"),

    MEGA_BOMB("Mega_Bomb", 50, new ArrayList<>(), "/assets/GameAssets/Crafting/Mega_Bomb.png"),

    SPRINKLER("Sprinkler", 0, new ArrayList<>(), "/assets/GameAssets/Crafting/Sprinkler.png"),

    QUALITY_SPRINKLER("Quality_Sprinkler", 0, new ArrayList<>(), "/assets/GameAssets/Crafting/Quality_Sprinkler.png"),

    IRIDIUM_SPRINKLER("Iridium_Sprinkler", 0, new ArrayList<>(), "/assets/GameAssets/Crafting/Iridium_Sprinkler.png"),

    CHARCOAL_KILN("Charcoal_Kiln", 0, new ArrayList<>(Arrays.asList(ArtisanType.COAL)), "/assets/GameAssets/Crafting/Charcoal_Kiln.png"),

    FURNACE("Furnace", 0, new ArrayList<>(Arrays.asList(ArtisanType.METAL_BAR)), "/assets/GameAssets/Crafting/Furnace.png"),

    SCARECROW("Scarecrow", 0, new ArrayList<>(), "/assets/GameAssets/Crafting/Scarecrow.png"),

    DELUXE_SCARECROW("Deluxe_Scarecrow", 0, new ArrayList<>(), "/assets/GameAssets/Crafting/Deluxe_Scarecrow.png"),

    BEE_HOUSE("Bee_House", 0, new ArrayList<>(Arrays.asList(ArtisanType.HONEY)), "/assets/GameAssets/Crafting/Bee_House.png"),

    CHEESE_PRESS("Cheese_Press", 0, new ArrayList<>(Arrays.asList(ArtisanType.CHEESE, ArtisanType.GOAT_CHEESE)), "/assets/GameAssets/Crafting/Cheese_Press.png"),

    KEG("Keg", 0, new ArrayList<>(Arrays.asList(
        ArtisanType.BEER,
        ArtisanType.COFFEE,
        ArtisanType.JUICE,
        ArtisanType.MEAD,
        ArtisanType.PALE_ALE,
        ArtisanType.WINE,
        ArtisanType.VINEGAR
    )), "/assets/GameAssets/Crafting/Keg.png"),

    LOOM("Loom", 0, new ArrayList<>(Arrays.asList(ArtisanType.CLOTH)), "/assets/GameAssets/Crafting/Loom.png"),

    MAYONNAISE_MACHINE("Mayonnaise_Machine", 0, new ArrayList<>(Arrays.asList(
        ArtisanType.MAYONNAISE,
        ArtisanType.DUCK_MAYONNAISE,
        ArtisanType.DINOSAUR_MAYONNAISE
    )), "/assets/GameAssets/Crafting/Mayonnaise_Machine.png"),

    OIL_MAKER("Oil_Maker", 0, new ArrayList<>(Arrays.asList(
        ArtisanType.TRUFFLE_OIL,
        ArtisanType.OIL
    )), "/assets/GameAssets/Crafting/Oil_Maker.png"),

    PRESERVES_JAR("Preserves_Jar", 0, new ArrayList<>(Arrays.asList(
        ArtisanType.PICKLES,
        ArtisanType.JELLY
    )), "/assets/GameAssets/Crafting/Preserves_Jar.png"),

    DEHYDRATOR("Dehydrator", 0, new ArrayList<>(Arrays.asList(
        ArtisanType.DRIED_MUSHROOMS,
        ArtisanType.DRIED_FRUIT,
        ArtisanType.RAISINS
    )), "/assets/GameAssets/Crafting/Dehydrator.png"),

    FISH_SMOKER("Fish_Smoker", 10000, new ArrayList<>(Arrays.asList(
        ArtisanType.SMOKED_FISH
    )), "/assets/GameAssets/Crafting/Fish_Smoker.png"),

    MYSTIC_TREE_SEED("Mystic_Tree_Seed", 100, new ArrayList<>(), "/assets/GameAssets/Crafting/Mystic_Tree_Seed.png"),

    CASK("Cask", 0, new ArrayList<>(Arrays.asList(
        ArtisanType.VINEGAR
    )), "/assets/GameAssets/Crafting/Cask.png"),

    GRASS_STARTER("Grass_Starter", 100, new ArrayList<>(), "/assets/GameAssets/Crafting/Grass_Starter.png");



    private final String name;
    private final int sellPrice;
    private final ArrayList<ArtisanType> artisanTypes;
    private final String imagePath;

    CraftingType(String name, int sellPrice, ArrayList<ArtisanType> artisanTypes, String imagePath) {
        this.name = name;
        this.sellPrice = sellPrice;
        this.artisanTypes = artisanTypes;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    @Override
    public String imagePath() {
        return imagePath;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    @Override
    public int getEnergy() {
        return 0;
    }

    public ArrayList<ArtisanType> getArtisanTypes() {
        return this.artisanTypes;
    }
}
