package org.example.models.builders.concrete_builders;

import org.example.models.builders.builder_interfaces.GameInterface;
import org.example.models.game_structure.Game;
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
    }

    @Override
    public void setAdminPlayer(Player player) {
        this.game.setGameAdmin(player);
    }
}
