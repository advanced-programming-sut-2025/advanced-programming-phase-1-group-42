package org.example.models.goods.craftings;

import org.example.models.goods.GoodType;
import org.example.models.goods.artisans.ArtisanType;

import java.util.ArrayList;
import java.util.Arrays;

public enum CraftingType implements GoodType {
    CHERRY_BOMB("Cherry_Bomb", 50, new ArrayList<>()),

    BOMB("Bomb" , 50, new ArrayList<>()),

    MEGA_BOMB("Mega_Bomb",50, new ArrayList<>()),

    SPRINKLER("Sprinkler",0, new ArrayList<>()),

    QUALITY_SPRINKLER("Quality_Sprinkler",0, new ArrayList<>()),

    IRIDIUM_SPRINKLER("Iridium_Sprinkler",0, new ArrayList<>()),

    CHARCOAL_KILN("Charcoal_Kiln",0, new ArrayList<>(Arrays.asList(ArtisanType.COAL))),

    FURNACE("Furnace",0, new ArrayList<>(Arrays.asList(ArtisanType.METAL_BAR))),

    SCARECROW("Scarecrow",0, new ArrayList<>()),

    DELUXE_SCARECROW("Deluxe_Scarecrow",0, new ArrayList<>()),

    BEE_HOUSE("Bee_House",0, new ArrayList<>(Arrays.asList(ArtisanType.HONEY))),

    CHEESE_PRESS("Cheese_Press",0, new ArrayList<>(Arrays.asList(ArtisanType.CHEESE, ArtisanType.GOAT_CHEESE))),

    KEG("Keg",0, new ArrayList<>(
            Arrays.asList(
                    ArtisanType.BEER,
                    ArtisanType.COFFEE,
                    ArtisanType.JUICE,
                    ArtisanType.MEAD,
                    ArtisanType.PALE_ALE,
                    ArtisanType.WINE
            )
    )),

    LOOM("Loom",0, new ArrayList<>(Arrays.asList(
            ArtisanType.CLOTH
    ))),

    MAYONNAISE_MACHINE("Mayonnaise_Machine",0, new ArrayList<>(Arrays.asList(
            ArtisanType.MAYONNAISE,
            ArtisanType.DUCK_MAYONNAISE,
            ArtisanType.DINOSAUR_MAYONNAISE
    ))),

    OIL_MAKER("Oil_Maker",0,
            new ArrayList<>(Arrays.asList(
                    ArtisanType.TRUFFLE_OIL,
                    ArtisanType.OIL
            ))
    ),

    PRESERVES_JAR("Preserves_Jar",0,
            new ArrayList<>(Arrays.asList(
                    ArtisanType.PICKLES,
                    ArtisanType.JELLY
            ))
    ),

    DEHYDRATOR("Dehydrator",0,
            new ArrayList<>(Arrays.asList(
                    ArtisanType.DRIED_MUSHROOMS,
                    ArtisanType.DRIED_FRUIT,
                    ArtisanType.RAISINS
            ))
    ),

    FISH_SMOKER("Fish_Smoker",10000,
            new ArrayList<>(Arrays.asList(
                    ArtisanType.SMOKED_FISH
            ))
    ),

    MYSTIC_TREE_SEED("Mystic_Tree_Seed",100,
            new ArrayList<>()),

    CASK("Cask",0, new ArrayList<>(
            Arrays.asList(
                    ArtisanType.VINEGAR
            ))
    );


    private final String name;
    private final int sellPrice;
    private final ArrayList<ArtisanType> artisanTypes;

    CraftingType(String name, int sellPrice, ArrayList<ArtisanType> artisanTypes) {
        this.name = name;
        this.sellPrice = sellPrice;
        this.artisanTypes = artisanTypes;
    }

    public String getName() {
        return name;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    @Override
    public int getEnergy() {
        return 0;
    }


}
