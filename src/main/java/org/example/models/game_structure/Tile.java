package org.example.models.game_structure;

import org.example.models.App;
import org.example.models.enums.TileType;
import org.example.models.goods.Good;
import org.example.models.goods.GoodType;
import org.example.models.goods.craftings.CraftingType;

import java.util.ArrayList;

public class Tile {
    private Coordinate coordinate;
    private TileType tileType;
    private ArrayList<Good> goods;
    private boolean isWatered = false;

    public Tile(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public ArrayList<Good> getGoods() {
        return goods;
    }

    public void deleteGood(Good good) {
        goods.remove(good);
    }
    public void addGood(Good good) {
        goods.add(good);
    }

    public void removeGoodFromTile(Good good) {
        goods.remove(good);
    }
    public void addGoodToTile(Good good) {
        goods.add(good);
    }


    public Coordinate getCordinate() {
        return coordinate;
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


    public boolean isWatered() {
        return isWatered;
    }

    public void setWatered(boolean watered) {
        isWatered = watered;
    }


    public boolean checkAroundForScarCrow(){
        for(int i = -1 ; i < 1 ; i++) {
            for(int j = -1 ; j < 1 ; j++) {
                for (Good good : App.getCurrentGame().getMap()
                        .findTileByXY(this.coordinate.getX() + i, this.coordinate.getY() + j).getGoods()) {
                    if (good.equals(CraftingType.SCARECROW)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
