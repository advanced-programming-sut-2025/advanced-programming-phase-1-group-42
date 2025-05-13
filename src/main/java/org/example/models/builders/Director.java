package org.example.models.builders;

import org.example.models.builders.concrete_builders.WholeGameBuilder;
import org.example.models.builders.concrete_builders.WholeMapBuilder;
import org.example.models.game_structure.Farm;
import org.example.models.game_structure.Tile;
import org.example.models.interactions.Player;

import java.util.ArrayList;

public class Director {
    public void createNewGame(WholeGameBuilder wholeGameBuilder, ArrayList<Player> players, Player adminPlayer) {
        wholeGameBuilder.reset();
        wholeGameBuilder.setPlayers(players);
        wholeGameBuilder.setAdminPlayer(adminPlayer);
        wholeGameBuilder.setCurrentPlayer(adminPlayer);
        wholeGameBuilder.setWeather();
        wholeGameBuilder.setDateTime();
        wholeGameBuilder.setTomorrow();
        wholeGameBuilder.setNPCs();
    }

    public void createNewMap(WholeMapBuilder wholeMapBuilder, ArrayList<Farm> farms, ArrayList<Tile> tiles) {
        wholeMapBuilder.reset();
        wholeMapBuilder.setTiles(tiles);
        wholeMapBuilder.setFarms(farms);
        wholeMapBuilder.setGameBuildings(tiles);
        wholeMapBuilder.setGameBuildings(tiles); //TODO
    }
}
