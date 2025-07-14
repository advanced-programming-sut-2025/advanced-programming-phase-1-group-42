package org.example.models.builders.builder_interfaces;

import org.example.models.game_structure.DateTime;
import org.example.models.game_structure.Game;
import org.example.models.game_structure.Tomorrow;
import org.example.models.game_structure.weathers.Weather;
import org.example.models.interactions.Player;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface GameInterface {
    public void reset();
    public Game getGame();
    public void setPlayers(ArrayList<Player> players);
    public void setAdminPlayer(Player player);
    public void setCurrentPlayer(Player player);
    public void setWeather();
    public void setDateTime();
    public void setTomorrow();
    public void setNPCs();
}
