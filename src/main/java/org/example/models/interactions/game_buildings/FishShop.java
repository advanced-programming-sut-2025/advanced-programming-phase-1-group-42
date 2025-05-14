package org.example.models.interactions.game_buildings;

import org.example.models.App;
import org.example.models.Pair;
import org.example.models.Result;
import org.example.models.game_structure.Coordinate;
import org.example.models.game_structure.Tile;
import org.example.models.goods.Good;
import org.example.models.goods.GoodType;
import org.example.models.goods.craftings.CraftingType;
import org.example.models.goods.fishs.FishType;
import org.example.models.goods.products.ProductType;
import org.example.models.goods.recipes.CraftingRecipeType;
import org.example.models.goods.tools.ToolType;
import org.example.models.interactions.NPCs.NPC;
import org.example.models.interactions.NPCs.NPCTypes;

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
                new Coordinate(130, 70));

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
    public String showAllProducts() {
        StringBuilder list = new StringBuilder();
        list.append("Fish Shop All Products:\n");
        listPartStock(list, products);

        return list.toString();
    }

    @Override
    public String showProducts() {
        StringBuilder list = new StringBuilder();
        list.append("Fish Shop Available Products:\n");
        listAvailablePartStock(list, products);

        return list.toString();
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
            if(App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(productPair.first()) != null)
                return new Result(false, "You have this Pole in your inventory!");

            return purchaseProduct(productName, count, productPair);
        }
    }
}
