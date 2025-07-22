package com.StardewValley.models.goods.foragings;

import com.StardewValley.models.goods.GoodType;

public enum ForagingMineralType implements GoodType {

    // Minerals from Foraging Minerals tab
    QUARTZ("Quartz", 25, "/assets/GameAssets/Mineral/Quartz.png"),

    EARTH_CRYSTAL("Earth_Crystal", 50, "/assets/GameAssets/Mineral/Earth_Crystal.png"),

    FROZEN_TEAR("Frozen_Tear", 75, "/assets/GameAssets/Mineral/Frozen_Tear.png"),

    FIRE_QUARTZ("Fire_Quartz", 100, "/assets/GameAssets/Mineral/Fire_Quartz.png"),

    EMERALD("Emerald", 250, "/assets/GameAssets/Mineral/Emerald.png"),

    AQUAMARINE("Aquamarine", 180, "/assets/GameAssets/Mineral/Aquamarine.png"),

    RUBY("Ruby", 250, "/assets/GameAssets/Mineral/Ruby.png"),

    AMETHYST("Amethyst", 100, "/assets/GameAssets/Mineral/Amethyst.png"),

    TOPAZ("Topaz", 80, "/assets/GameAssets/Mineral/Topaz.png"),

    JADE("Jade", 200, "/assets/GameAssets/Mineral/Jade.png"),

    DIAMOND("Diamond", 750, "/assets/GameAssets/Mineral/Diamond.png"),

    PRISMATIC_SHARD("Prismatic_Shard", 2000, "/assets/GameAssets/Mineral/Prismatic_Shard.png"),

    COPPER_ORE("Copper_Ore", 5, "/assets/GameAssets/Mineral/Copper_Ore.png"),

    IRON_ORE("Iron_Ore", 10, "/assets/GameAssets/Mineral/Iron_Ore.png"),

    GOLD_ORE("Gold_Ore", 25, "/assets/GameAssets/Mineral/Gold_Ore.png"),

    IRIDIUM_ORE("Iridium_Ore", 100, "/assets/GameAssets/Mineral/Iridium_Ore.png"),

    COAL("Coal", 15, "/assets/GameAssets/Mineral/Coal.png");


    private String name;
    private int sellPrice;
    private final String imagePath;

    ForagingMineralType(String name, int sellPrice , String imagePath) {
        this.name = name;
        this.sellPrice = sellPrice;
        this.imagePath = imagePath;
    }


    @Override
    public int getSellPrice() {
        return this.sellPrice;
    }

    @Override
    public int getEnergy() {
        return 0;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String imagePath() {
        return imagePath;
    }

    @Override
    public String toString() {
        StringBuilder list =  new StringBuilder();
        list.append("Name: ").append(name).append("\n");
        list.append("SellPrice: ").append(getSellPrice()).append("\n");
        return list.toString();
    }
}
