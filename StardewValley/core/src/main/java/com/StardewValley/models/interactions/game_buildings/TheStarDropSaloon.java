package com.StardewValley.models.interactions.game_buildings;

import com.StardewValley.models.Pair;
import com.StardewValley.models.Result;
import com.StardewValley.models.enums.TileAssets;
import com.StardewValley.models.game_structure.Coordinate;
import com.StardewValley.models.game_structure.Tile;
import com.StardewValley.models.goods.Good;
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
    private final ArrayList<Pair<String, Integer>> products;

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
            new Pair<>(FoodType.BEER.getName(), Integer.MAX_VALUE),
            new Pair<>(FoodType.SALAD.getName(), Integer.MAX_VALUE),
            new Pair<>(FoodType.BREAD.getName(), Integer.MAX_VALUE),
            new Pair<>(FoodType.SPAGHETTI.getName(), Integer.MAX_VALUE),
            new Pair<>(FoodType.PIZZA.getName(), Integer.MAX_VALUE),
            new Pair<>(FoodType.COFFEE.getName(), Integer.MAX_VALUE),
            new Pair<>(CookingRecipeType.HASH_BROWNS.getName(), 1),
            new Pair<>(CookingRecipeType.OMELET.getName(), 1),
            new Pair<>(CookingRecipeType.PANCAKES.getName(), 1),
            new Pair<>(CookingRecipeType.BREAD.getName(), 1),
            new Pair<>(CookingRecipeType.TORTILLA.getName(), 1),
            new Pair<>(CookingRecipeType.PIZZA.getName(), 1),
            new Pair<>(CookingRecipeType.MAKI_ROLL.getName(), 1),
            new Pair<>(CookingRecipeType.TRIPLE_SHOT_ESPRESSO.getName(), 1),
            new Pair<>(CookingRecipeType.COOKIE.getName(), 1)
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
        Pair<String, Integer> productPair = null;
        for (Pair<String, Integer> pair : products) {
            if(pair.first().equals(productName)) {
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
        for (Pair<String, Integer> pair : products) {
            if(pair.first().equals(goodType.getName())) {
                return new Pair<>(goodType, pair.second());
            }
        }
        return null;
    }
}
