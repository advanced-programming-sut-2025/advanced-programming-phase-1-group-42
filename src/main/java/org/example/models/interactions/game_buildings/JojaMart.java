package org.example.models.interactions.game_buildings;

import org.example.models.App;
import org.example.models.Pair;
import org.example.models.Result;
import org.example.models.game_structure.Coordinate;
import org.example.models.game_structure.Tile;
import org.example.models.goods.Good;
import org.example.models.goods.GoodType;
import org.example.models.goods.craftings.CraftingType;
import org.example.models.goods.foods.FoodType;
import org.example.models.goods.foragings.ForagingSeedType;
import org.example.models.goods.products.ProductType;
import org.example.models.interactions.NPCs.NPC;
import org.example.models.interactions.NPCs.NPCTypes;

import java.util.ArrayList;
import java.util.Arrays;

public class JojaMart extends GameBuilding {
    private final ArrayList<ArrayList<Pair<GoodType, Integer>>> products;
    private final ArrayList<String> stockParts;

    public static ArrayList<Tile> getExpectedTiles(ArrayList<Tile> tiles) {
        return getTiles(tiles, 45, 55);
    }

    public JojaMart(ArrayList<Tile> tiles) {
        super(tiles,
                "JojaMart",
                new NPC(NPCTypes.MORRIS),
                new Pair<>(9, 23),
                new Coordinate(45, 55),
                new Coordinate(65, 75));

        this.products = new ArrayList<>();
        //Permanent Stock
        this.products.add(new ArrayList<>(Arrays.asList(
                new Pair(ProductType.JOJA_MART, Integer.MAX_VALUE),
                new Pair(ForagingSeedType.ANCIENT_SEEDS, 1),
                new Pair(CraftingType.GRASS_STARTER, Integer.MAX_VALUE),
                new Pair(FoodType.SUGAR, Integer.MAX_VALUE),
                new Pair(FoodType.WHEAT_FLOUR, Integer.MAX_VALUE),
                new Pair(FoodType.RICE, Integer.MAX_VALUE)
        )));

        // Spring Stock
        this.products.add(new ArrayList<>(Arrays.asList(
                new Pair(ForagingSeedType.PARSNIP_SEEDS, 5),
                new Pair(ForagingSeedType.BEAN_STARTER, 5),
                new Pair(ForagingSeedType.CAULIFLOWER_SEEDS, 5),
                new Pair(ForagingSeedType.POTATO_SEEDS, 5),
                new Pair(ForagingSeedType.STRAWBERRY_SEEDS, 5),
                new Pair(ForagingSeedType.TULIP_BULB, 5),
                new Pair(ForagingSeedType.KALE_SEEDS, 5),
                new Pair(ForagingSeedType.COFFEE_BEAN_SEEDS, 1),
                new Pair(ForagingSeedType.CARROT_SEEDS, 10),
                new Pair(ForagingSeedType.RHUBARB_SEEDS, 5),
                new Pair(ForagingSeedType.JAZZ_SEEDS, 5)
        )));

        // Summer Stock
        this.products.add(new ArrayList<>(Arrays.asList(
                new Pair(ForagingSeedType.TOMATO_SEEDS, 5),
                new Pair(ForagingSeedType.PEPPER_SEEDS, 5),
                new Pair(ForagingSeedType.WHEAT_SEEDS, 10),
                new Pair(ForagingSeedType.SUMMER_SQUASH_SEEDS, 10),
                new Pair(ForagingSeedType.RADISH_SEEDS, 5),
                new Pair(ForagingSeedType.MELON_SEEDS, 5),
                new Pair(ForagingSeedType.HOPS_STARTER, 5),
                new Pair(ForagingSeedType.POPPY_SEEDS, 5),
                new Pair(ForagingSeedType.SPANGLE_SEEDS, 5),
                new Pair(ForagingSeedType.STARFRUIT_SEEDS, 5),
                new Pair(ForagingSeedType.COFFEE_BEAN_SEEDS, 1),
                new Pair(ForagingSeedType.SUNFLOWER_SEEDS, 5)
        )));

        // Fall Stock
        this.products.add(new ArrayList<>(Arrays.asList(
                new Pair(ForagingSeedType.CORN_SEEDS, 5),
                new Pair(ForagingSeedType.EGGPLANT_SEEDS, 5),
                new Pair(ForagingSeedType.PUMPKIN_SEEDS, 5),
                new Pair(ForagingSeedType.BROCCOLI_SEEDS, 5),
                new Pair(ForagingSeedType.AMARANTH_SEEDS, 5),
                new Pair(ForagingSeedType.GRAPE_STARTER, 5),
                new Pair(ForagingSeedType.BEET_SEEDS, 5),
                new Pair(ForagingSeedType.YAM_SEEDS, 5),
                new Pair(ForagingSeedType.BOK_CHOY_SEEDS, 5),
                new Pair(ForagingSeedType.CRANBERRY_SEEDS, 5),
                new Pair(ForagingSeedType.SUNFLOWER_SEEDS, 5),
                new Pair(ForagingSeedType.FAIRY_SEEDS, 5),
                new Pair(ForagingSeedType.RARE_SEED, 1),
                new Pair(ForagingSeedType.WHEAT_SEEDS, 5)
        )));

        // Winter Stock
        this.products.add(new ArrayList<>(Arrays.asList(
                new Pair(ForagingSeedType.POWDERMELON_SEEDS, 10)
        )));

        this.stockParts = new ArrayList<>(Arrays.asList(
                "Permanent", "Spring", "Summer", "Fall", "Winter"
        ));

    }

    @Override
    public String showAllProducts() {
        StringBuilder list = new StringBuilder();
        list.append("JojaMart All Products:\n");
        int ptr = 0;
        for (ArrayList<Pair<GoodType, Integer>> products : this.products) {
            list.append(stockParts.get(ptr++)).append(" Stock:\n");
            listPartStock(list, products);
        }

        return list.toString();
    }

    @Override
    public String showProducts() {
        StringBuilder list = new StringBuilder();
        list.append("JojaMart Available Products:\n");
        list.append(stockParts.get(0)).append(" Stock:\n");
        listAvailablePartStock(list, products.get(0));

        list.append(stockParts.get(
                App.getCurrentGame().getDateTime().getSeasonOfYear().getValue()
        )).append(" Stock:\n");
        listAvailablePartStock(list, products.get(
                App.getCurrentGame().getDateTime().getSeasonOfYear().getValue()
        ));

        return list.toString();
    }

    @Override
    public Result purchase(String productName, String count) {
        Pair<GoodType, Integer> productPair = null;
        int partNumber = 0;
        for (ArrayList<Pair<GoodType, Integer>> products : this.products) {
            for (Pair<GoodType, Integer> product : products) {
                if(product.first().getName().equals(productName)) {
                    productPair = product;
                    break;
                }
            }
            partNumber++;
        }

        if(productPair == null)
            return new Result(false, "There is no Good of this type in JojaMart Shop!");
        if(partNumber != 0 &&
                partNumber != App.getCurrentGame().getDateTime().getSeasonOfYear().getValue())
            return new Result(false, "This product is not available in season " +
                    App.getCurrentGame().getDateTime().getSeasonOfYear().getName() + " in JojaMart Shop!");

        return purchaseProduct(productName, count, productPair);
    }
}
