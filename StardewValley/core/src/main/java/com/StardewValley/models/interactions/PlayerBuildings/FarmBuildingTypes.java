package com.StardewValley.models.interactions.PlayerBuildings;

import com.StardewValley.models.Pair;
import com.badlogic.gdx.graphics.Texture;

public enum FarmBuildingTypes {

    HOME("Home", -1, new Pair<>(20, 20), -1, -1, -1, new Texture("GameAssets/Player_Building/House.png")
    , new Texture("GameAssets/Flooring/Flooring_16.png")),
    BARN("Barn", 4, new Pair<>(7, 4), 6000, 350, 150, new Texture("GameAssets/FarmBuildings/Nan.png"),
        new Texture("GameAssets/FarmBuildings/interior/Barn_Interior.png")),
    BIG_BARN("Big_Barn", 8, new Pair<>(7, 4), 12000, 450, 200, new Texture("GameAssets/FarmBuildings/Nan.png"),
        new Texture("GameAssets/FarmBuildings/interior/Big_Barn_Interior.png")),
    DELUXE_BARN("Deluxe_Barn", 12, new Pair<>(7, 4), 25000, 550, 300, new Texture("GameAssets/FarmBuildings/Nan.png"),
        new Texture("GameAssets/FarmBuildings/interior/Deluxe_Barn_Interior.png")),
    COOP("Coop", 4, new Pair<>(6, 3), 4000, 300, 100, new Texture("GameAssets/FarmBuildings/Nan.png"),
        new Texture("GameAssets/FarmBuildings/interior/Coop_interior.png")),
    BIG_COOP("Big_Coop", 8, new Pair<>(6, 3), 10000, 400, 150, new Texture("GameAssets/FarmBuildings/Nan.png"),
        new Texture("GameAssets/FarmBuildings/interior/Big_Coop_Interior.png")),
    DELUXE_COOP("Deluxe_Coop", 12, new Pair<>(6, 3), 20000, 500, 200, new Texture("GameAssets/FarmBuildings/Nan.png"),
        new Texture("GameAssets/FarmBuildings/interior/Deluxe_Coop_Interior.png"));

    private final String name;
    private final int capacity;
    private final Pair<Integer, Integer> size;
    private final int cost;
    private final int wood;
    private final int stone;
    private final Texture texture;
    private final Texture interiorTexture;


    FarmBuildingTypes(String name, int capacity, Pair<Integer, Integer> size,
                      int cost, int wood, int stone, Texture texture , Texture interiorTexture) {
        this.name = name;
        this.capacity = capacity;
        this.size = size;
        this.cost = cost;
        this.wood = wood;
        this.stone = stone;
        this.texture = texture;
        this.interiorTexture = interiorTexture;
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
        return texture;
    }

    public Texture getInteriorTexture() {
        return interiorTexture;
    }
}
