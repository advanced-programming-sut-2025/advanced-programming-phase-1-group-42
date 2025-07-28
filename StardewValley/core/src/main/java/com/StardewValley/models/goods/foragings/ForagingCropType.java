package com.StardewValley.models.goods.foragings;

import com.StardewValley.models.enums.Season;
import com.StardewValley.models.goods.GoodType;

import java.util.ArrayList;
import java.util.Arrays;

public enum ForagingCropType implements GoodType {
    // Foraging Crops
    DAFFODIL("Daffodil", 0, 30,
        new ArrayList<>(Arrays.asList(Season.SPRING)), "GameAssets/Foraging/Daffodil.png"),

    DANDELION("Dandelion", 25, 40,
        new ArrayList<>(Arrays.asList(Season.SPRING)), "GameAssets/Foraging/Dandelion.png"),

    LEEK("Leek", 40, 60,
        new ArrayList<>(Arrays.asList(Season.SPRING)), "GameAssets/Foraging/Leek.png"),

    MOREL("Morel", 20, 150,
        new ArrayList<>(Arrays.asList(Season.SPRING)), "GameAssets/Foraging/Morel.png"),

    SALMONBERRY("Salmonberry", 25, 5,
        new ArrayList<>(Arrays.asList(Season.SPRING)), "GameAssets/Foraging/Salmonberry.png"),

    SPRING_ONION("Spring_Onion", 13, 8,
        new ArrayList<>(Arrays.asList(Season.SPRING)), "GameAssets/Foraging/Spring_Onion.png"),

    WILD_HORSERADISH("Wild_Horseradish", 13, 50,
        new ArrayList<>(Arrays.asList(Season.SPRING)), "GameAssets/Foraging/Wild_Horseradish.png"),

    FIDDLEHEAD_FERN("Fiddlehead_Fern", 25, 90,
        new ArrayList<>(Arrays.asList(Season.SUMMER)), "GameAssets/Foraging/Fiddlehead_Fern.png"),

    GRAPE("Grape", 38, 80,
        new ArrayList<>(Arrays.asList(Season.SUMMER)), "GameAssets/Foraging/Grape.png"),

    RED_MUSHROOM("Red_Mushroom", -50, 75,
        new ArrayList<>(Arrays.asList(Season.SUMMER)), "GameAssets/Foraging/Red_Mushroom.png"),

    SPICE_BERRY("Spice_Berry", 25, 80,
        new ArrayList<>(Arrays.asList(Season.SUMMER)), "GameAssets/Foraging/Spice_Berry.png"),

    SWEET_PEA("Sweet_Pea", 0, 50,
        new ArrayList<>(Arrays.asList(Season.SUMMER)), "GameAssets/Foraging/Sweet_Pea.png"),

    BLACKBERRY("Blackberry", 25, 25,
        new ArrayList<>(Arrays.asList(Season.FALL)), "GameAssets/Foraging/Blackberry.png"),

    CHANTERELLE("Chanterelle", 75, 160,
        new ArrayList<>(Arrays.asList(Season.FALL)), "GameAssets/Foraging/Chanterelle.png"),

    HAZELNUT("Hazelnut", 38, 40,
        new ArrayList<>(Arrays.asList(Season.FALL)), "GameAssets/Foraging/Hazelnut.png"),

    PURPLE_MUSHROOM("Purple_Mushroom", 30, 90,
        new ArrayList<>(Arrays.asList(Season.FALL)), "GameAssets/Foraging/Purple_Mushroom.png"),

    WILD_PLUM("Wild_Plum", 25, 80,
        new ArrayList<>(Arrays.asList(Season.FALL)), "GameAssets/Foraging/Wild_Plum.png"),

    CROCUS("Crocus", 0, 60,
        new ArrayList<>(Arrays.asList(Season.WINTER)), "GameAssets/Foraging/Crocus.png"),

    CRYSTAL_FRUIT("Crystal_Fruit", 63, 150,
        new ArrayList<>(Arrays.asList(Season.WINTER)), "GameAssets/Foraging/Crystal_Fruit.png"),

    HOLLY("Holly", -37, 80,
        new ArrayList<>(Arrays.asList(Season.WINTER)), "GameAssets/Foraging/Holly.png"),

    SNOW_YAM("Snow_Yam", 30, 100,
        new ArrayList<>(Arrays.asList(Season.WINTER)), "GameAssets/Foraging/Snow_Yam.png"),

    WINTER_ROOT("Winter_Root", 25, 70,
        new ArrayList<>(Arrays.asList(Season.WINTER)), "GameAssets/Foraging/Winter_Root.png");

    private String name;
    private int energy;
    private int baseSellPrice;
    private final ArrayList<Season> seasons;
    private final String imagePath;

    ForagingCropType(String name, int energy, int baseSellPrice, ArrayList<Season> seasons , String imagePath) {
        this.name = name;
        this.energy = energy;
        this.baseSellPrice = baseSellPrice;
        this.seasons = seasons;
        this.imagePath = imagePath;
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
    public String imagePath() {
        return imagePath;
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
