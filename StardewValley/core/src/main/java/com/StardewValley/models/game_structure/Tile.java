package com.StardewValley.models.game_structure;

import com.StardewValley.models.App;
import com.StardewValley.models.enums.TileType;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.goods.craftings.Crafting;
import com.StardewValley.models.goods.craftings.CraftingType;
import com.StardewValley.models.goods.farmings.FarmingTree;
import com.StardewValley.models.goods.farmings.FarmingTreeSapling;
import com.StardewValley.models.goods.fishs.Fish;
import com.StardewValley.models.goods.foods.Food;
import com.StardewValley.models.goods.foragings.ForagingMineral;
import com.StardewValley.models.goods.foragings.ForagingMixedSeed;
import com.StardewValley.models.goods.foragings.ForagingSeed;
import com.StardewValley.models.goods.foragings.ForagingTree;
import com.StardewValley.models.goods.products.Product;
import com.StardewValley.models.goods.recipes.CookingRecipe;
import com.StardewValley.models.goods.recipes.CookingRecipeType;
import com.StardewValley.models.goods.recipes.CraftingRecipe;
import com.StardewValley.models.goods.tools.Tool;

import java.util.ArrayList;

public class Tile {
    private Coordinate coordinate;
    private TileType tileType;
    private ArrayList<Good> goods = new ArrayList<>();
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

    public Good doesHasTree() {
        for (Good good : this.goods) {
            if (good instanceof ForagingTree || good instanceof FarmingTree)
                return good;
        }
        return null;
    }

    public Good doesHasTreeSapling() {
        for (Good good : this.goods) {
            if (good instanceof FarmingTreeSapling)
                return good;
        }
        return null;
    }

    public Good doesHasFish() {
        for (Good good : this.goods) {
            if (good instanceof Fish)
                return good;
        }
        return null;
    }

    public Good doesHasFood() {
        for (Good good : this.goods) {
            if (good instanceof Food)
                return good;
        }
        return null;
    }

    public Good doesHasSeed() {
        for (Good good : this.goods) {
            if (good instanceof ForagingSeed || good instanceof ForagingMixedSeed)
                return good;
        }
        return null;
    }

    public Good doesHasMineral() {
        for (Good good : this.goods) {
            if (good instanceof ForagingMineral)
                return good;
        }
        return null;
    }

    public Good doesHasProduct() {
        for (Good good : this.goods) {
            if (good instanceof Product)
                return good;
        }
        return null;
    }

    public Good doesHasCrafting() {
        for (Good good : this.goods) {
            if (good instanceof Crafting)
                return good;
        }
        return null;
    }

    public Good doesHasRecipe() {
        for (Good good : this.goods) {
            if (good instanceof CookingRecipe || good instanceof CraftingRecipe)
                return good;
        }
        return null;
    }

    public Good doesHasTool() {
        for (Good good : this.goods) {
            if (good instanceof Tool)
                return good;
        }
        return null;
    }
}
