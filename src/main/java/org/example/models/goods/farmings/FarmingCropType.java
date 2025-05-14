package org.example.models.goods.farmings;

import org.example.models.goods.GoodType;
import org.example.models.goods.foragings.ForagingSeedType;
import org.example.models.goods.foragings.ForagingTreeType;

public enum FarmingCropType implements GoodType {

    // Spring Crops
    BLUE_JAZZ("Blue_Jazz", ForagingSeedType.JAZZ_SEEDS, true, 45, 50),
    CARROT("Carrot", ForagingSeedType.CARROT_SEEDS, true, 75, 35),
    CAULIFLOWER("Cauliflower", ForagingSeedType.CAULIFLOWER_SEEDS, true, 75, 175),
    COFFEE_BEAN("Coffee_Bean", ForagingSeedType.COFFEE_BEAN_SEEDS, false, 0, 15),
    GARLIC("Garlic", ForagingSeedType.GARLIC_SEEDS, true, 20, 60),
    GREEN_BEAN("Green_Bean", ForagingSeedType.BEAN_STARTER, true, 25, 40),
    KALE("Kale", ForagingSeedType.KALE_SEEDS, true, 50, 110),
    PARSNIP("Parsnip", ForagingSeedType.PARSNIP_SEEDS, true, 25, 35),
    POTATO("Potato", ForagingSeedType.POTATO_SEEDS, true, 25, 80),
    RHUBARB("Rhubarb", ForagingSeedType.RHUBARB_SEEDS, false, 0, 220),
    STRAWBERRY("Strawberry", ForagingSeedType.STRAWBERRY_SEEDS, true, 50, 120),
    TULIP("Tulip", ForagingSeedType.TULIP_BULB, true, 45, 30),
    UNMILLED_RICE("Unmilled_Rice", ForagingSeedType.RICE_SHOOT, true, 3, 30),

    // Summer Crops
    BLUEBERRY("Blueberry", ForagingSeedType.BLUEBERRY_SEEDS, true, 25, 50),
    CORN("Corn", ForagingSeedType.CORN_SEEDS, true, 25, 50),
    HOPS("Hops", ForagingSeedType.HOPS_STARTER, true, 45, 25),
    HOT_PEPPER("Hot_Pepper", ForagingSeedType.PEPPER_SEEDS, true, 13, 40),
    MELON("Melon", ForagingSeedType.MELON_SEEDS, true, 113, 250),
    POPPY("Poppy", ForagingSeedType.POPPY_SEEDS, true, 45, 140),
    RADISH("Radish", ForagingSeedType.RADISH_SEEDS, true, 45, 90),
    RED_CABBAGE("Red_Cabbage", ForagingSeedType.RED_CABBAGE_SEEDS, true, 75, 260),
    STARFRUIT("Starfruit", ForagingSeedType.STARFRUIT_SEEDS, true, 125, 750),
    SUMMER_SPANGLE("Summer_Spangle", ForagingSeedType.SPANGLE_SEEDS, true, 45, 90),
    SUMMER_SQUASH("Summer_Squash", ForagingSeedType.SUMMER_SQUASH_SEEDS, true, 63, 45),
    SUNFLOWER("Sunflower", ForagingSeedType.SUNFLOWER_SEEDS, true, 45, 80),
    TOMATO("Tomato", ForagingSeedType.TOMATO_SEEDS, true, 20, 60),
    WHEAT("Wheat", ForagingSeedType.WHEAT_SEEDS, false, 0, 25),

    // Fall Crops
    AMARANTH("Amaranth", ForagingSeedType.AMARANTH_SEEDS, true, 50, 150),
    ARTICHOKE("Artichoke", ForagingSeedType.ARTICHOKE_SEEDS, true, 30, 160),
    BEET("Beet", ForagingSeedType.BEET_SEEDS, true, 30, 100),
    BOK_CHOY("Bok_Choy", ForagingSeedType.BOK_CHOY_SEEDS, true, 25, 80),
    BROCCOLI("Broccoli", ForagingSeedType.BROCCOLI_SEEDS, true, 63, 70),
    CRANBERRY("Cranberries", ForagingSeedType.CRANBERRY_SEEDS, true, 38, 75),
    EGGPLANT("Eggplant", ForagingSeedType.EGGPLANT_SEEDS, true, 20, 60),
    FAIRY_ROSE("Fairy_Rose", ForagingSeedType.FAIRY_SEEDS, true, 45, 290),
    GRAPE("Grape", ForagingSeedType.GRAPE_STARTER, true, 38, 80),
    PUMPKIN("Pumpkin", ForagingSeedType.PUMPKIN_SEEDS, false, 0, 320),
    YAM("Yam", ForagingSeedType.YAM_SEEDS, true, 45, 160),
    SWEET_GEM_BERRY("Sweet_Gem_Berry", ForagingSeedType.RARE_SEED, false, 0, 3000),

    // Winter Crop
    POWDERMELON("PowderMelon", ForagingSeedType.POWDERMELON_SEEDS, true, 63, 60),

    // Special Crop (Grows in all seasons except Winter)
    ANCIENT_FRUIT("Ancient_Fruit", ForagingSeedType.ANCIENT_SEEDS, false, 0, 550),

    //TODO
    //Giant Farming Goods do not forget to implement

    //Trees Crop Not eatable
    OAK_RESIN("Oak_Resin", ForagingTreeType.ACORNS, false, -1, 150),

    MAPLE_SYRUP("Maple_Syrup", ForagingTreeType.MAPLE_SEEDS, false, -1, 200),

    PINE_TAR("Pine_Tar", ForagingTreeType.PINE_CONES, false, -1, 100),

    ANY("Any Crop",null,false,-1,-1);


    private String name;
    private GoodType foragingType;
    private boolean isEdible;
    private int energy;
    private int baseSellPrice;
    private

    FarmingCropType(String name, GoodType foragingType, boolean isEdible, int energy, int baseSellPrice) {
        this.name = name;
        this.foragingType = foragingType;
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
        list.append(getSeedType().toString());
        list.append("Is Edible: ").append(isEdible).append("\n");
        list.append("Base Energy: ").append(energy).append("\n");
        list.append("Base Sell Price: ").append(getBaseSellPrice()).append("\n");

        return list.toString();
    }
}
