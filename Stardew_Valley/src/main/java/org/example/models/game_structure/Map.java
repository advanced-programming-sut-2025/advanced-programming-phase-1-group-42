package org.example.models.game_structure;

import org.example.models.interactions.game_buildings.ShopType;

import java.util.ArrayList;

public class Map {
    private final ArrayList<Tile> tiles = new ArrayList<>();
    private final ArrayList<Farm> farms = new ArrayList<>();
    private final ArrayList<ShopType> gameBuildings = new ArrayList<>();



    // A function to print map
    public String printMap() {
        //TODO
        return "";
    }

    // function to help reading the map (Maybe RGB)
    public String helpReadingMap() {
        //TODO
        return "";
    }

    public void setFarms(ArrayList<Farm> farms) {
        this.farms.addAll(farms);
    }

    public boolean dfsCheck(Cordinate start, Cordinate end) {
        //TODO
    }

    public GameBuilding getBlackSmith() {
        return this.gameBuildings.get(0);
    }

    public GameBuilding getJojoMart() {
        return this.gameBuildings.get(1);
    }

    public GameBuilding getPierreGeneralStore() {
        return this.gameBuildings.get(2);
    }

    public GameBuilding getCarpenterShop() {
        return this.gameBuildings.get(3);
    }

    public GameBuilding getFishShop() {
        return this.gameBuildings.get(4);
    }

    public GameBuilding getMarnieRanch() {
        return this.gameBuildings.get(5);
    }

    public GameBuilding getTheStarDropSaloon() {
        return this.gameBuildings.get(6);
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public Tile findTile(Cordinate cordinate) {
        for (Tile tile : this.tiles) {
            if(tile.getCordinate().equals(cordinate))
                return tile;
        }
        return null;
    }
}
