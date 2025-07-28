package com.StardewValley.models.goods.farmings;

import com.StardewValley.models.goods.GoodType;

public enum FarmingCropType implements GoodType {

    // Spring Crops
    BLUE_JAZZ("Blue_Jazz", true, 45, 50, "GameAssets/Crops/Blue_Jazz.png"),
    CARROT("Carrot", true, 75, 35, "GameAssets/Crops/Carrot.png"),
    CAULIFLOWER("Cauliflower", true, 75, 175, "GameAssets/Crops/Cauliflower.png"),
    COFFEE_BEAN("Coffee_Bean", false, 0, 15, "GameAssets/Crops/Coffee_Bean.png"),
    GARLIC("Garlic", true, 20, 60, "GameAssets/Crops/Garlic.png"),
    GREEN_BEAN("Green_Bean", true, 25, 40, "GameAssets/Crops/Green_Bean.png"),
    KALE("Kale", true, 50, 110, "GameAssets/Crops/Kale.png"),
    PARSNIP("Parsnip", true, 25, 35, "GameAssets/Crops/Parsnip.png"),
    POTATO("Potato", true, 25, 80, "GameAssets/Crops/Potato.png"),
    RHUBARB("Rhubarb", false, 0, 220, "GameAssets/Crops/Rhubarb.png"),
    STRAWBERRY("Strawberry", true, 50, 120, "GameAssets/Crops/Strawberry.png"),
    TULIP("Tulip", true, 45, 30, "GameAssets/Crops/Tulip.png"),
    UNMILLED_RICE("Unmilled_Rice", true, 3, 30, "GameAssets/Crops/Unmilled_Rice.png"),

    // Summer Crops
    BLUEBERRY("Blueberry", true, 25, 50, "GameAssets/Crops/Blueberry.png"),
    CORN("Corn", true, 25, 50, "GameAssets/Crops/Corn.png"),
    HOPS("Hops", true, 45, 25, "GameAssets/Crops/Hops.png"),
    HOT_PEPPER("Hot_Pepper", true, 13, 40, "GameAssets/Crops/Hot_Pepper.png"),
    MELON("Melon", true, 113, 250, "GameAssets/Crops/Melon.png"),
    POPPY("Poppy", true, 45, 140, "GameAssets/Crops/Poppy.png"),
    RADISH("Radish", true, 45, 90, "GameAssets/Crops/Radish.png"),
    RED_CABBAGE("Red_Cabbage", true, 75, 260, "GameAssets/Crops/Red_Cabbage.png"),
    STARFRUIT("Starfruit", true, 125, 750, "GameAssets/Crops/Starfruit.png"),
    SUMMER_SPANGLE("Summer_Spangle", true, 45, 90, "GameAssets/Crops/Summer_Spangle.png"),
    SUMMER_SQUASH("Summer_Squash", true, 63, 45, "GameAssets/Crops/Summer_Squash.png"),
    SUNFLOWER("Sunflower", true, 45, 80, "GameAssets/Crops/Sunflower.png"),
    TOMATO("Tomato", true, 20, 60, "GameAssets/Crops/Tomato.png"),
    WHEAT("Wheat", false, 0, 25, "GameAssets/Crops/Wheat.png"),

    // Fall Crops
    AMARANTH("Amaranth", true, 50, 150, "GameAssets/Crops/Amaranth.png"),
    ARTICHOKE("Artichoke", true, 30, 160, "GameAssets/Crops/Artichoke.png"),
    BEET("Beet", true, 30, 100, "GameAssets/Crops/Beet.png"),
    BOK_CHOY("Bok_Choy", true, 25, 80, "GameAssets/Crops/Bok_Choy.png"),
    BROCCOLI("Broccoli", true, 63, 70, "GameAssets/Crops/Broccoli.png"),
    CRANBERRY("Cranberries", true, 38, 75, "GameAssets/Crops/Cranberries.png"),
    EGGPLANT("Eggplant", true, 20, 60, "GameAssets/Crops/Eggplant.png"),
    FAIRY_ROSE("Fairy_Rose", true, 45, 290, "GameAssets/Crops/Fairy_Rose.png"),
    GRAPE("Grape", true, 38, 80, "GameAssets/Crops/Grape.png"),
    PUMPKIN("Pumpkin", false, 0, 320, "GameAssets/Crops/Pumpkin.png"),
    YAM("Yam", true, 45, 160, "GameAssets/Crops/Yam.png"),
    SWEET_GEM_BERRY("Sweet_Gem_Berry", false, 0, 3000, "GameAssets/Crops/Sweet_Gem_Berry.png"),

    // Winter
    POWDERMELON("PowderMelon", true, 63, 60, "GameAssets/Crops/PowderMelon.png"),

    // Special
    ANCIENT_FRUIT("Ancient_Fruit", false, 0, 550, "GameAssets/Crops/Ancient_Fruit.png"),

    // Tree Crops (not edible)
    OAK_RESIN("Oak_Resin", false, -1, 150, "GameAssets/Trees/Oak_Resin.png"),
    MAPLE_SYRUP("Maple_Syrup", false, -1, 200, "GameAssets/Trees/Maple_Syrup.png"),
    PINE_TAR("Pine_Tar", false, -1, 100, "GameAssets/Trees/Pine_Tar.png"),

    // Utility
    ANY("Any Crop", false, -1, -1, "");

    private final String name;
    private final boolean isEdible;
    private final int energy;
    private final int baseSellPrice;
    private final String imagePath;

    FarmingCropType(String name, boolean isEdible, int energy, int baseSellPrice, String imagePath) {
        this.name = name;
        this.isEdible = isEdible;
        this.energy = energy;
        this.baseSellPrice = baseSellPrice;
        this.imagePath = imagePath;
    }

    @Override
    public String getName() {
        return name;
    }

    public boolean isEdible() {
        return isEdible;
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    @Override
    public int getSellPrice() {
        return baseSellPrice;
    }

    @Override
    public String imagePath() {
        return imagePath;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
            "Energy: " + energy + "\n" +
            "Is Edible: " + isEdible + "\n" +
            "Base Sell Price: " + baseSellPrice + "\n";
    }
}
