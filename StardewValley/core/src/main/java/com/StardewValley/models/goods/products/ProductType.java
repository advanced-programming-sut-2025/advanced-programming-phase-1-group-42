package com.StardewValley.models.goods.products;

import com.StardewValley.models.goods.GoodType;


public enum ProductType implements GoodType {
    //TODO

    //BlackSmith
    COPPER_BAR("Copper_Bar", -1, 75),

    IRON_BAR("Iron_Bar", -1, 150),

    COAL("Coal", -1, 150),

    GOLD_BAR("Gold_Bar", -1, 400),

    IRIDIUM_BAR("Iridium_Bar", -1, 500),

    //JojaMart
    JOJA_MART("Joja_Mart", 13, 75),

    //Carpenter's Shop
    WOOD("Wood", -1, 10),

    STONE("Stone", -1, 20),

    GRASS("Grass", -1, -1),

    //Marnie's Ranch
    HAY("Hay", -1, 50),
    MILK_PAIL("Milk_Pail", -1, 1000),
    SHEARS("Shears", -1, 1000),

    //Pierre's general store
    // All year
    WEDDING_RING("Wedding_Ring", -1, 10000),

    BOUQUET("Bouquet", -1, 1000),

    SPEED_GRO("Speed_Gro", -1, 100),

    BASIC_RETAINING_SOIL("Basic_Retaining_Soil", -1, 100),

    QUALITY_RETAINING_SOIL("Quality_Retaining_Soil", -1, 150),

    DELUXE_RETAINING_SOIL("Deluxe_Retaining_Soil", -1, 150),

    OIL("Oil", -1, 1200),

    FIBER("Fiber", -1, -1),

    // BackPacks

    //Fish Shop
    TROUT_SOUP("Trout_Soup", -1, 250),

    // BackPack
    LARGE_PACK("Large_Pack", -1, 2000),

    DELUXE_PACK("Deluxe_Pack", -1, 10000),

    HARD_WOOD("Hard_Wood",-1,0);

    ProductType(String name, int energy, int price) {
        this.name = name;
        this.energy = energy;
        this.price = price;
    }

    private String name;
    private int energy;
    private int price;

    @Override
    public int getSellPrice() {
        return price;
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String imagePath() {
        return "";
    }
}
