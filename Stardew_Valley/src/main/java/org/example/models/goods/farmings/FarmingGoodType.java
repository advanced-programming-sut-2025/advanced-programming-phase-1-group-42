package org.example.models.goods.farmings;

import org.example.models.enums.Season;
import org.example.models.goods.GoodType;
import org.example.models.goods.foragings.SeedType;

import java.util.ArrayList;
import java.util.Arrays;

public enum FarmingGoodType implements GoodType {

    // Spring Crops
    BLUE_JAZZ("Blue_Jazz", SeedType.JAZZ_SEEDS, true, 45, 50),
    CARROT("Carrot", SeedType.CARROT_SEEDS, true, 75, 35),
    CAULIFLOWER("Cauliflower", SeedType.CAULIFLOWER_SEEDS, true, 75, 175),
    COFFEE_BEAN("Coffee_Bean", SeedType.COFFEE_BEAN, false, 0, 15),
    GARLIC("Garlic", SeedType.GARLIC_SEEDS, true, 20, 60),
    GREEN_BEAN("Green_Bean", SeedType.BEAN_STARTER, true, 25, 40),
    KALE("Kale", SeedType.KALE_SEEDS, true, 50, 110),
    PARSNIP("Parsnip", SeedType.PARSNIP_SEEDS, true, 25, 35),
    POTATO("Potato", SeedType.POTATO_SEEDS, true, 25, 80),
    RHUBARB("Rhubarb", SeedType.RHUBARB_SEEDS, false, 0, 220),
    STRAWBERRY("Strawberry", SeedType.STRAWBERRY_SEEDS, true, 50, 120),
    TULIP("Tulip", SeedType.TULIP_BULB, true, 45, 30),
    UNMILLED_RICE("Unmilled_Rice", SeedType.RICE_SHOOT, true, 3, 30),

    // Summer Crops
    BLUEBERRY("Blueberry", SeedType.BLUEBERRY_SEEDS, true, 25, 50),
    CORN("Corn", SeedType.CORN_SEEDS, true, 25, 50),
    HOPS("Hops", SeedType.HOPS_STARTER, true, 45, 25),
    HOT_PEPPER("Hot_Pepper", SeedType.PEPPER_SEEDS, true, 13, 40),
    MELON("Melon", SeedType.MELON_SEEDS, true, 113, 250),
    POPPY("Poppy", SeedType.POPPY_SEEDS, true, 45, 140),
    RADISH("Radish", SeedType.RADISH_SEEDS, true, 45, 90),
    RED_CABBAGE("Red_Cabbage", SeedType.RED_CABBAGE_SEEDS, true, 75, 260),
    STARFRUIT("Starfruit", SeedType.STARFRUIT_SEEDS, true, 125, 750),
    SUMMER_SPANGLE("Summer_Spangle", SeedType.SPANGLE_SEEDS, true, 45, 90),
    SUMMER_SQUASH("Summer_Squash", SeedType.SUMMER_SQUASH_SEEDS, true, 63, 45),
    SUNFLOWER("Sunflower", SeedType.SUNFLOWER_SEEDS, true, 45, 80),
    TOMATO("Tomato", SeedType.TOMATO_SEEDS, true, 20, 60),
    WHEAT("Wheat", SeedType.WHEAT_SEEDS, false, 0, 25),

    // Fall Crops
    AMARANTH("Amaranth", SeedType.AMARANTH_SEEDS, true, 50, 150),
    ARTICHOKE("Artichoke", SeedType.ARTICHOKE_SEEDS, true, 30, 160),
    BEET("Beet", SeedType.BEET_SEEDS, true, 30, 100),
    BOK_CHOY("Bok_Choy", SeedType.BOK_CHOY_SEEDS, true, 25, 80),
    BROCCOLI("Broccoli", SeedType.BROCCOLI_SEEDS, true, 63, 70),
    CRANBERRIES("Cranberries", SeedType.CRANBERRY_SEEDS, true, 38, 75),
    EGGPLANT("Eggplant", SeedType.EGGPLANT_SEEDS, true, 20, 60),
    FAIRY_ROSE("Fairy_Rose", SeedType.FAIRY_SEEDS, true, 45, 290),
    GRAPE("Grape", SeedType.GRAPE_STARTER, true, 38, 80),
    PUMPKIN("Pumpkin", SeedType.PUMPKIN_SEEDS, false, 0, 320),
    YAM("Yam", SeedType.YAM_SEEDS, true, 45, 160),
    SWEET_GEM_BERRY("Sweet_Gem_Berry", SeedType.RARE_SEED, false, 0, 3000),

    // Winter Crop
    POWDERMELON("Powdermelon", SeedType.POWDERMELON_SEEDS, true, 63, 60),

    // Special Crop (Grows in all seasons except Winter)
    ANCIENT_FRUIT("Ancient_Fruit", SeedType.ANCIENT_SEEDS, false, 0, 550);

    //TODO
    //Giant Farming Goods do not forget to implement


    private String name;
    private SeedType seedType;
    private boolean isEdible;
    private int energy;
    private int baseSellPrice;

    FarmingGoodType(String name, SeedType seedType, boolean isEdible, int energy, int baseSellPrice) {
        this.name = name;
        this.seedType = seedType;
        this.isEdible = isEdible;
        this.energy = energy;
        this.baseSellPrice = baseSellPrice;
    }

    public String getName() {
        return name;
    }

    public SeedType getSeedType() {
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
