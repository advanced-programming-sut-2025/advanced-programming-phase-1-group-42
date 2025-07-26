package com.StardewValley.models.goods.foragings;

import com.StardewValley.models.goods.GoodType;

public enum ForagingMineralType implements GoodType {

    // Minerals from Foraging Minerals tab
    QUARTZ("Quartz", 25, "GameAssets/Mineral/Quartz.png"),

    EARTH_CRYSTAL("Earth_Crystal", 50, "GameAssets/Mineral/Earth_Crystal.png"),

    FROZEN_TEAR("Frozen_Tear", 75, "GameAssets/Mineral/Frozen_Tear.png"),

    FIRE_QUARTZ("Fire_Quartz", 100, "GameAssets/Mineral/Fire_Quartz.png"),

    EMERALD("Emerald", 250, "GameAssets/Gem/Emerald.png"),

    AQUAMARINE("Aquamarine", 180, "GameAssets/Gem/Aquamarine.png"),

    RUBY("Ruby", 250, "GameAssets/Gem/Ruby.png"),

    AMETHYST("Amethyst", 100, "GameAssets/Gem/Amethyst.png"),

    TOPAZ("Topaz", 80, "GameAssets/Gem/Topaz.png"),

    JADE("Jade", 200, "GameAssets/Gem/Jade.png"),

    DIAMOND("Diamond", 750, "GameAssets/Gem/Diamond.png"),

    PRISMATIC_SHARD("Prismatic_Shard", 2000, "GameAssets/Gem/Prismatic_Shard.png"),

    COPPER_ORE("Copper_Ore", 5, "GameAssets/Crafting/Copper_Ore.png"),

    IRON_ORE("Iron_Ore", 10, "GameAssets/Crafting/Iron_Ore.png"),

    GOLD_ORE("Gold_Ore", 25, "GameAssets/Crafting/Gold_Ore.png"),

    IRIDIUM_ORE("Iridium_Ore", 100, "GameAssets/Crafting/Iridium_Ore.png"),

    COAL("Coal", 15, "GameAssets/Crafting/Coal.png");


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
