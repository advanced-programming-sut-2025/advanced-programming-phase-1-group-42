package org.example.models.interactions.game_buildings;

import org.example.models.Pair;
import org.example.models.Result;
import org.example.models.game_structure.Coordinate;
import org.example.models.game_structure.Tile;
import org.example.models.goods.products.Product;
import org.example.models.interactions.Building;
import org.example.models.interactions.NPCs.NPC;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class GameBuilding extends Building {
    private ArrayList<Tile> tiles;
    private String name;
    private NPC shopkeeper;
    private final Pair<Integer, Integer> hours = new Pair<>(9, 16);

    public abstract String showAllProducts();
    public abstract String showProducts();
    public abstract Result purchase(String productName, String count);


    public boolean isInWorkingHours(int time) {
        if(time > getHours().second() || time < getHours().first())
            return false;
        return true;
    }

    public GameBuilding(ArrayList<Tile> tiles, String name, NPC shopkeeper) {
        this.tiles = tiles;
        this.name = name;
        this.shopkeeper = shopkeeper;
    }

    public boolean isInBuilding(Coordinate coordinate) {
        for (Tile tile : tiles) {
            if(tile.getCordinate().equals(coordinate))
                return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public Pair<Integer, Integer> getHours() {
        return hours;
    }
}
