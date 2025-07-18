package com.StardewValley.models.builders.concrete_builders;


import com.StardewValley.models.builders.builder_interfaces.MapInterface;
import com.StardewValley.models.enums.TileType;
import com.StardewValley.models.game_structure.*;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.interactions.Player;
import com.StardewValley.models.interactions.game_buildings.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class WholeMapBuilder implements MapInterface {
    private Map map;

    @Override
    public void reset() {
        this.map = new Map();
    }

    @Override
    public Map getMap() {
        Map finalMap = this.map;
        this.reset();
        return finalMap;
    }

    @Override
    public void setFarms(ArrayList<Farm> farms) {
        this.map.setFarms(farms);
    }

    @Override
    public void setGameBuildings(ArrayList<Tile> tiles) {
        ArrayList<GameBuilding> gameBuildings = new ArrayList<>();
        gameBuildings.add(new Blacksmith(Blacksmith.getExpectedTiles(tiles)));
        gameBuildings.add(new JojaMart(JojaMart.getExpectedTiles(tiles)));
        gameBuildings.add(new PierreGeneralStore(PierreGeneralStore.getExpectedTiles(tiles)));
        gameBuildings.add(new CarpenterShop(CarpenterShop.getExpectedTiles(tiles)));
        gameBuildings.add(new FishShop(FishShop.getExpectedTiles(tiles)));
        gameBuildings.add(new MarnieRanch(MarnieRanch.getExpectedTiles(tiles)));
        gameBuildings.add(new TheStarDropSaloon(TheStarDropSaloon.getExpectedTiles(tiles)));

        this.map.setGameBuildings(gameBuildings);
    }

    @Override
    public void setShippingBins() {
        HashMap<Player, ArrayList<ArrayList<Good>>> list = new HashMap<>();
        ArrayList<ShippingBin> shippingBins = new ArrayList<>(
                Arrays.asList(
                        new ShippingBin(new Coordinate(30, 49), list),
                        new ShippingBin(new Coordinate(105, 49), list),
                        new ShippingBin(new Coordinate(25, 110), list),
                        new ShippingBin(new Coordinate(100, 110), list),
                        new ShippingBin(new Coordinate(35, 80), list),
                        new ShippingBin(new Coordinate(104, 80), list)
                )
        );

        map.setShippingBins(shippingBins);
        int ptr = 0;
        for (Farm farm : this.map.getFarms()) {
            farm.setShippingBin(shippingBins.get(ptr++));
        }
        for (ShippingBin shippingBin : shippingBins) {
            this.map.getTiles().get((shippingBin.getCoordinate().getX() * 160) + shippingBin.getCoordinate().getY()).setTileType(TileType.SHIPPING_BIN);
            this.map.getTiles().get((shippingBin.getCoordinate().getX() * 160) + shippingBin.getCoordinate().getY()).getGoods().add(shippingBin);
        }
    }

    @Override
    public void setTiles(ArrayList<Tile> tiles) {
        map.setTiles(tiles);
    }
}
