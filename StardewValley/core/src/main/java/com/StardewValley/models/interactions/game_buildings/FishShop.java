package com.StardewValley.models.interactions.game_buildings;

import com.StardewValley.client.AppClient;
import com.StardewValley.models.Pair;
import com.StardewValley.models.Result;
import com.StardewValley.models.enums.TileAssets;
import com.StardewValley.models.game_structure.Coordinate;
import com.StardewValley.models.game_structure.Tile;
import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.goods.products.ProductType;
import com.StardewValley.models.goods.recipes.CraftingRecipeType;
import com.StardewValley.models.goods.tools.ToolType;
import com.StardewValley.models.interactions.NPCs.NPC;
import com.StardewValley.models.interactions.NPCs.NPCTypes;

import java.util.ArrayList;
import java.util.Arrays;

public class FishShop extends GameBuilding {
    private final ArrayList<Pair<GoodType, Integer>> products;
    private final ArrayList<Integer> requiredLevel;

    public static ArrayList<Tile> getExpectedTiles(ArrayList<Tile> tiles) {
        return getTiles(tiles, new Coordinate(120, 60));
    }

    public FishShop(ArrayList<Tile> tiles) {
        super(tiles,
                "FishShop",
                new NPC(NPCTypes.WILLY),
                new Pair<>(9, 17),
                new Coordinate(120, 60),
                new Coordinate(130, 70),
                TileAssets.FISH_SHOP.getTexture());

        this.products = new ArrayList<>(Arrays.asList(
                new Pair<>(CraftingRecipeType.FISH_SMOKER, 1),
                new Pair<>(ProductType.TROUT_SOUP, 1),
                new Pair<>(ToolType.BAMBOO_FISHING_POLE, 1),
                new Pair<>(ToolType.TRAINING_FISHING_POLE, 1),
                new Pair<>(ToolType.FIBERGLASS_FISHING_POLE, 1),
                new Pair<>(ToolType.IRIDIUM_FISHING_POLE, 1)
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
    public Result purchase(String productName, String count) {
        Pair<GoodType, Integer> productPair = null;
        for(Pair<GoodType, Integer> pair : products) {
            if(pair.first().getName().equals(productName)) {
                productPair = pair;
                break;
            }
        }

        if(productPair == null)
            return new Result(false, "There is no Good of this type in FishShop Shop!");


        if(productPair.first() == CraftingRecipeType.FISH_SMOKER ||
            productPair.first() == ProductType.TROUT_SOUP) {
                return purchaseProduct(productName, count, productPair);
        }
        else {
            if(AppClient.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(productPair.first()) != null)
                return new Result(false, "You have this Pole in your inventory!");

            return purchaseProduct(productName, count, productPair);
        }
    }

    public ArrayList<Pair<GoodType, Integer>> getProducts() {
        return products;
    }

    @Override
    public Pair<GoodType, Integer> findProduct(GoodType goodType) {
        for (Pair<GoodType, Integer> pair : products) {
            if(pair.first() == goodType) {
                return pair;
            }
        }
        return null;
    }
}
