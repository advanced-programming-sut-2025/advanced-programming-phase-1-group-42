package org.example.models.game_structure;

import org.example.models.interactions.GreenHouse;
import org.example.models.interactions.Player;

import java.util.ArrayList;

public class Farm {
    private final ArrayList<Tile> tiles = new ArrayList<>();
    private GreenHouse greenHouse;
    private ArrayList<Tile> lakes;
    private ArrayList<Tile> quarry;
    private int farmNumber;

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
}
