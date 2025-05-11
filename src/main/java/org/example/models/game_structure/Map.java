package org.example.models.game_structure;

import jdk.jfr.Recording;
import org.example.models.enums.TileType;
import org.example.models.interactions.Animals.Animal;
import org.example.models.interactions.Player;
import org.example.models.interactions.game_buildings.GameBuilding;

import java.util.ArrayList;

public class Map {
    private final ArrayList<Tile> tiles = new ArrayList<>();
    private final ArrayList<Farm> farms = new ArrayList<>();
    private final ArrayList<GameBuilding> gameBuildings = new ArrayList<>();
    private final ArrayList<Animal> animals = new ArrayList<>();
    private final Coordinate startingCoordinate, endingCoordinate;

    public Map() {
        this.startingCoordinate = new Coordinate(0, 0);
        this.endingCoordinate = new Coordinate(150, 160);
    }

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

    public boolean dfsCheck(Coordinate start, Coordinate end) {
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

    public Tile findTile(Coordinate coordinate) {
        for (Tile tile : this.tiles) {
            if(tile.getCordinate().equals(coordinate))
                return tile;
        }
        return null;
    }

    public TileType findTileType(Coordinate coordinate) {
        for (Tile tile : this.tiles) {
            if (tile.getCordinate().equals(coordinate)) {
                return tile.getTileType();
            }
        }
        return null;
    }

    public Tile findTileByXY(int x, int y) {
        for (Tile tile : this.tiles) {
            if (tile.getCordinate().equals(new Coordinate(x, y))) {
                return tile;
            }
        }
        return null;
    }
    public Farm findFarm(Coordinate coordinate) {
        for (Farm farm : this.farms) {
            if(farm.findTile(coordinate) != null)
                return farm;
        }
        return null;
    }

    public Coordinate getStartingCoordinate() {
        return startingCoordinate;
    }

    public Coordinate getEndingCoordinate() {
        return endingCoordinate;
    }

    public GameBuilding findGameBuilding(Coordinate coordinate) {
        for (GameBuilding gameBuilding : this.gameBuildings) {
            if(gameBuilding.isInBuilding(coordinate))
                return gameBuilding;
        }
        return null;
    }

    public ArrayList<Animal> allAnimals() {
        return this.animals;
    }

    public Animal findAnimalByName(String name) {
        for (Animal animal : this.animals) {
            if (animal.getName().equals(name)) {
                return animal;
            }
        }
        return null;
    }
}
