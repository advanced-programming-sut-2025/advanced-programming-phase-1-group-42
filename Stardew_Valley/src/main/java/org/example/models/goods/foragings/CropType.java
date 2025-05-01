package org.example.models.goods.foragings;

import org.example.models.enums.Season;
import org.example.models.game_structure.Buff;
import org.example.models.goods.GoodType;

import java.util.ArrayList;
import java.util.Arrays;

public enum CropType implements GoodType {
    WOOD("Wood", 0, 10,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))),

    STONE("Stone", 0, 20,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))),

    //Trees Crop Not eatable
    OAK_RESIN("Oak_Resin", 0, 150,
                      new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))),
    MAPLE_SYRUP("Maple_Syrup", 0, 200,
                        new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))),
    PINE_TAR("Pine_Tar", 0, 100,
                     new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))),

    // Foraging Crops
    COMMON_MUSHROOM("Common_Mushroom", 38, 40,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))),

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

    private final String name;
    private final int energy;
    private final int sellPrice;
    private final ArrayList<Season> seasons;

    CropType(String name, int energy, int sellPrice, ArrayList<Season> seasons) {
        this.name = name;
        this.energy = energy;
        this.sellPrice = sellPrice;
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
        return "";
    }
}
