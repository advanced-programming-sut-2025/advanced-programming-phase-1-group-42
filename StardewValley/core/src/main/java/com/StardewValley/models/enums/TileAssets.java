package com.StardewValley.models.enums;

public enum TileAssets {
    GRASS("GameAssets/Flooring/Flooring_50.png"),
    FARM_ORDINARY("GameAssets/Flooring/Flooring_14.png"),
    FARM_WINTER("GameAssets/Flooring/Flooring_38.png"),
    FARM_PLOWED("GameAssets/Flooring/Flooring_21.png"),
    FARM_WET("GameAssets/Flooring/Flooring_62.png"),
    WATER("GameAssets/Flooring/Flooring_26.png"),
    BEACH("GameAssets/Flooring/Flooring_25.png"),
    GREEN_HOUSE("GameAssets/Greenhouse/greenhouse.png"),
    QUARRY("GameAssets/Flooring/Flooring_52.png"),
    STONE_WALL("GameAssets/Flooring/Flooring_35.png"),
    SQUARE("GameAssets/Flooring/Flooring_16.png"),
    ROAD("GameAssets/Flooring/Flooring_48.png"),
    SHIPPING_BIN("GameAssets/Shipping_Bin/Shipping_Bin.png"),
    HOUSE("GameAssets/Player_Building/House.png"),
    JOJAMART("GameAssets/Game_Buildings/Jojamart.png"),
    MARNIE_RANCH("GameAssets/Game_Buildings/Ranch.png"),
    PIERRE_SHOP("GameAssets/Game_Buildings/Pierres_shop.png"),
    THE_STARDROP_SALOON("GameAssets/Game_Buildings/Saloon.png"),
    BLACKSMITH("GameAssets/Game_Buildings/Blacksmith.png"),
    CARPENTER_SHOP("GameAssets/Game_Buildings/Carpenter_Shop.png"),
    FISH_SHOP("GameAssets/Game_Buildings/Fish_Shop.png"),
    BROKEN_GREEN_HOUSE("GameAssets/Greenhouse/Broken_Greenhouse.png"),
    THUNDER("GameAssets/Thunder/Thunder.png");

    private final String imagePath;

    TileAssets(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }
}
