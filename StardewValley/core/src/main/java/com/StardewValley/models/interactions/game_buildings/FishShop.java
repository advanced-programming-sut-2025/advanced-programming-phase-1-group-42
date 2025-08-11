package com.StardewValley.models.interactions.game_buildings;


import com.StardewValley.models.Pair;
import com.StardewValley.models.Result;
import com.StardewValley.models.enums.TileAssets;
import com.StardewValley.models.game_structure.Coordinate;
import com.StardewValley.models.game_structure.Tile;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.goods.products.ProductType;
import com.StardewValley.models.goods.recipes.CraftingRecipeType;
import com.StardewValley.models.goods.tools.ToolType;
import com.StardewValley.models.interactions.NPCs.NPC;
import com.StardewValley.models.interactions.NPCs.NPCTypes;
import com.StardewValley.models.interactions.Player;
import com.StardewValley.server.ClientHandler;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Arrays;

public class FishShop extends GameBuilding {
    private final ArrayList<Pair<String, Integer>> products;
    private final ArrayList<Integer> requiredLevel;

    public static ArrayList<Tile> getExpectedTiles(ArrayList<Tile> tiles) {
        return getTiles(tiles, new Coordinate(120, 60));
    }

    public FishShop(ArrayList<Tile> tiles, ArrayList<Player> players) {
        super(tiles,
                "FishShop",
                new NPC(NPCTypes.WILLY, players),
                new Pair<>(9, 17),
                new Coordinate(120, 60),
                new Coordinate(130, 70),
                (TileAssets.FISH_SHOP.getImagePath()));

        this.products = new ArrayList<>(Arrays.asList(
                new Pair<>(CraftingRecipeType.FISH_SMOKER.getName(), 1),
                new Pair<>(ProductType.TROUT_SOUP.getName(), 1),
                new Pair<>(ToolType.BAMBOO_FISHING_POLE.getName(), 1),
                new Pair<>(ToolType.TRAINING_FISHING_POLE.getName(), 1),
                new Pair<>(ToolType.FIBERGLASS_FISHING_POLE.getName(), 1),
                new Pair<>(ToolType.IRIDIUM_FISHING_POLE.getName(), 1)
        ));

        this.requiredLevel = new ArrayList<>(Arrays.asList(
                Integer.MAX_VALUE,
                Integer.MAX_VALUE,
                Integer.MAX_VALUE,
                Integer.MAX_VALUE,
                2,
                4
        ));


    }

    @Override
    public ArrayList<GoodType> showAllProducts() {
        return FishShop.getWholeGoodType(products);
    }

    @Override
    public ArrayList<GoodType> showProducts() {

        return FishShop.getWholeGoodType(products);
    }

    @Override
    public Result purchase(String productName, String count, ClientHandler clientHandler) {
        Pair<String, Integer> productPair = null;
        for(Pair<String, Integer> pair : products) {
            if(pair.first().equals(productName)) {
                productPair = pair;
                break;
            }
        }

        if(productPair == null)
            return new Result(false, "There is no Good of this type in FishShop Shop!");


        if(productPair.first().equals(CraftingRecipeType.FISH_SMOKER.getName()) ||
            productPair.first().equals(ProductType.TROUT_SOUP.getName())) {
                return purchaseProduct(productName, count, productPair, clientHandler);
        }
        else {
            if(clientHandler.getClientPlayer().getInventory().isInInventory(productPair.first()) != null)
                return new Result(false, "You have this Pole in your inventory!");

            return purchaseProduct(productName, count, productPair, clientHandler);
        }
    }

    public ArrayList<Pair<String, Integer>> getProducts() {
        return products;
    }

    @Override
    public Pair<GoodType, Integer> findProduct(GoodType goodType) {
        for (Pair<String, Integer> pair : products) {
            if(pair.first().equals(goodType.getName())) {
                return new Pair<>(Good.newGoodType(pair.first()), pair.second());
            }
        }
        return null;
    }
}
