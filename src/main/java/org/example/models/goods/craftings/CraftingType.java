package org.example.models.goods.craftings;

import org.example.models.goods.GoodType;

public enum CraftingType implements GoodType {
    CHERRY_BOMB("Cherry_Bomb", 50),

    BOMB("Bomb" , 50),

    MEGA_BOMB("Mega_Bomb",50),

    SPRINKLER("Sprinkler",0),

    QUALITY_SPRINKLER("Quality_Sprinkler",0),

    IRIDIUM_SPRINKLER("Iridium_Sprinkler",0),

    CHARCOAL_KILN("Charcoal_Kiln",0),

    FURNACE("Furnace",0),

    SCARECROW("Scarecrow",0),

    DELUXE_SCARECROW("Deluxe_Scarecrow",0),

    BEE_HOUSE("Bee_House",0),

    CHEESE_PRESS("Cheese_Press",0),

    KEG("Keg",0),

    LOOM("Loom",0),

    MAYONNAISE_MACHINE("Mayonnaise_Machine",0),

    OIL_MAKER("Oil_Maker",0),

    PRESERVES_JAR("Preserves_Jar",0),

    DEHYDRATOR("Dehydrator",0),

    FISH_SMOKER("Fish_Smoker",10000),

    MYSTIC_TREE_SEED("Mystic_Tree_Seed",100),

    CASK("Cask",0);


    private final String name;
    private final int sellPrice;

    CraftingType(String name, int sellPrice) {
        this.name = name;
        this.sellPrice = sellPrice;
    }

    public String getName() {
        return name;
    }

    public int getSellPrice() {
        return sellPrice;
    }


    //Crafts cant be eaten
    @Override
    public int getEnergy() {return -1;}


}
