package org.example.models.game_structure;

import org.example.models.interactions.GreenHouse;

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
}
