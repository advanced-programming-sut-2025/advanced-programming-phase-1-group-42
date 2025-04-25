package org.example.models.builders.concrete_builders;

import org.example.models.builders.builder_interfaces.MapInterface;
import org.example.models.game_structure.Farm;
import org.example.models.game_structure.Map;

import java.util.ArrayList;

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
    public void setGameBuildings() {
        //TODO
    }
}
