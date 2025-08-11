package com.StardewValley.models.game_structure.weathers;

import com.StardewValley.client.AppClient;
import com.StardewValley.models.enums.TileType;
import com.StardewValley.models.game_structure.Coordinate;
import com.StardewValley.models.game_structure.Game;
import com.StardewValley.models.game_structure.Tile;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.farmings.FarmingTree;
import com.StardewValley.models.goods.farmings.FarmingTreeSapling;
import com.StardewValley.models.goods.foragings.ForagingMineralType;
import com.StardewValley.models.goods.foragings.ForagingTree;
import com.StardewValley.server.ClientHandler;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Weather {
    public void thunder(int x, int y, ClientHandler handler) {
        // TODO Parsa
        Coordinate coordinate = new Coordinate(x, y);
        if(handler.getClientPlayer().getCoordinate().equals(coordinate)){
            System.out.println("You've Been Struck by Thunder!");
        } else {
            // Null checks first
            if (handler.getClientGame() == null || handler.getClientGame().getMap() == null) {
                System.err.println("Error: Game or map not loaded.");
                return;
            }

            Tile tile = handler.getClientGame().getMap().findTile(coordinate, handler.getClientGame());
            if (tile == null) {
                System.err.println("Error: Tile not found at coordinate " + coordinate);
                return;
            }

            System.out.println("Thunder has been struck");

            // Skip if GREEN_HOUSE
            if (TileType.GREEN_HOUSE.equals(tile.getTileType())) {
                System.out.println("Green House tiles are protected.");
                return;
            }


            // Single message variable (avoid duplication)
            final String TREE_FALLEN_MSG = "A Tree has been Fallen By Thor";

            // Remove all tree types and replace with coal
            List<Good> treesToRemove = tile.getGoods().stream()
                    .filter(good -> good instanceof ForagingTree
                            || good instanceof FarmingTree
                            || good instanceof FarmingTreeSapling)
                    .collect(Collectors.toList());

            treesToRemove.forEach(tree -> {
                System.out.println(TREE_FALLEN_MSG);
                tile.addGoodToTile(Good.newGood(ForagingMineralType.COAL));
                tile.getGoods().remove(tree); // Safe because we're not iterating
            });
        }
    }
    public abstract double getWeatherEffectingEnergy();
    public abstract double getFishChance();

    public abstract String getName();


}
