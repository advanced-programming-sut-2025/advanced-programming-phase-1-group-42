package org.example.models.builders;

import org.example.models.builders.concrete_builders.WholeGameBuilder;
import org.example.models.builders.concrete_builders.WholeMapBuilder;
import org.example.models.game_structure.Farm;
import org.example.models.interactions.Player;

import java.util.ArrayList;

public class Director {
    public void createNewGame(WholeGameBuilder wholeGameBuilder, ArrayList<Player> players) {
        wholeGameBuilder.reset();
        wholeGameBuilder.setPlayers(players);
        //TODO

    }

    public void createNewMap(WholeMapBuilder wholeMapBuilder, ArrayList<Farm> farms) {
        wholeMapBuilder.reset();
        wholeMapBuilder.setFarms(farms);
        wholeMapBuilder.setGameBuildings();
        //TODO
    }
}
