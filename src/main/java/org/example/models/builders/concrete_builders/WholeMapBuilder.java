package org.example.models.builders.concrete_builders;

import org.example.models.builders.builder_interfaces.MapInterface;
import org.example.models.game_structure.*;
import org.example.models.goods.Good;
import org.example.models.goods.artisans.Artisan;
import org.example.models.interactions.Player;
import org.example.models.interactions.game_buildings.*;

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
                        new ShippingBin(new Coordinate(30, 50), list),
                        new ShippingBin(new Coordinate(105, 50), list),
                        new ShippingBin(new Coordinate(30, 110), list),
                        new ShippingBin(new Coordinate(105, 110), list),
                        new ShippingBin(new Coordinate(35, 80), list),
                        new ShippingBin(new Coordinate(110, 80), list)
                )
        );

        map.setShippingBins(shippingBins);
        int ptr = 0;
        for (Farm farm : this.map.getFarms()) {
            farm.setShippingBin(shippingBins.get(ptr++));
        }
    }

    @Override
    public void setTiles(ArrayList<Tile> tiles) {
        map.setTiles(tiles);
    }
}
