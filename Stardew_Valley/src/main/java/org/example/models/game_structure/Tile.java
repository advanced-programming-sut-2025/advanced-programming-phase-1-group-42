package org.example.models.game_structure;

import org.example.models.enums.TileType;
import org.example.models.goods.Good;
import org.example.models.goods.GoodType;

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

    public TileType getTileType() {
        return tileType;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }

    public void setGoods(ArrayList<Good> goods) {
        this.goods = goods;
    }

    public Good findGood(GoodType goodType) {
        for (Good good : this.goods) {
            if(good.getType() == goodType)
                return good;
        }
        return null;
    }

    public Good findGood(String goodName) {
        for (Good good : this.goods) {
            if(good.getName().equals(goodName))
                return good;
        }
        return null;
    }
}
