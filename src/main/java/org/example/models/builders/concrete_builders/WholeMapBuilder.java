package org.example.models.builders.concrete_builders;

import org.example.models.builders.builder_interfaces.MapInterface;
import org.example.models.game_structure.Coordinate;
import org.example.models.game_structure.Farm;
import org.example.models.game_structure.Map;
import org.example.models.game_structure.Tile;
import org.example.models.interactions.game_buildings.*;

import java.util.ArrayList;

public class WholeMapBuilder implements MapInterface {
    private Map map;

    @Override
    public void setShippingBins(ArrayList<Tile> tiles) {

    }

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
    }


    @Override
    public void setTiles(ArrayList<Tile> tiles) {
        map.setTiles(tiles);
    }
}
