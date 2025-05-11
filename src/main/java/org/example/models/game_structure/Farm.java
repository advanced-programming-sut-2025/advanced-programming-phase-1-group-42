package org.example.models.game_structure;

import org.example.models.App;
import org.example.models.enums.TileType;
import org.example.models.interactions.GreenHouse;
import org.example.models.interactions.Player;
import org.example.models.interactions.PlayerBuildings.FarmBuilding;
import org.example.models.interactions.PlayerBuildings.FarmBuildingTypes;

import java.util.ArrayList;

public class Farm {
    private final ArrayList<Tile> tiles = new ArrayList<>();
    private final ArrayList<FarmBuilding> farmBuildings = new ArrayList<>();
    private GreenHouse greenHouse;
    private ArrayList<Tile> lakes;
    private ArrayList<Tile> quarry;
    private int farmNumber;

    public Farm(int farmNumber, int playerNumber, ArrayList<Tile> tiles) {
        this.farmNumber = farmNumber;

        for (int i = (playerNumber % 2) * 70; i < (playerNumber % 2 + 1) * 70; i++) {
            boolean flag = (i == 0 || (i + 1 == (playerNumber % 2 + 1) * 70));
            for (int j = (playerNumber / 2) * 110; j < (playerNumber / 2) * 110 + 50; j++) {
                this.tiles.add(tiles.get(i + (j * 150)));
                this.tiles.getLast().setTileType(TileType.FARM);
                if(flag || j == 0 || (j + 1 == (playerNumber / 2) * 110 + 50))
                    this.tiles.getLast().setTileType(TileType.STONE_WALL);
            }
        }

        farmBuildings.add(getHome(playerNumber));
        iniGreenHouse();
        lakes = getLakes(playerNumber, tiles);
        quarry = getQuarry(playerNumber, tiles);


    }

    private void iniGreenHouse() {
        greenHouse = null;
        if(farmNumber == 0) {
            for (int i = 32; i - 32 < 6; i++) {
                for (int j = 5; j - 5 < 5; j++) {
                    this.tiles.get(i + (j * 150)).setTileType(TileType.GREEN_HOUSE);
                }
            }
        }
        else {
            for (int i = 59; i - 59 < 6; i++) {
                for (int j = 5; j - 5 < 5; j++) {
                    this.tiles.get(i + (j * 150)).setTileType(TileType.GREEN_HOUSE);
                }
            }
        }
    }

    private FarmBuilding getHome(int playerNumber) {
        Coordinate startCoordinate = null;
        if(farmNumber == 0) {
            startCoordinate = new Coordinate((playerNumber % 2) * 70 + 45,  (playerNumber / 2) * 110 + 5);
            for (int i = 45; i - 45 < 20; i++) {
                for (int j = 5; j - 5 < 20; j++) {
                    this.tiles.get(i + (j * 150)).setTileType(TileType.PLAYER_BUILDING);
                }
            }
        }
        else {
            startCoordinate = new Coordinate((playerNumber % 2) * 70 + 25, (playerNumber / 2) * 110 + 15);
            for (int i = 25; i - 25 < 20; i++) {
                for (int j = 15; j - 15 < 20; j++) {
                    this.tiles.get(i + (j * 150)).setTileType(TileType.PLAYER_BUILDING);
                }
            }
        }
        FarmBuilding home = new FarmBuilding(FarmBuildingTypes.HOME, startCoordinate);
        return home;
    }

    private ArrayList<Tile> getLakes(int playerNumber, ArrayList<Tile> tiles) {
        ArrayList<Tile> lakes = new ArrayList<>();
        Coordinate startCoordinate = null;
        if(farmNumber == 0) {
            startCoordinate = new Coordinate( 25, 35);
            for (int i = startCoordinate.getX(); i - 25 < 20; i++) {
                for (int j = startCoordinate.getY(); j - 35 < 10; j++) {
                    lakes.add(this.tiles.get(i + (j * 150)));
                    lakes.getLast().setTileType(TileType.WATER);
                }
            }
        }
        else {
            startCoordinate = new Coordinate(0, 0);
            for (int i = startCoordinate.getX(); i < 5; i++) {
                for (int j = startCoordinate.getY(); j < 50; j++) {
                    lakes.add(this.tiles.get(i + (j * 150)));
                    lakes.getLast().setTileType(TileType.WATER);
                }
            }
        }

        return lakes;
    }

    private ArrayList<Tile> getQuarry(int playerNumber, ArrayList<Tile> tiles) {
        ArrayList<Tile> quarryTiles = new ArrayList<>();
        Coordinate startCoordinate = null;
        if(farmNumber == 0) {
            startCoordinate = new Coordinate(5, 10);
            for (int i = startCoordinate.getX(); i - 5 < 10; i++) {
                for (int j = startCoordinate.getY(); j - 10 < 10; j++) {
                    quarryTiles.add(this.tiles.get(i + (j * 150)));
                    quarryTiles.getLast().setTileType(TileType.QUARRY);
                }
            }
        }
        else {
            startCoordinate = new Coordinate(55, 35);
            for (int i = startCoordinate.getX(); i - 55 < 10; i++) {
                for (int j = startCoordinate.getY(); j - 35 < 10; j++) {
                    quarryTiles.add(this.tiles.get(i + (j * 150)));
                    quarryTiles.getLast().setTileType(TileType.QUARRY);
                }
            }
        }

        return quarryTiles;
    }

    public int getFarmNumber() {
        return farmNumber;
    }

    public void setFarmNumber(int farmNumber) {
        this.farmNumber = farmNumber;
    }

    public GreenHouse getGreenHouse() {
        return greenHouse;
    }

    public void setGreenHouse(GreenHouse greenHouse) {
        this.greenHouse = greenHouse;
    }

    public Tile checkInFarm(Coordinate coordinate, Player player) {
        for (Tile tile : tiles) {
            if(tile.getCordinate().equals(coordinate)) {
                return tile;
            }
        }


        if(player.getMarried() != null) {
            for (Tile tile : player.getMarried().getFarm().getTiles()) {
                if(tile.getCordinate().equals(coordinate)) {
                    return tile;
                }
            }
        }
        return null;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public Tile findTile(Coordinate coordinate) {
        for (Tile tile : tiles) {
            if(tile.getCordinate().equals(coordinate)) {
                return tile;
            }
        }
        return null;
    }

    public ArrayList<FarmBuilding> getFarmBuildings() {
        return farmBuildings;
    }
}
