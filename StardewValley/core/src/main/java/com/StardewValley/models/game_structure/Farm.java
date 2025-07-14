package com.StardewValley.models.game_structure;

import com.StardewValley.models.enums.TileType;
import com.StardewValley.models.interactions.GreenHouse;
import com.StardewValley.models.interactions.Player;
import com.StardewValley.models.interactions.PlayerBuildings.FarmBuilding;
import com.StardewValley.models.interactions.PlayerBuildings.FarmBuildingTypes;

import java.util.ArrayList;

public class Farm {
    private final ArrayList<Tile> tiles = new ArrayList<>();
    private final ArrayList<FarmBuilding> farmBuildings = new ArrayList<>();
    private GreenHouse greenHouse;
    private ArrayList<Tile> lakes;
    private ArrayList<Tile> quarry;
    private int farmNumber;
    private ShippingBin shippingBin;

    public Farm(int farmNumber, int playerNumber, ArrayList<Tile> tiles) {
        this.farmNumber = farmNumber;
        this.shippingBin = null;

        for (int i = (playerNumber % 2) * 70; i < (playerNumber % 2 + 1) * 70; i++) {
            boolean flag = (i == (playerNumber % 2) * 70) || (i + 1 == (playerNumber % 2 + 1) * 70);
            for (int j = (playerNumber / 2) * 110; j < (playerNumber / 2) * 110 + 50; j++) {
                try {
                    this.tiles.add(tiles.get((i * 160) + j));
                    this.tiles.getLast().setTileType(TileType.FARM);

                    if(flag || j == (playerNumber / 2) * 110 || (j + 1 == (playerNumber / 2) * 110 + 50))
                        this.tiles.getLast().setTileType(TileType.STONE_WALL);

                    switch (playerNumber) {
                        case 0:
                            if(j == 49 && i >= 30 && i < 40)
                                this.tiles.getLast().setTileType(TileType.PLAIN);
                            break;
                        case 1:
                            if(j == 49 && i >= 105 && i < 115)
                                this.tiles.getLast().setTileType(TileType.PLAIN);
                            break;
                        case 2:
                            if(j == 109 && i >= 30 && i < 40)
                                this.tiles.getLast().setTileType(TileType.PLAIN);
                            break;

                        case 3:
                            if(j == 109 && i >= 105 && i < 115)
                                this.tiles.getLast().setTileType(TileType.PLAIN);
                            break;
                    }
                } catch (Exception e) {
                    System.out.println("Ops");
                }

            }
        }

        farmBuildings.add(getHome(playerNumber));
        iniGreenHouse(playerNumber);
        lakes = getLakes(playerNumber, tiles);
        quarry = getQuarry(playerNumber, tiles);

        for (int i = (playerNumber % 2) * 70; i < (playerNumber % 2 + 1) * 70; i++) {
            boolean flag = (i == (playerNumber % 2) * 70 || (i + 1 == (playerNumber % 2 + 1) * 70));
            for (int j = (playerNumber / 2) * 110; j < (playerNumber / 2) * 110 + 50; j++) {

                int ii = i - (playerNumber % 2) * 70, jj = j - (playerNumber / 2) * 110;
                if(flag || (j == (playerNumber / 2) * 110) || (j + 1 == (playerNumber / 2) * 110 + 50))
                    this.tiles.get((ii * 50) + jj).setTileType(TileType.STONE_WALL);

                switch (playerNumber) {
                    case 0:
                        if (j == 49 && i >= 30 && i < 40)
                            this.tiles.get((ii * 50) + jj).setTileType(TileType.PLAIN);
                        break;
                    case 1:
                        if (j == 49 && i >= 105 && i < 115)
                            this.tiles.get((ii * 50) + jj).setTileType(TileType.PLAIN);
                        break;
                    case 2:
                        if (j == 110 && i >= 25 && i < 35)
                            this.tiles.get((ii * 50) + jj).setTileType(TileType.PLAIN);
                        break;

                    case 3:
                        if (j == 110 && i >= 100 && i < 110)
                            this.tiles.get((ii * 50) + jj).setTileType(TileType.PLAIN);
                        break;
                }
            }
        }
    }

