package org.example.models.game_structure;

import org.example.models.enums.TileType;
import org.example.models.goods.Good;
import org.example.models.goods.GoodType;

import java.util.ArrayList;

public class Tile {
    private Cordinate cordinate;
    private TileType tileType;
    private ArrayList<Good> goods;
    private boolean isWatered;

    public boolean isWatered() {
        return isWatered;
    }

    public void setWatered(boolean watered) {
        isWatered = watered;
    }

    public ArrayList<Good> getGoods() {
        return goods;
    }

    public Cordinate getCordinate() {
        return cordinate;
    }

    public TileType getTileType() {
        return tileType;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }

    public void setGoods(ArrayList<Good> goods) {
        this.goods = goods;
    }

    public Good findGoods(GoodType goodType) {
        for (Good good : this.goods) {
            if(good.getType() == goodType)
                return good;
        }
        return null;
    }
}
