package com.StardewValley.models.goods.products;

import com.StardewValley.models.goods.GoodType;


public enum ProductType implements GoodType {
    //TODO

    //BlackSmith
    COPPER_BAR("Copper_Bar", -1, 75,"/assets/GameAssets/Resource/Copper_Bar.png"),

    IRON_BAR("Iron_Bar", -1, 150,"/assets/GameAssets/Resource/Iron_Bar.png"),

    COAL("Coal", -1, 150,"/assets/GameAssets/Resource/Coal.png"),

    GOLD_BAR("Gold_Bar", -1, 400,"/assets/GameAssets/Resource/Gold_Bar.png"),

    IRIDIUM_BAR("Iridium_Bar", -1, 500,"/assets/GameAssets/Resource/Iridium_Bar.png"),

    //JojaMart
    JOJA_MART("Joja_Mart", 13, 75,"/assets/GameAssets/Concessions/Joja_Cola_%28large%29.png"),

    //Carpenter's Shop
    WOOD("Wood", -1, 10,"/assets/GameAssets/Resource/Wood.png"),

    STONE("Stone", -1, 20,"/assets/GameAssets/Resource/Stone.png"),

    GRASS("Grass", -1, -1, "GameAssets/sprites/Grass pic.png"),

    //Marnie's Ranch
    HAY("Hay", -1, 50, "GameAssets/Tools/Hay_Hopper.png"),
    MILK_PAIL("Milk_Pail", -1, 1000,"/assets/GameAssets/Tools/Milk_Pail.png"),
    SHEARS("Shears", -1, 1000,"/assets/GameAssets/Tools/Shears.png"),

    //Pierre's general store
    // All year
    WEDDING_RING("Wedding_Ring", -1, 10000,"/assets/GameAssets/Crafting/Wedding_Ring.png"),

    BOUQUET("Bouquet", -1, 1000,"\\assets\\GameAssets\\Special_item/Bouquet.png"),

    SPEED_GRO("Speed_Gro", -1, 100,"\\assets\\GameAssets\\Fertilizer/Speed_Gro.png"),

    BASIC_RETAINING_SOIL("Basic_Retaining_Soil", -1, 100,"\\assets\\GameAssets\\Fertilizer/Basic_Retaining_Soil.png"),

    QUALITY_RETAINING_SOIL("Quality_Retaining_Soil", -1, 150,"\\assets\\GameAssets\\Fertilizer\\Quality_Retaining_Soil.png"),

    DELUXE_RETAINING_SOIL("Deluxe_Retaining_Soil", -1, 150,"\\assets\\GameAssets\\Fertilizer\\Deluxe_Retaining_Soil.png"),

    OIL("Oil", -1, 1200,"\\assets\\GameAssets\\Crafting\\Oil.png"),

    FIBER("Fiber", -1, -1,"/assets/GameAssets/Resource/Fiber.png"),

    //Fish Shop
    TROUT_SOUP("Trout_Soup", -1, 250,"\\assets\\GameAssets\\Recipe\\Trout_Soup.png"),

    // BackPack
    LARGE_PACK("Large_Pack", -1, 2000,"\\assets\\GameAssets\\Tools\\Backpack.png"),

    DELUXE_PACK("Deluxe_Pack", -1, 10000,"\\assets\\GameAssets\\Recipe\\36_Backpack.png"),

    HARD_WOOD("Hard_Wood",-1,0,"\\assets\\GameAssets\\Resource\\Hardwood.png");

    ProductType(String name, int energy, int price, String imagePath) {
        this.name = name;
        this.energy = energy;
        this.price = price;
        this.imagePath = imagePath;
    }

    private String name;
    private int energy;
    private int price;
    private String imagePath;
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
        return imagePath;
    }
}
