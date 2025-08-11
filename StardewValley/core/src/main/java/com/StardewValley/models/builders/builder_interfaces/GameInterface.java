package com.StardewValley.models.builders.builder_interfaces;

import com.StardewValley.models.game_structure.Game;
import com.StardewValley.models.interactions.Player;

import java.util.ArrayList;

public interface GameInterface {
    public void reset();
    public Game getGame();
    public void setPlayers(ArrayList<Player> players);
    public void setAdminPlayer(Player player);
    public void setWeather();
    public void setDateTime();
    public void setTomorrow();
    public void setNPCs(ArrayList<Player> players);
    public void setGameID(int gameID);
}
