package org.example.models.goods.foragings;

import org.example.models.enums.Season;
import org.example.models.game_structure.Buff;
import org.example.models.goods.GoodType;

import java.util.ArrayList;
import java.util.Arrays;

public enum CropType implements GoodType {
    WOOD("Wood", 0, 0, null,
            new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))),

    OAK_RESIN("Oak_Resin", 0, 150, null,
                      new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))),
    MAPLE_SYRUP("Maple_Syrup", 0, 200, null,
                        new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER))),
    PINE_TAR("Pine_Tar", 0, 100, null,
                     new ArrayList<>(Arrays.asList(Season.SPRING, Season.SUMMER, Season.FALL, Season.WINTER)));

    private final String name;
    private final int energy;
    private final int sellPrice;
    private final Buff buff;
    private final ArrayList<Season> seasons;

    CropType(String name, int energy, int sellPrice, Buff buff, ArrayList<Season> seasons) {
        this.name = name;
        this.energy = energy;
        this.sellPrice = sellPrice;
        this.buff = buff;
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
}
