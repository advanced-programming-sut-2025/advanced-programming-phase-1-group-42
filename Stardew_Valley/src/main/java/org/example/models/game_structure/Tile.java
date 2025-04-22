package org.example.models.game_structure;

import org.example.models.enums.TileType;
import org.example.models.goods.Good;

import java.util.ArrayList;

public class Tile {
    private Cordinate cordinate;
    private TileType tileType;
    private ArrayList<Good> goods;

    public ArrayList<Good> getGoods() {
        return goods;
    }

    public Cordinate getCordinate() {
        return cordinate;
    }





}
