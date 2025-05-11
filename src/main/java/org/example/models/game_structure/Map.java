package org.example.models.game_structure;

import jdk.jfr.Recording;
import org.example.models.Pair;
import org.example.models.enums.TileType;
import org.example.models.goods.Good;
import org.example.models.goods.farmings.FarmingTree;
import org.example.models.goods.farmings.FarmingTreeSapling;
import org.example.models.goods.foragings.ForagingMineralType;
import org.example.models.goods.foragings.ForagingTree;
import org.example.models.interactions.Animals.Animal;
import org.example.models.interactions.Player;
import org.example.models.enums.TileType;
import org.example.models.interactions.game_buildings.GameBuilding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Map {
    private final ArrayList<Tile> tiles = new ArrayList<>();
    private final ArrayList<Farm> farms = new ArrayList<>();
    private final ArrayList<GameBuilding> gameBuildings = new ArrayList<>();
    private final ArrayList<Animal> animals = new ArrayList<>();
    private final Coordinate startingCoordinate, endingCoordinate;

    static final HashMap<String, String> colorMap = new HashMap<String, String>() {{
        put("Reset", "\u001B[0m");
        // Foreground colors
        put("Black", "\033[0;30m");
        put("Red", "\033[0;31m");
        put("Green", "\033[0;32m");
        put("Yellow", "\033[0;33m");
        put("Blue", "\033[0;34m");
        put("Purple", "\033[0;35m");
        put("Cyan", "\033[0;36m");
        put("White", "\033[0;37m");
        put("Gray", "\033[0;90m");

        // Background colors
        put("Black_Background", "\033[40m");
        put("Red_Background", "\033[41m");
        put("Green_Background", "\033[42m");
        put("Yellow_Background", "\033[43m");
        put("Blue_Background", "\033[44m");
        put("Purple_Background", "\033[45m");
        put("Cyan_Background", "\033[46m");
        put("White_Background", "\033[47m");
        put("Gray_Background", "\033[100m");
    }};



    public Map() {
        this.startingCoordinate = new Coordinate(0, 0);
        this.endingCoordinate = new Coordinate(150, 160);
    }

    // A function to print map
    public String printMap(int x , int y, int size) {
        for(int i = 0 ; i < size; i++){
            System.out.print("\n");
            for(int j = 0 ; j < size; j++){
                Coordinate coordinate = new Coordinate(x + i,y + j);
                Tile tile = findTile(coordinate);

                if(tile == null){
                    System.out.print(colorMap.get("Black_Background") + " " + colorMap.get("Reset"));
                }else if(tile.getTileType() == TileType.FARM){
                    for (Good good : tile.getGoods()) {
                        if(good instanceof ForagingTree){
                            System.out.print(colorMap.get("Green_Background")+
                                    colorMap.get("Yellow")+"T"+ colorMap.get("Reset"));
                        } else if(good instanceof FarmingTree){
                            System.out.print(colorMap.get("Green_Background")+
                                    colorMap.get("Yellow")+"T"+ colorMap.get("Reset"));
                        } else if(good instanceof FarmingTreeSapling){
                            System.out.print(colorMap.get("Green_Background")+
                                    colorMap.get("Yellow")+"T"+ colorMap.get("Reset"));
                        } else{
                            System.out.print(colorMap.get("Green_Background")+
                                    colorMap.get("Yellow")+" "+ colorMap.get("Reset"));
                        }
                    }
                }else if(tile.getTileType() == TileType.WATER){
                    System.out.print(colorMap.get("Cyan_Background") + " "+ colorMap.get("Reset"));
                }else if(tile.getTileType() == TileType.GREEN_HOUSE) {
                    for (Good good : tile.getGoods()) {
                        if(good instanceof ForagingTree){
                            System.out.print(colorMap.get("White_Background")+
                                    colorMap.get("Yellow")+"T"+ colorMap.get("Reset"));
                        } else if(good instanceof FarmingTree){
                            System.out.print(colorMap.get("White_Background")+
                                    colorMap.get("Yellow")+"T"+ colorMap.get("Reset"));
                        } else if(good instanceof FarmingTreeSapling){
                            System.out.print(colorMap.get("White_Background")+
                                    colorMap.get("Yellow")+"T"+ colorMap.get("Reset"));
                        } else{
                            System.out.print(colorMap.get("White_Background")+
                                    colorMap.get("Yellow")+" "+ colorMap.get("Reset"));
                        }
                    }
                }else if(tile.getTileType() == TileType.PLAYER_BUILDING){
                    System.out.print(colorMap.get("Yellow_Background")+" "+ colorMap.get("Reset") );
                }else if(tile.getTileType() == TileType.ROAD){
                    System.out.print(colorMap.get("Gray_Background")+
                            colorMap.get("Purple")+"-"+ colorMap.get("Reset"));
                }else if(tile.getTileType() == TileType.QUARRY) {
                    System.out.print(colorMap.get("Gray_Background")+" "+ colorMap.get("Reset"));
                }else if(tile.getTileType() == TileType.PLAIN) {
                    System.out.print(colorMap.get("Green_Background")+
                            colorMap.get("Red")+"-"+ colorMap.get("Reset"));
                }
            }
        }
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
        return false;
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

    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles.addAll(tiles);
    }
}