    private void iniGreenHouse(int playerNumber) {
        ArrayList<Tile> greenHouseTile = new ArrayList<>();
        Coordinate startCoordinate = null;
        Coordinate endCoordinate = null;

        if(farmNumber == 0) {
            for (int i = 32; i - 32 < 6; i++) {
                for (int j = 5; j - 5 < 5; j++) {
                    this.tiles.get((i * 50) + j).setTileType(TileType.GREEN_HOUSE);
                    greenHouseTile.add(this.tiles.get((i * 50) + j));
                    if(j == 5)
                        greenHouseTile.getLast().setTileType(TileType.WATER);
                }
            }
            startCoordinate = new Coordinate((playerNumber % 2) * 70 + 32, (playerNumber / 2) * 110 + 5);
            endCoordinate = new Coordinate((playerNumber % 2) * 70 + 38, (playerNumber / 2) * 110 + 10);
        }
        else {
            for (int i = 59; i - 59 < 6; i++) {
                for (int j = 5; j - 5 < 5; j++) {
                    this.tiles.get((i * 50) + j).setTileType(TileType.GREEN_HOUSE);
                    greenHouseTile.add(this.tiles.get((i * 50) + j));
                    if(j == 5)
                        greenHouseTile.getLast().setTileType(TileType.WATER);
                }
            }
            startCoordinate = new Coordinate((playerNumber % 2) * 70 + 59, (playerNumber / 2) * 110 + 5);
            endCoordinate = new Coordinate((playerNumber % 2) * 70 + 65, (playerNumber / 2) * 110 + 10);
        }
        this.greenHouse = new GreenHouse(startCoordinate, endCoordinate, greenHouseTile);
    }

    private FarmBuilding getHome(int playerNumber) {
        Coordinate startCoordinate = null;
        if(farmNumber == 0) {
            startCoordinate = new Coordinate((playerNumber % 2) * 70 + 45,  (playerNumber / 2) * 110 + 5);
            for (int i = 45; i - 45 < 10; i++) {
                for (int j = 5; j - 5 < 10; j++) {
                    this.tiles.get((i * 50) + j).setTileType(TileType.PLAYER_BUILDING);
                }
            }
        }
        else {
            startCoordinate = new Coordinate((playerNumber % 2) * 70 + 25, (playerNumber / 2) * 110 + 15);
            for (int i = 25; i - 25 < 10; i++) {
                for (int j = 15; j - 15 < 10; j++) {
                    try {
                        this.tiles.get((i * 50) + j).setTileType(TileType.PLAYER_BUILDING);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        return new FarmBuilding(FarmBuildingTypes.HOME, startCoordinate);
    }

    private ArrayList<Tile> getLakes(int playerNumber, ArrayList<Tile> tiles) {
        ArrayList<Tile> lakes = new ArrayList<>();
        Coordinate startCoordinate = null;
        if(farmNumber == 0) {
            startCoordinate = new Coordinate( 25, 35);
            for (int i = startCoordinate.getX(); i - 25 < 20; i++) {
                for (int j = startCoordinate.getY(); j - 35 < 10; j++) {
                    lakes.add(this.tiles.get((i * 50) + j));
                    lakes.getLast().setTileType(TileType.WATER);
                }
            }
        }
        else {
            startCoordinate = new Coordinate(0, 0);
            for (int i = startCoordinate.getX(); i < 7; i++) {
                for (int j = startCoordinate.getY(); j < 50; j++) {
                    try {

                        lakes.add(this.tiles.get((i * 50) + j));
                        lakes.getLast().setTileType(TileType.WATER);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
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
                    quarryTiles.add(this.tiles.get((i * 50) + j));
                    quarryTiles.getLast().setTileType(TileType.QUARRY);
                }
            }
        }
        else {
            startCoordinate = new Coordinate(55, 35);
            for (int i = startCoordinate.getX(); i - 55 < 10; i++) {
                for (int j = startCoordinate.getY(); j - 35 < 10; j++) {
                    try {

                        quarryTiles.add(this.tiles.get((i * 50) + j));
                        quarryTiles.getLast().setTileType(TileType.QUARRY);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
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

    public void setShippingBin(ShippingBin shippingBin) {
        this.shippingBin = shippingBin;
    }
}
