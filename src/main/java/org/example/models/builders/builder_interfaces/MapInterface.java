package org.example.models.builders.builder_interfaces;

import org.example.models.game_structure.Farm;
import org.example.models.game_structure.Map;

import java.util.ArrayList;

public interface MapInterface {
    public void reset();
    public Map getMap();
    public void setFarms(ArrayList<Farm> farms);
    public void setGameBuildings();
    //TODO : After building the map on DB, we will complete this section

}
