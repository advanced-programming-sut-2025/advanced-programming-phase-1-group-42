package com.StardewValley.models.goods.fishs;

import com.StardewValley.models.enums.Season;
import com.StardewValley.models.goods.GoodType;

public enum FishType implements GoodType {
    ANONYMOUS("Anonymous_Fish", -1, Season.SPRING, "/assets/GameAssets/Fish/Anonymous_Fish.png"),
    //Spring
    FLOUNDER("Flounder", 100, Season.SPRING, "/assets/GameAssets/Fish/Flounder.png"),
    LIONFISH("Lionfish", 100, Season.SPRING, "/assets/GameAssets/Fish/Lionfish.png"),
    HERRING("Herring", 30, Season.SPRING, "/assets/GameAssets/Fish/Herring.png"),
    GHOSTFISH("Ghostfish", 45, Season.SPRING, "/assets/GameAssets/Fish/Ghostfish.png"),

    //Summer
    TILAPIA("Tilapia", 75, Season.SUMMER, "/assets/GameAssets/Fish/Tilapia.png"),
    DORADO("Dorado", 100, Season.SUMMER, "/assets/GameAssets/Fish/Dorado.png"),
    SUNFISH("Sunfish", 30, Season.SUMMER, "/assets/GameAssets/Fish/Sunfish.png"),
    RAINBOW_TROUT("Rainbow_Trout", 65, Season.SUMMER, "/assets/GameAssets/Fish/Rainbow_Trout.png"),

    //Fall
    SALMON("Salmon", 75, Season.FALL, "/assets/GameAssets/Fish/Salmon.png"),
    SARDINE("Sardine", 40, Season.FALL, "/assets/GameAssets/Fish/Sardine.png"),
    SHAD("Shad", 60, Season.FALL, "/assets/GameAssets/Fish/Shad.png"),
    BLUE_DISCUS("Blue_Discus", 120, Season.FALL, "/assets/GameAssets/Fish/Blue_Discus.png"),

    //Winter
    MIDNIGHT_CARP("Midnight_Carp", 150, Season.WINTER, "/assets/GameAssets/Fish/Midnight_Carp.png"),
    SQUID("Squid", 80, Season.WINTER, "/assets/GameAssets/Fish/Squid.png"),
    TUNA("Tuna", 100, Season.WINTER, "/assets/GameAssets/Fish/Tuna.png"),
    PERCH("Perch", 55, Season.WINTER, "/assets/GameAssets/Fish/Perch.png"),

    // Legendary Fish
    LEGEND("Legend", 5000, Season.SPRING, "/assets/GameAssets/Fish/Legend.png"),
    CRIMSONFISH("Crimsonfish", 1500, Season.SUMMER, "/assets/GameAssets/Fish/Crimsonfish.png"),
    ANGLER("Angler", 900, Season.FALL, "/assets/GameAssets/Fish/Angler.png"),
    GLACIERFISH("Glacierfish", 1000, Season.WINTER, "/assets/GameAssets/Fish/Glacierfish.png");


    //Good Level ?!
    private String name;
    private int price;
    private Season season;
    private String imagePath;

    FishType(String name, int price, Season season , String imagePath) {
        this.name = name;
        this.price = price;
        this.season = season;
        this.imagePath= imagePath;
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

    @Override
    public String imagePath() {
        return imagePath;
    }

    public Season getSeason() {
        return season;
    }
}
