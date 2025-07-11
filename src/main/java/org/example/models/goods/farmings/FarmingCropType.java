package org.example.models.goods.farmings;

import org.example.models.goods.GoodType;
import org.example.models.goods.foragings.ForagingSeedType;
import org.example.models.goods.foragings.ForagingTreeType;

public enum FarmingCropType implements GoodType {

    // Spring Crops
    BLUE_JAZZ("Blue_Jazz", true, 45, 50),
    CARROT("Carrot", true, 75, 35),
    CAULIFLOWER("Cauliflower", true, 75, 175),
    COFFEE_BEAN("Coffee_Bean", false, 0, 15),
    GARLIC("Garlic", true, 20, 60),
    GREEN_BEAN("Green_Bean", true, 25, 40),
    KALE("Kale", true, 50, 110),
    PARSNIP("Parsnip", true, 25, 35),
    POTATO("Potato", true, 25, 80),
    RHUBARB("Rhubarb", false, 0, 220),
    STRAWBERRY("Strawberry", true, 50, 120),
    TULIP("Tulip", true, 45, 30),
    UNMILLED_RICE("Unmilled_Rice", true, 3, 30),

    // Summer Crops
    BLUEBERRY("Blueberry", true, 25, 50),
    CORN("Corn", true, 25, 50),
    HOPS("Hops", true, 45, 25),
    HOT_PEPPER("Hot_Pepper", true, 13, 40),
    MELON("Melon", true, 113, 250),
    POPPY("Poppy", true, 45, 140),
    RADISH("Radish", true, 45, 90),
    RED_CABBAGE("Red_Cabbage", true, 75, 260),
    STARFRUIT("Starfruit", true, 125, 750),
    SUMMER_SPANGLE("Summer_Spangle", true, 45, 90),
    SUMMER_SQUASH("Summer_Squash", true, 63, 45),
    SUNFLOWER("Sunflower", true, 45, 80),
    TOMATO("Tomato", true, 20, 60),
    WHEAT("Wheat", false, 0, 25),

    // Fall Crops
    AMARANTH("Amaranth", true, 50, 150),
    ARTICHOKE("Artichoke", true, 30, 160),
    BEET("Beet", true, 30, 100),
    BOK_CHOY("Bok_Choy", true, 25, 80),
    BROCCOLI("Broccoli", true, 63, 70),
    CRANBERRY("Cranberries", true, 38, 75),
    EGGPLANT("Eggplant", true, 20, 60),
    FAIRY_ROSE("Fairy_Rose", true, 45, 290),
    GRAPE("Grape", true, 38, 80),
    PUMPKIN("Pumpkin", false, 0, 320),
    YAM("Yam", true, 45, 160),
    SWEET_GEM_BERRY("Sweet_Gem_Berry", false, 0, 3000),

    // Winter Crop
    POWDERMELON("PowderMelon", true, 63, 60),

    // Special Crop (Grows in all seasons except Winter)
    ANCIENT_FRUIT("Ancient_Fruit", false, 0, 550),

    //Giant Farming Goods do not forget to implement

    //Trees Crop Not eatable
    OAK_RESIN("Oak_Resin", false, -1, 150),

    MAPLE_SYRUP("Maple_Syrup", false, -1, 200),

    PINE_TAR("Pine_Tar", false, -1, 100),

    ANY("Any Crop",false,-1,-1);


    private String name;
    private GoodType foragingType;
    private boolean isEdible;
    private int energy;
    private int baseSellPrice;
    private

    FarmingCropType(String name, boolean isEdible, int energy, int baseSellPrice) {
        this.name = name;
        this.isEdible = isEdible;
        this.energy = energy;
        this.baseSellPrice = baseSellPrice;
    }

    public String getName() {
        return this.name;
    }

    public GoodType getSeedType() {
        return foragingType;
    }

    public boolean isEdible() {
        return isEdible;
    }

    @Override
    public int getSellPrice() {
            return baseSellPrice;
    }

    public int getEnergy() {
        return energy;
    }

    public int getBaseSellPrice() {
        return baseSellPrice;
    }


    @Override
    public String toString() {
        StringBuilder list = new StringBuilder();
        list.append("Name: ").append(name).append("\n");
        list.append("Source: ").append(foragingType.getName()).append("\n");
        list.append("Energy: ").append(energy).append("\n");
        list.append("Is Edible: ").append(isEdible).append("\n");
        list.append("Base Energy: ").append(energy).append("\n");
        list.append("Base Sell Price: ").append(getBaseSellPrice()).append("\n");

        return list.toString();
    }
}
