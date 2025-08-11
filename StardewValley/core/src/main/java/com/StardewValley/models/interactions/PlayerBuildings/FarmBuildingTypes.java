package com.StardewValley.models.interactions.PlayerBuildings;

import com.StardewValley.models.Pair;
import com.badlogic.gdx.graphics.Texture;

public enum FarmBuildingTypes {

    HOME("Home", -1, new Pair<>(20, 20), -1, -1, -1, "GameAssets/Player_Building/House.png"
    , "GameAssets/Flooring/Flooring_16.png"),
    BARN("Barn", 4, new Pair<>(7, 4), 6000, 350, 150, "GameAssets/FarmBuildings/Barn.png",
        "GameAssets/FarmBuildings/interior/Barn_Interior.png"),
    BIG_BARN("Big_Barn", 8, new Pair<>(7, 4), 12000, 450, 200, "GameAssets/FarmBuildings/Big_Barn.png",
        "GameAssets/FarmBuildings/interior/Big_Barn_Interior.png"),
    DELUXE_BARN("Deluxe_Barn", 12, new Pair<>(7, 4), 25000, 550, 300, "GameAssets/FarmBuildings/Deluxe_Barn.png",
        "GameAssets/FarmBuildings/interior/Deluxe_Barn_Interior.png"),
    COOP("Coop", 4, new Pair<>(6, 3), 4000, 300, 100, "GameAssets/FarmBuildings/Coop.png",
        "GameAssets/FarmBuildings/interior/Coop_interior.png"),
    BIG_COOP("Big_Coop", 8, new Pair<>(6, 3), 10000, 400, 150, "GameAssets/FarmBuildings/Big_Coop.png",
        "GameAssets/FarmBuildings/interior/Big_Coop_Interior.png"),
    DELUXE_COOP("Deluxe_Coop", 12, new Pair<>(6, 3), 20000, 500, 200, "GameAssets/FarmBuildings/Deluxe_Coop.png",
        "GameAssets/FarmBuildings/interior/Deluxe_Coop_Interior.png");

    private final String name;
    private final int capacity;
    private final Pair<Integer, Integer> size;
    private final int cost;
    private final int wood;
    private final int stone;
    private final String textureString;
    private final String interiorTextureString;


    FarmBuildingTypes(String name, int capacity, Pair<Integer, Integer> size,
                      int cost, int wood, int stone, String textureString , String interiorTextureString) {
        this.name = name;
        this.capacity = capacity;
        this.size = size;
        this.cost = cost;
        this.wood = wood;
        this.stone = stone;
        this.textureString = textureString;
        this.interiorTextureString = interiorTextureString;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public Pair<Integer, Integer> getSize() {
        return size;
    }

    public int getCost() {
        return cost;
    }

    public int getWood() {
        return wood;
    }

    public int getStone() {
        return stone;
    }

    public Texture getTexture() {
        return new Texture(textureString);
    }

    public Texture getInteriorTexture() {
        return new Texture(interiorTextureString);
    }
}
