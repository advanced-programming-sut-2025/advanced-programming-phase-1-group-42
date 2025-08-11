package com.StardewValley.models.interactions.game_buildings;

import com.StardewValley.models.Pair;
import com.StardewValley.models.Result;
import com.StardewValley.models.enums.TileAssets;
import com.StardewValley.models.game_structure.Coordinate;
import com.StardewValley.models.game_structure.Tile;
import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.goods.foods.FoodType;
import com.StardewValley.models.goods.recipes.CookingRecipeType;
import com.StardewValley.models.interactions.NPCs.NPC;
import com.StardewValley.models.interactions.NPCs.NPCTypes;
import com.StardewValley.models.interactions.Player;
import com.StardewValley.server.ClientHandler;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Arrays;

public class TheStarDropSaloon extends GameBuilding {
    private final ArrayList<Pair<GoodType, Integer>> products;

    public static ArrayList<Tile> getExpectedTiles(ArrayList<Tile> tiles) {
        return getTiles(tiles, new Coordinate(80, 90));
    }

    public TheStarDropSaloon(ArrayList<Tile> tiles, ArrayList<Player> players) {
        super(tiles,
                "TheStarDropSaloon",
                new NPC(NPCTypes.GUS, players),
                new Pair<>(12, 24),
                new Coordinate(80, 90),
                new Coordinate(90, 100),
                (TileAssets.THE_STARDROP_SALOON.getImagePath()));

        // Permanent Products
        this.products = new ArrayList<>(Arrays.asList(
                new Pair<>(FoodType.BEER, Integer.MAX_VALUE),
                new Pair<>(FoodType.SALAD, Integer.MAX_VALUE),
                new Pair<>(FoodType.BREAD, Integer.MAX_VALUE),
                new Pair<>(FoodType.SPAGHETTI, Integer.MAX_VALUE),
                new Pair<>(FoodType.PIZZA, Integer.MAX_VALUE),
                new Pair<>(FoodType.COFFEE, Integer.MAX_VALUE),
                new Pair<>(CookingRecipeType.HASH_BROWNS, 1),
                new Pair<>(CookingRecipeType.OMELET, 1),
                new Pair<>(CookingRecipeType.PANCAKES, 1),
                new Pair<>(CookingRecipeType.BREAD, 1),
                new Pair<>(CookingRecipeType.TORTILLA, 1),
                new Pair<>(CookingRecipeType.PIZZA, 1),
                new Pair<>(CookingRecipeType.MAKI_ROLL, 1),
                new Pair<>(CookingRecipeType.TRIPLE_SHOT_ESPRESSO, 1),
                new Pair<>(CookingRecipeType.COOKIE, 1)
        ));

    }

    @Override
    public ArrayList<GoodType> showAllProducts() {
        return getWholeGoodType(products);
    }

    @Override
    public ArrayList<GoodType> showProducts() {
        return getWholeGoodType(products);
    }

    @Override
    public Result purchase(String productName, String count, ClientHandler clientHandler) {
        Pair<GoodType, Integer> productPair = null;
        for (Pair<GoodType, Integer> pair : products) {
            if(pair.first().getName().equals(productName)) {
                productPair = pair;
                break;
            }
        }

        if(productPair == null)
            return new Result(false, "There is no Good of this type in The StarDrop Saloon!");

        return purchaseProduct(productName, count, productPair, clientHandler);
    }

    @Override
    public Pair<GoodType, Integer> findProduct(GoodType goodType) {
        for (Pair<GoodType, Integer> pair : products) {
            if(pair.first().equals(goodType)) {
                return pair;
            }
        }
        return null;
    }
}
