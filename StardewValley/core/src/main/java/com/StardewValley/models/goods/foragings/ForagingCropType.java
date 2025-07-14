package com.StardewValley.models.goods.foragings;

import com.StardewValley.models.enums.Season;
import com.StardewValley.models.goods.GoodType;

import java.util.ArrayList;
import java.util.Arrays;

public enum ForagingCropType implements GoodType {
    // Foraging Crops
    DAFFODIL("Daffodil", 0, 30,
            new ArrayList<>(Arrays.asList(Season.SPRING))),

    DANDELION("Dandelion", 25, 40,
            new ArrayList<>(Arrays.asList(Season.SPRING))),

    LEEK("Leek", 40, 60,
            new ArrayList<>(Arrays.asList(Season.SPRING))),

    MOREL("Morel", 20, 150,
            new ArrayList<>(Arrays.asList(Season.SPRING))),

    SALMONBERRY("Salmonberry", 25, 5,
            new ArrayList<>(Arrays.asList(Season.SPRING))),

    SPRING_ONION("Spring_Onion", 13, 8,
            new ArrayList<>(Arrays.asList(Season.SPRING))),

    WILD_HORSERADISH("Wild_Horseradish", 13, 50,
            new ArrayList<>(Arrays.asList(Season.SPRING))),

    FIDDLEHEAD_FERN("Fiddlehead_Fern", 25, 90,
            new ArrayList<>(Arrays.asList(Season.SUMMER))),

    GRAPE("Grape", 38, 80,
            new ArrayList<>(Arrays.asList(Season.SUMMER))),

    RED_MUSHROOM("Red_Mushroom", -50, 75,
            new ArrayList<>(Arrays.asList(Season.SUMMER))),

    SPICE_BERRY("Spice_Berry", 25, 80,
            new ArrayList<>(Arrays.asList(Season.SUMMER))),

    SWEET_PEA("Sweet_Pea", 0, 50,
            new ArrayList<>(Arrays.asList(Season.SUMMER))),

    BLACKBERRY("Blackberry", 25, 25,
            new ArrayList<>(Arrays.asList(Season.FALL))),

    CHANTERELLE("Chanterelle", 75, 160,
            new ArrayList<>(Arrays.asList(Season.FALL))),

    HAZELNUT("Hazelnut", 38, 40,
            new ArrayList<>(Arrays.asList(Season.FALL))),

    PURPLE_MUSHROOM("Purple_Mushroom", 30, 90,
            new ArrayList<>(Arrays.asList(Season.FALL))),

    WILD_PLUM("Wild_Plum", 25, 80,
            new ArrayList<>(Arrays.asList(Season.FALL))),

    CROCUS("Crocus", 0, 60,
            new ArrayList<>(Arrays.asList(Season.WINTER))),

    CRYSTAL_FRUIT("Crystal_Fruit", 63, 150,
            new ArrayList<>(Arrays.asList(Season.WINTER))),

    HOLLY("Holly", -37, 80,
            new ArrayList<>(Arrays.asList(Season.WINTER))),

    SNOW_YAM("Snow_Yam", 30, 100,
            new ArrayList<>(Arrays.asList(Season.WINTER))),

    WINTER_ROOT("Winter_Root", 25, 70,
            new ArrayList<>(Arrays.asList(Season.WINTER)));

    private String name;
    private int energy;
    private int baseSellPrice;
    private final ArrayList<Season> seasons;

    ForagingCropType(String name, int energy, int baseSellPrice, ArrayList<Season> seasons) {
        this.name = name;
        this.energy = energy;
        this.baseSellPrice = baseSellPrice;
        this.seasons = seasons;
    }

    @Override
    public int getSellPrice() {
        return 0;
    }

    @Override
    public int getEnergy() {
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        StringBuilder list =  new StringBuilder();
        list.append("Name: ").append(name).append("\n");
        list.append("Energy: ").append(energy).append("\n");
        list.append("BaseSellPrice: ").append(baseSellPrice).append("\n");
        list.append("Season: ");
        for (int i = 0; i < seasons.size(); i++) {
            list.append(seasons.get(i).getName());
            if(i != seasons.size() - 1)
                list.append(", ");
        }
        list.append("\n");

        return list.toString();
    }
}
