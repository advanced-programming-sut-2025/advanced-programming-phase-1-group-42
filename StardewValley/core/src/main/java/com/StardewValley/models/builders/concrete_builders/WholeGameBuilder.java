package com.StardewValley.models.builders.concrete_builders;

import com.StardewValley.models.Assets;
import com.StardewValley.models.builders.builder_interfaces.GameInterface;
import com.StardewValley.models.enums.WeatherType;
import com.StardewValley.models.game_structure.DateTime;
import com.StardewValley.models.game_structure.Game;
import com.StardewValley.models.game_structure.Tomorrow;
import com.StardewValley.models.game_structure.weathers.Weather;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.recipes.CookingRecipeType;
import com.StardewValley.models.goods.recipes.CraftingRecipeType;
import com.StardewValley.models.goods.tools.ToolType;
import com.StardewValley.models.interactions.NPCs.NPC;
import com.StardewValley.models.interactions.NPCs.NPCTypes;
import com.StardewValley.models.interactions.Player;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

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
            player.getInventory().addGoodByObject(Good.newGood(ToolType.HOE), game, player);
            player.getInventory().addGoodByObject(Good.newGood(ToolType.PICKAXE), game, player);
            player.getInventory().addGoodByObject(Good.newGood(ToolType.AXE), game, player);
            player.getInventory().addGoodByObject(Good.newGood(ToolType.WATERING_CAN), game, player);
            player.getInventory().addGoodByObject(Good.newGood(ToolType.SCYTHE), game, player);
//            player.getInventory().addGoodByObject(Good.newGood(CraftingRecipeType.BOMB));
//            player.getInventory().addGoodByObject(Good.newGood(CookingRecipeType.BREAD));
            player.setInHandGood(player.getInventory().getList().getFirst());
        }
    }

    @Override
    public void setAdminPlayer(Player player) {
        this.game.setGameAdmin(player);
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
    public void setNPCs(ArrayList<Player> players) {
        ArrayList<NPC> npcs = new ArrayList<>();
        for (NPCTypes value : NPCTypes.values()) {
            npcs.add(new NPC(value, players));
        }
        this.game.setNPCs(npcs);
    }

    @Override
    public void setGameID(int gameID) {
        this.game.setGameID(gameID);
    }
}
