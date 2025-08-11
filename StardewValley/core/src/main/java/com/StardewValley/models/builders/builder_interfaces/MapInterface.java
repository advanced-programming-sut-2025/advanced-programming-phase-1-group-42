package com.StardewValley.models.builders.builder_interfaces;

import com.StardewValley.models.game_structure.Farm;
import com.StardewValley.models.game_structure.Map;
import com.StardewValley.models.game_structure.Tile;
import com.StardewValley.models.interactions.Player;

import java.util.ArrayList;

public interface MapInterface {
    public void reset();
    public Map getMap();
    public void setTiles(ArrayList<Tile> tiles);
    public void setFarms(ArrayList<Farm> farms);
    public void setGameBuildings(ArrayList<Tile> tiles, ArrayList<Player> players);
    public void setShippingBins();

}
