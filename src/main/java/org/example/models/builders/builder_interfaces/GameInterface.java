package org.example.models.builders.builder_interfaces;

import org.example.models.game_structure.Game;
import org.example.models.interactions.Player;

import java.util.ArrayList;

public interface GameInterface {
    public void reset();
    public Game getGame();
    public void setPlayers(ArrayList<Player> players);
    public void setAdminPlayer(Player player);
    //TODO : After building the game on DB, we will complete this section
}
