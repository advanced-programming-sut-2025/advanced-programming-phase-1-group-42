package com.StardewValley.models.builders;

import com.StardewValley.models.builders.concrete_builders.WholeGameBuilder;
import com.StardewValley.models.builders.concrete_builders.WholeMapBuilder;
import com.StardewValley.models.game_structure.Farm;
import com.StardewValley.models.game_structure.Tile;
import com.StardewValley.models.interactions.Player;

import java.util.ArrayList;

public class Director {
    public void createNewGame(WholeGameBuilder wholeGameBuilder, ArrayList<Player> players, Player adminPlayer, int gameID) {
        wholeGameBuilder.reset();
        wholeGameBuilder.setGameID(gameID);
        wholeGameBuilder.setPlayers(players);
        wholeGameBuilder.setAdminPlayer(adminPlayer);
        wholeGameBuilder.setWeather();
        wholeGameBuilder.setDateTime();
        wholeGameBuilder.setTomorrow();
        wholeGameBuilder.setNPCs(players);
    }

    public void createNewMap(WholeMapBuilder wholeMapBuilder, ArrayList<Farm> farms, ArrayList<Tile> tiles, ArrayList<Player> players) {
        wholeMapBuilder.reset();
        wholeMapBuilder.setTiles(tiles);
        wholeMapBuilder.setFarms(farms);
        wholeMapBuilder.setGameBuildings(tiles, players);
        wholeMapBuilder.setShippingBins();
    }
}
