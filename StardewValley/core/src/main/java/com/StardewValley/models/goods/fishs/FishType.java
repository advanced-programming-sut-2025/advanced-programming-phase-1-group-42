package com.StardewValley.models.goods.fishs;

import com.StardewValley.models.enums.Season;
import com.StardewValley.models.goods.GoodType;

public enum FishType implements GoodType {
    // Regular Fish

    ANONYMOUS("Anonymous_Fish", -1, Season.SPRING),
    //Spring
    FLOUNDER("Flounder", 100, Season.SPRING),
    LIONFISH("Lionfish", 100, Season.SPRING),
    HERRING("Herring", 30, Season.SPRING),
    GHOSTFISH("Ghostfish", 45, Season.SPRING),

    //Summer
    TILAPIA("Tilapia", 75, Season.SUMMER),
    DORADO("Dorado", 100, Season.SUMMER),
    SUNFISH("Sunfish", 30, Season.SUMMER),
    RAINBOW_TROUT("Rainbow_Trout", 65, Season.SUMMER),

    //Fall
    SALMON("Salmon", 75, Season.FALL),
    SARDINE("Sardine", 40, Season.FALL),
    SHAD("Shad", 60, Season.FALL),
    BLUE_DISCUS("Blue_Discus", 120, Season.FALL),

    //Winter
    MIDNIGHT_CARP("Midnight_Carp", 150, Season.WINTER),
    SQUID("Squid", 80, Season.WINTER),
    TUNA("Tuna", 100, Season.WINTER),
    PERCH("Perch", 55, Season.WINTER),


    // Legendary Fish
    LEGEND("Legend", 5000, Season.SPRING),
    CRIMSONFISH("Crimsonfish", 1500, Season.SUMMER),
    ANGLER("Angler", 900, Season.FALL),
    GLACIERFISH("Glacierfish", 1000, Season.WINTER);

    //Good Level ?!
    private String name;
    private int price;
    private Season season;

    FishType(String name, int price, Season season) {
        this.name = name;
        this.price = price;
        this.season = season;
    }


    @Override
    public int getSellPrice() {
        return price;
    }

    @Override
    public int getEnergy() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public Season getSeason() {
        return season;
    }
}
