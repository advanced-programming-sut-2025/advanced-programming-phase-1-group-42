package com.StardewValley.models.goods.farmings;

import com.StardewValley.models.goods.GoodType;

public enum FarmingCropType implements GoodType {

    // Spring Crops
    BLUE_JAZZ("Blue_Jazz", true, 45, 50, "/assets/GameAssets/Crops/Blue_Jazz.png"),
    CARROT("Carrot", true, 75, 35, "/assets/GameAssets/Crops/Carrot.png"),
    CAULIFLOWER("Cauliflower", true, 75, 175, "/assets/GameAssets/Crops/Cauliflower.png"),
    COFFEE_BEAN("Coffee_Bean", false, 0, 15, "/assets/GameAssets/Crops/Coffee_Bean.png"),
    GARLIC("Garlic", true, 20, 60, "/assets/GameAssets/Crops/Garlic.png"),
    GREEN_BEAN("Green_Bean", true, 25, 40, "/assets/GameAssets/Crops/Green_Bean.png"),
    KALE("Kale", true, 50, 110, "/assets/GameAssets/Crops/Kale.png"),
    PARSNIP("Parsnip", true, 25, 35, "/assets/GameAssets/Crops/Parsnip.png"),
    POTATO("Potato", true, 25, 80, "/assets/GameAssets/Crops/Potato.png"),
    RHUBARB("Rhubarb", false, 0, 220, "/assets/GameAssets/Crops/Rhubarb.png"),
    STRAWBERRY("Strawberry", true, 50, 120, "/assets/GameAssets/Crops/Strawberry.png"),
    TULIP("Tulip", true, 45, 30, "/assets/GameAssets/Crops/Tulip.png"),
    UNMILLED_RICE("Unmilled_Rice", true, 3, 30, "/assets/GameAssets/Crops/Unmilled_Rice.png"),

    // Summer Crops
    BLUEBERRY("Blueberry", true, 25, 50, "/assets/GameAssets/Crops/Blueberry.png"),
    CORN("Corn", true, 25, 50, "/assets/GameAssets/Crops/Corn.png"),
    HOPS("Hops", true, 45, 25, "/assets/GameAssets/Crops/Hops.png"),
    HOT_PEPPER("Hot_Pepper", true, 13, 40, "/assets/GameAssets/Crops/Hot_Pepper.png"),
    MELON("Melon", true, 113, 250, "/assets/GameAssets/Crops/Melon.png"),
    POPPY("Poppy", true, 45, 140, "/assets/GameAssets/Crops/Poppy.png"),
    RADISH("Radish", true, 45, 90, "/assets/GameAssets/Crops/Radish.png"),
    RED_CABBAGE("Red_Cabbage", true, 75, 260, "/assets/GameAssets/Crops/Red_Cabbage.png"),
    STARFRUIT("Starfruit", true, 125, 750, "/assets/GameAssets/Crops/Starfruit.png"),
    SUMMER_SPANGLE("Summer_Spangle", true, 45, 90, "/assets/GameAssets/Crops/Summer_Spangle.png"),
    SUMMER_SQUASH("Summer_Squash", true, 63, 45, "/assets/GameAssets/Crops/Summer_Squash.png"),
    SUNFLOWER("Sunflower", true, 45, 80, "/assets/GameAssets/Crops/Sunflower.png"),
    TOMATO("Tomato", true, 20, 60, "/assets/GameAssets/Crops/Tomato.png"),
    WHEAT("Wheat", false, 0, 25, "/assets/GameAssets/Crops/Wheat.png"),

    // Fall Crops
    AMARANTH("Amaranth", true, 50, 150, "/assets/GameAssets/Crops/Amaranth.png"),
    ARTICHOKE("Artichoke", true, 30, 160, "/assets/GameAssets/Crops/Artichoke.png"),
    BEET("Beet", true, 30, 100, "/assets/GameAssets/Crops/Beet.png"),
    BOK_CHOY("Bok_Choy", true, 25, 80, "/assets/GameAssets/Crops/Bok_Choy.png"),
    BROCCOLI("Broccoli", true, 63, 70, "/assets/GameAssets/Crops/Broccoli.png"),
    CRANBERRY("Cranberries", true, 38, 75, "/assets/GameAssets/Crops/Cranberries.png"),
    EGGPLANT("Eggplant", true, 20, 60, "/assets/GameAssets/Crops/Eggplant.png"),
    FAIRY_ROSE("Fairy_Rose", true, 45, 290, "/assets/GameAssets/Crops/Fairy_Rose.png"),
    GRAPE("Grape", true, 38, 80, "/assets/GameAssets/Crops/Grape.png"),
    PUMPKIN("Pumpkin", false, 0, 320, "/assets/GameAssets/Crops/Pumpkin.png"),
    YAM("Yam", true, 45, 160, "/assets/GameAssets/Crops/Yam.png"),
    SWEET_GEM_BERRY("Sweet_Gem_Berry", false, 0, 3000, "/assets/GameAssets/Crops/Sweet_Gem_Berry.png"),

    // Winter
    POWDERMELON("PowderMelon", true, 63, 60, "/assets/GameAssets/Crops/PowderMelon.png"),

    // Special
    ANCIENT_FRUIT("Ancient_Fruit", false, 0, 550, "/assets/GameAssets/Crops/Ancient_Fruit.png"),

    // Tree Crops (not edible)
    OAK_RESIN("Oak_Resin", false, -1, 150, "/assets/GameAssets/Crops/Oak_Resin.png"),
    MAPLE_SYRUP("Maple_Syrup", false, -1, 200, "/assets/GameAssets/Crops/Maple_Syrup.png"),
    PINE_TAR("Pine_Tar", false, -1, 100, "/assets/GameAssets/Crops/Pine_Tar.png"),

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
