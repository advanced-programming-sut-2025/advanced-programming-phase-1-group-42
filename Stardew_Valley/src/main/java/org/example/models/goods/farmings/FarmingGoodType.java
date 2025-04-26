package org.example.models.goods.farmings;

import org.example.models.enums.Season;
import org.example.models.goods.GoodType;

import java.util.ArrayList;
import java.util.Arrays;

public enum FarmingGoodType implements GoodType {

    // Spring Crops
    BLUE_JAZZ("Blue_Jazz", FarmingSeedType.JAZZ_SEEDS, true, 45, 50),
    CARROT("Carrot", FarmingSeedType.CARROT_SEEDS, true, 75, 35),
    CAULIFLOWER("Cauliflower", FarmingSeedType.CAULIFLOWER_SEEDS, true, 75, 175),
    COFFEE_BEAN("Coffee_Bean", FarmingSeedType.COFFEE_BEAN, false, 0, 15),
    GARLIC("Garlic", FarmingSeedType.GARLIC_SEEDS, true, 20, 60),
    GREEN_BEAN("Green_Bean", FarmingSeedType.BEAN_STARTER, true, 25, 40),
    KALE("Kale", FarmingSeedType.KALE_SEEDS, true, 50, 110),
    PARSNIP("Parsnip", FarmingSeedType.PARSNIP_SEEDS, true, 25, 35),
    POTATO("Potato", FarmingSeedType.POTATO_SEEDS, true, 25, 80),
    RHUBARB("Rhubarb", FarmingSeedType.RHUBARB_SEEDS, false, 0, 220),
    STRAWBERRY("Strawberry", FarmingSeedType.STRAWBERRY_SEEDS, true, 50, 120),
    TULIP("Tulip", FarmingSeedType.TULIP_BULB, true, 45, 30),
    UNMILLED_RICE("Unmilled_Rice", FarmingSeedType.RICE_SHOOT, true, 3, 30),

    // Summer Crops
    BLUEBERRY("Blueberry", FarmingSeedType.BLUEBERRY_SEEDS, true, 25, 50),
    CORN("Corn", FarmingSeedType.CORN_SEEDS, true, 25, 50),
    HOPS("Hops", FarmingSeedType.HOPS_STARTER, true, 45, 25),
    HOT_PEPPER("Hot_Pepper", FarmingSeedType.PEPPER_SEEDS, true, 13, 40),
    MELON("Melon", FarmingSeedType.MELON_SEEDS, true, 113, 250),
    POPPY("Poppy", FarmingSeedType.POPPY_SEEDS, true, 45, 140),
    RADISH("Radish", FarmingSeedType.RADISH_SEEDS, true, 45, 90),
    RED_CABBAGE("Red_Cabbage", FarmingSeedType.RED_CABBAGE_SEEDS, true, 75, 260),
    STARFRUIT("Starfruit", FarmingSeedType.STARFRUIT_SEEDS, true, 125, 750),
    SUMMER_SPANGLE("Summer_Spangle", FarmingSeedType.SPANGLE_SEEDS, true, 45, 90),
    SUMMER_SQUASH("Summer_Squash", FarmingSeedType.SUMMER_SQUASH_SEEDS, true, 63, 45),
    SUNFLOWER("Sunflower", FarmingSeedType.SUNFLOWER_SEEDS, true, 45, 80),
    TOMATO("Tomato", FarmingSeedType.TOMATO_SEEDS, true, 20, 60),
    WHEAT("Wheat", FarmingSeedType.WHEAT_SEEDS, false, 0, 25),

    // Fall Crops
    AMARANTH("Amaranth", FarmingSeedType.AMARANTH_SEEDS, true, 50, 150),
    ARTICHOKE("Artichoke", FarmingSeedType.ARTICHOKE_SEEDS, true, 30, 160),
    BEET("Beet", FarmingSeedType.BEET_SEEDS, true, 30, 100),
    BOK_CHOY("Bok_Choy", FarmingSeedType.BOK_CHOY_SEEDS, true, 25, 80),
    BROCCOLI("Broccoli", FarmingSeedType.BROCCOLI_SEEDS, true, 63, 70),
    CRANBERRIES("Cranberries", FarmingSeedType.CRANBERRY_SEEDS, true, 38, 75),
    EGGPLANT("Eggplant", FarmingSeedType.EGGPLANT_SEEDS, true, 20, 60),
    FAIRY_ROSE("Fairy_Rose", FarmingSeedType.FAIRY_SEEDS, true, 45, 290),
    GRAPE("Grape", FarmingSeedType.GRAPE_STARTER, true, 38, 80),
    PUMPKIN("Pumpkin", FarmingSeedType.PUMPKIN_SEEDS, false, 0, 320),
    YAM("Yam", FarmingSeedType.YAM_SEEDS, true, 45, 160),
    SWEET_GEM_BERRY("Sweet_Gem_Berry", FarmingSeedType.RARE_SEED, false, 0, 3000),

    // Winter Crop
    POWDERMELON("Powdermelon", FarmingSeedType.POWDERMELON_SEEDS, true, 63, 60),

    // Special Crop (Grows in all seasons except Winter)
    ANCIENT_FRUIT("Ancient_Fruit", FarmingSeedType.ANCIENT_SEEDS, false, 0, 550);

    //TODO
    //Giant Farming Goods do not forget to implement


    private String name;
    private FarmingSeedType seedType;
    private boolean isEdible;
    private int energy;
    private int baseSellPrice;

    FarmingGoodType(String name, FarmingSeedType seedType, boolean isEdible, int energy, int baseSellPrice) {
        this.name = name;
        this.seedType = seedType;
        this.isEdible = isEdible;
        this.energy = energy;
        this.baseSellPrice = baseSellPrice;
    }

    public String getName() {
        return name;
    }

    public FarmingSeedType getSeedType() {
        return seedType;
    }

    public boolean isEdible() {
        return isEdible;
    }

    @Override
    public int getSellPrice() {
            return 0;
    }

    public int getEnergy() {
        return energy;
    }

    public int getBaseSellPrice() {
        return baseSellPrice;
    }


}
