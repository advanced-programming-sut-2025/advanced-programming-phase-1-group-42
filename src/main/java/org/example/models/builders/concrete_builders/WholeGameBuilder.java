package org.example.models.builders.concrete_builders;

import org.example.models.builders.builder_interfaces.GameInterface;
import org.example.models.enums.WeatherType;
import org.example.models.game_structure.DateTime;
import org.example.models.game_structure.Game;
import org.example.models.game_structure.Tomorrow;
import org.example.models.game_structure.weathers.Weather;
import org.example.models.goods.Good;
import org.example.models.goods.tools.ToolType;
import org.example.models.interactions.NPCs.NPC;
import org.example.models.interactions.NPCs.NPCTypes;
import org.example.models.interactions.Player;

import java.util.ArrayList;

public class WholeGameBuilder implements GameInterface {
    private Game game;

    @Override
    public void reset() {
        this.game = new Game();
    }

    @Override
    public Game getGame() {
        Game finalGame = this.game;
        this.reset();
        return finalGame;
    }

    @Override
    public void setPlayers(ArrayList<Player> players) {
        this.game.setPlayers(players);
        for (Player player : players) {
            player.getInventory().addGoodByObject(Good.newGood(ToolType.HOE));
            player.getInventory().addGoodByObject(Good.newGood(ToolType.PICKAXE));
            player.getInventory().addGoodByObject(Good.newGood(ToolType.AXE));
            player.getInventory().addGoodByObject(Good.newGood(ToolType.WATERING_CAN));
            player.getInventory().addGoodByObject(Good.newGood(ToolType.SCYTHE));
        }
    }

    @Override
    public void setAdminPlayer(Player player) {
        this.game.setGameAdmin(player);
    }

    @Override
    public void setCurrentPlayer(Player player) {
        this.game.setCurrentPlayer(player);
    }

    @Override
    public void setWeather() {
        Weather weather = WeatherType.Sunny.getWeather();
        this.game.setWeather(weather);
    }

    @Override
    public void setDateTime() {
        DateTime dateTime = new DateTime();
        this.game.setDateTime(dateTime);
    }

    @Override
    public void setTomorrow() {
        Tomorrow tomorrow = new Tomorrow();
        tomorrow.setTomorrowWeather(game);
        this.game.setTomorrow(tomorrow);
    }

    @Override
    public void setNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();
        for (NPCTypes value : NPCTypes.values()) {
            npcs.add(new NPC(value));
        }
        this.game.setNPCs(npcs);
    }
}
