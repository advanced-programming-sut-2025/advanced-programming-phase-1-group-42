package com.StardewValley.models.interactions.game_buildings;

import com.StardewValley.models.Pair;
import com.StardewValley.models.Result;
import com.StardewValley.models.enums.TileAssets;
import com.StardewValley.models.game_structure.Coordinate;
import com.StardewValley.models.game_structure.Tile;
import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.goods.craftings.CraftingType;
import com.StardewValley.models.goods.foods.FoodType;
import com.StardewValley.models.goods.foragings.ForagingSeedType;
import com.StardewValley.models.goods.products.ProductType;
import com.StardewValley.models.interactions.NPCs.NPC;
import com.StardewValley.models.interactions.NPCs.NPCTypes;
import com.StardewValley.server.ClientHandler;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Arrays;

public class JojaMart extends GameBuilding {
    private final ArrayList<ArrayList<Pair<GoodType, Integer>>> products;
    private final ArrayList<String> stockParts;

    public static ArrayList<Tile> getExpectedTiles(ArrayList<Tile> tiles) {
        return getTiles(tiles, new Coordinate(50, 60));
    }

    public JojaMart(ArrayList<Tile> tiles) {
        super(tiles,
                "JojaMart",
                new NPC(NPCTypes.MORRIS),
                new Pair<>(9, 23),
                new Coordinate(50, 60),
                new Coordinate(60, 70),
                (TileAssets.JOJAMART.getImagePath()));

        this.products = new ArrayList<>();
        //Permanent Stock
        this.products.add(new ArrayList<>(Arrays.asList(
                new Pair<>(ProductType.JOJA_MART, Integer.MAX_VALUE),
                new Pair<>(ForagingSeedType.ANCIENT_SEEDS, 1),
                new Pair<>(CraftingType.GRASS_STARTER, Integer.MAX_VALUE),
                new Pair<>(FoodType.SUGAR, Integer.MAX_VALUE),
                new Pair<>(FoodType.WHEAT_FLOUR, Integer.MAX_VALUE),
                new Pair<>(FoodType.RICE, Integer.MAX_VALUE)
        )));

        // Spring Stock
        this.products.add(new ArrayList<>(Arrays.asList(
                new Pair<>(ForagingSeedType.PARSNIP_SEEDS, 5),
                new Pair<>(ForagingSeedType.BEAN_STARTER, 5),
                new Pair<>(ForagingSeedType.CAULIFLOWER_SEEDS, 5),
                new Pair<>(ForagingSeedType.POTATO_SEEDS, 5),
                new Pair<>(ForagingSeedType.STRAWBERRY_SEEDS, 5),
                new Pair<>(ForagingSeedType.TULIP_BULB, 5),
                new Pair<>(ForagingSeedType.KALE_SEEDS, 5),
                new Pair<>(ForagingSeedType.COFFEE_BEAN_SEEDS, 1),
                new Pair<>(ForagingSeedType.CARROT_SEEDS, 10),
                new Pair<>(ForagingSeedType.RHUBARB_SEEDS, 5),
                new Pair<>(ForagingSeedType.JAZZ_SEEDS, 5)
        )));

        // Summer Stock
        this.products.add(new ArrayList<>(Arrays.asList(
                new Pair<>(ForagingSeedType.TOMATO_SEEDS, 5),
                new Pair<>(ForagingSeedType.PEPPER_SEEDS, 5),
                new Pair<>(ForagingSeedType.WHEAT_SEEDS, 10),
                new Pair<>(ForagingSeedType.SUMMER_SQUASH_SEEDS, 10),
                new Pair<>(ForagingSeedType.RADISH_SEEDS, 5),
                new Pair<>(ForagingSeedType.MELON_SEEDS, 5),
                new Pair<>(ForagingSeedType.HOPS_STARTER, 5),
                new Pair<>(ForagingSeedType.POPPY_SEEDS, 5),
                new Pair<>(ForagingSeedType.SPANGLE_SEEDS, 5),
                new Pair<>(ForagingSeedType.STARFRUIT_SEEDS, 5),
                new Pair<>(ForagingSeedType.COFFEE_BEAN_SEEDS, 1),
                new Pair<>(ForagingSeedType.SUNFLOWER_SEEDS, 5)
        )));

        // Fall Stock
        this.products.add(new ArrayList<>(Arrays.asList(
                new Pair<>(ForagingSeedType.CORN_SEEDS, 5),
                new Pair<>(ForagingSeedType.EGGPLANT_SEEDS, 5),
                new Pair<>(ForagingSeedType.PUMPKIN_SEEDS, 5),
                new Pair<>(ForagingSeedType.BROCCOLI_SEEDS, 5),
                new Pair<>(ForagingSeedType.AMARANTH_SEEDS, 5),
                new Pair<>(ForagingSeedType.GRAPE_STARTER, 5),
                new Pair<>(ForagingSeedType.BEET_SEEDS, 5),
                new Pair<>(ForagingSeedType.YAM_SEEDS, 5),
                new Pair<>(ForagingSeedType.BOK_CHOY_SEEDS, 5),
                new Pair<>(ForagingSeedType.CRANBERRY_SEEDS, 5),
                new Pair<>(ForagingSeedType.SUNFLOWER_SEEDS, 5),
                new Pair<>(ForagingSeedType.FAIRY_SEEDS, 5),
                new Pair<>(ForagingSeedType.RARE_SEED, 1),
                new Pair<>(ForagingSeedType.WHEAT_SEEDS, 5)
        )));

        // Winter Stock
        this.products.add(new ArrayList<>(Arrays.asList(
                new Pair<>(ForagingSeedType.POWDERMELON_SEEDS, 10)
        )));

        this.stockParts = new ArrayList<>(Arrays.asList(
                "Permanent", "Spring", "Summer", "Fall", "Winter"
        ));

    }

    @Override
    public ArrayList<GoodType> showAllProducts() {
        return getGoodTypes(products);
    }

    @Override
    public ArrayList<GoodType> showProducts() {
        return getSeasonProducts(products);
    }

    @Override
    public Result purchase(String productName, String count, ClientHandler clientHandler) {
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
                partNumber != clientHandler.getClientGame().getDateTime().getSeasonOfYear().getValue())
            return new Result(false, "This product is not available in season " +
                    clientHandler.getClientGame().getDateTime().getSeasonOfYear().getName() + " in JojaMart Shop!");

        return purchaseProduct(productName, count, productPair, clientHandler);
    }

    @Override
    public Pair<GoodType, Integer> findProduct(GoodType goodType) {
        for (ArrayList<Pair<GoodType, Integer>> products : this.products) {
            for (Pair<GoodType, Integer> product : products) {
                if (product.first().getName().equals(goodType.getName())) {
                    return product;
                }
            }
        }
        return null;
    }
}
