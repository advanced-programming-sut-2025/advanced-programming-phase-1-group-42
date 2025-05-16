package org.example.models.goods.foragings;

import org.example.models.goods.GoodType;

public enum ForagingMineralType implements GoodType {

    // Minerals from Foraging Minerals tab
    QUARTZ("Quartz", 25),

    EARTH_CRYSTAL("Earth_Crystal", 50),

    FROZEN_TEAR("Frozen_Tear", 75),

    FIRE_QUARTZ("Fire_Quartz", 100),

    EMERALD("Emerald", 250),

    AQUAMARINE("Aquamarine", 180),

    RUBY("Ruby", 250),

    AMETHYST("Amethyst", 100),

    TOPAZ("Topaz", 80),

    JADE("Jade", 200),

    DIAMOND("Diamond", 750),

    PRISMATIC_SHARD("Prismatic_Shard", 2000),

    COPPER_ORE("Copper_Ore", 5),

    IRON_ORE("Iron_Ore", 10),

    GOLD_ORE("Gold_Ore", 25),

    IRIDIUM_ORE("Iridium_Ore", 100),

    COAL("Coal", 15);

    private String name;
    private int sellPrice;

    ForagingMineralType(String name, int sellPrice) {
        this.name = name;
        this.sellPrice = sellPrice;
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
    public String toString() {
        StringBuilder list =  new StringBuilder();
        list.append("Name: ").append(name).append("\n");
        list.append("SellPrice: ").append(getSellPrice()).append("\n");
        return list.toString();
    }
}
