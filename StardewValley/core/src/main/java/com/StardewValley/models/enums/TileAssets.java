package com.StardewValley.models.enums;

import com.badlogic.gdx.graphics.Texture;

public enum TileAssets {
    GRASS(new Texture("GameAssets/Flooring/Flooring_50.png")),
    FARM_ORDINARY(new Texture("GameAssets/Flooring/Flooring_14.png")),
    FARM_WINTER(new Texture("GameAssets/Flooring/Flooring_38.png")),
    FARM_PLOWED(new Texture("GameAssets/Flooring/Flooring_21.png")),
    FARM_WET(new Texture("GameAssets/Flooring/Flooring_62.png")),
    WATER(new Texture("GameAssets/Flooring/Flooring_26.png")),
    BEACH(new Texture("GameAssets/Flooring/Flooring_25.png")),
    GREEN_HOUSE(new Texture("GameAssets/Greenhouse/greenhouse.png")),
    QUARRY(new Texture("GameAssets/Flooring/Flooring_52.png")),
    STONE_WALL(new Texture("GameAssets/Flooring/Flooring_35.png")),
    SQUARE(new Texture("GameAssets/Flooring/Flooring_16.png")),
    ROAD(new Texture("GameAssets/Flooring/Flooring_48.png")),
    SHIPPING_BIN(new Texture("GameAssets/Shipping_Bin/Shipping_Bin.png")),
    HOUSE(new Texture("GameAssets/Player_Building/House.png")),
    JOJAMART(new Texture("GameAssets/Game_Buildings/Jojamart.png")),
    MARNIE_RANCH(new Texture("GameAssets/Game_Buildings/Ranch.png")),
    PIERRE_SHOP(new Texture("GameAssets/Game_Buildings/Pierres_shop.png")),
    THE_STARDROP_SALOON(new Texture("GameAssets/Game_Buildings/Saloon.png")),
    BLACKSMITH(new Texture("GameAssets/Game_Buildings/Blacksmith.png")),
    CARPENTER_SHOP(new Texture("GameAssets/Game_Buildings/Carpenter_Shop.png")),
    FISH_SHOP(new Texture("GameAssets/Game_Buildings/Fish_Shop.png")),
    BROKEN_GREEN_HOUSE(new Texture("GameAssets/Greenhouse/Broken_Greenhouse.png")),
    THUNDER(new Texture("GameAssets/Thunder/Thunder.png"));

    private final Texture texture;
    TileAssets(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }
}
