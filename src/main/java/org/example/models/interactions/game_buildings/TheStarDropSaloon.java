package org.example.models.interactions.game_buildings;

import org.example.models.Pair;
import org.example.models.Result;
import org.example.models.game_structure.Coordinate;
import org.example.models.game_structure.Tile;
import org.example.models.goods.GoodType;
import org.example.models.goods.foods.FoodType;
import org.example.models.goods.recipes.CookingRecipeType;
import org.example.models.interactions.NPCs.NPC;
import org.example.models.interactions.NPCs.NPCTypes;

import java.util.ArrayList;
import java.util.Arrays;

public class TheStarDropSaloon extends GameBuilding {
    private final ArrayList<Pair<GoodType, Integer>> products;

    public static ArrayList<Tile> getExpectedTiles(ArrayList<Tile> tiles) {
        return getTiles(tiles, 75, 85);
    }

    public TheStarDropSaloon(ArrayList<Tile> tiles) {
        super(tiles,
                "TheStarDropSaloon",
                new NPC(NPCTypes.GUS),
                new Pair<>(12, 24),
                new Coordinate(75, 85),
                new Coordinate(95, 105));

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
    public String showAllProducts() {
        StringBuilder list = new StringBuilder();
        list.append("The StarDrop Saloon All Products:\n");
        listPartStock(list, products);

        return list.toString();
    }

    @Override
    public String showProducts() {
        StringBuilder list = new StringBuilder();
        list.append("The StarDrop Saloon Available Products:\n");
        listAvailablePartStock(list, products);

        return list.toString();
    }

    @Override
    public Result purchase(String productName, String count) {
        Pair<GoodType, Integer> productPair = null;
        for (Pair<GoodType, Integer> pair : products) {
            if(pair.first().getName().equals(productName)) {
                productPair = pair;
                break;
            }
        }

        if(productPair == null)
            return new Result(false, "There is no Good of this type in The StarDrop Saloon!");

        return purchaseProduct(productName, count, productPair);
    }
}
