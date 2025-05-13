package org.example.models.builders.builder_interfaces;

import org.example.models.game_structure.Farm;
import org.example.models.game_structure.Map;
import org.example.models.game_structure.Tile;

import java.util.ArrayList;

public interface MapInterface {
    public void reset();
    public Map getMap();
    public void setTiles(ArrayList<Tile> tiles);
    public void setFarms(ArrayList<Farm> farms);
    public void setGameBuildings(ArrayList<Tile> tiles);
    public void setShippingBins(ArrayList<Tile> tiles);
    //TODO : After building the map on DB, we will complete this section

}
