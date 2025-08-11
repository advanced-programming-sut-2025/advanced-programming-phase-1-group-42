package com.StardewValley.models.interactions.game_buildings;

import com.StardewValley.models.Pair;
import com.StardewValley.models.Result;
import com.StardewValley.models.enums.TileAssets;
import com.StardewValley.models.game_structure.Coordinate;
import com.StardewValley.models.game_structure.Tile;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.goods.craftings.CraftingType;
import com.StardewValley.models.goods.foods.FoodType;
import com.StardewValley.models.goods.foragings.ForagingSeedType;
import com.StardewValley.models.goods.products.ProductType;
import com.StardewValley.models.interactions.NPCs.NPC;
import com.StardewValley.models.interactions.NPCs.NPCTypes;
import com.StardewValley.models.interactions.Player;
import com.StardewValley.server.ClientHandler;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Arrays;

public class JojaMart extends GameBuilding {
    private final ArrayList<ArrayList<Pair<String, Integer>>> products;
    private final ArrayList<String> stockParts;

    public static ArrayList<Tile> getExpectedTiles(ArrayList<Tile> tiles) {
        return getTiles(tiles, new Coordinate(50, 60));
    }

    public JojaMart(ArrayList<Tile> tiles, ArrayList<Player> players) {
        super(tiles,
                "JojaMart",
                new NPC(NPCTypes.MORRIS, players),
                new Pair<>(9, 23),
                new Coordinate(50, 60),
                new Coordinate(60, 70),
                (TileAssets.JOJAMART.getImagePath()));

        this.products = new ArrayList<>();
        //Permanent Stock
        this.products.add(new ArrayList<>(Arrays.asList(
                new Pair<>(ProductType.JOJA_MART.getName(), Integer.MAX_VALUE),
                new Pair<>(ForagingSeedType.ANCIENT_SEEDS.getName(), 1),
                new Pair<>(CraftingType.GRASS_STARTER.getName(), Integer.MAX_VALUE),
                new Pair<>(FoodType.SUGAR.getName(), Integer.MAX_VALUE),
                new Pair<>(FoodType.WHEAT_FLOUR.getName(), Integer.MAX_VALUE),
                new Pair<>(FoodType.RICE.getName(), Integer.MAX_VALUE)
        )));

        // Spring Stock
        this.products.add(new ArrayList<>(Arrays.asList(
                new Pair<>(ForagingSeedType.PARSNIP_SEEDS.getName(), 5),
                new Pair<>(ForagingSeedType.BEAN_STARTER.getName(), 5),
                new Pair<>(ForagingSeedType.CAULIFLOWER_SEEDS.getName(), 5),
                new Pair<>(ForagingSeedType.POTATO_SEEDS.getName(), 5),
                new Pair<>(ForagingSeedType.STRAWBERRY_SEEDS.getName(), 5),
                new Pair<>(ForagingSeedType.TULIP_BULB.getName(), 5),
                new Pair<>(ForagingSeedType.KALE_SEEDS.getName(), 5),
                new Pair<>(ForagingSeedType.COFFEE_BEAN_SEEDS.getName(), 1),
                new Pair<>(ForagingSeedType.CARROT_SEEDS.getName(), 10),
                new Pair<>(ForagingSeedType.RHUBARB_SEEDS.getName(), 5),
                new Pair<>(ForagingSeedType.JAZZ_SEEDS.getName(), 5)
        )));

        // Summer Stock
        this.products.add(new ArrayList<>(Arrays.asList(
            new Pair<>(ForagingSeedType.TOMATO_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.PEPPER_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.WHEAT_SEEDS.getName(), 10),
            new Pair<>(ForagingSeedType.SUMMER_SQUASH_SEEDS.getName(), 10),
            new Pair<>(ForagingSeedType.RADISH_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.MELON_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.HOPS_STARTER.getName(), 5),
            new Pair<>(ForagingSeedType.POPPY_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.SPANGLE_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.STARFRUIT_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.COFFEE_BEAN_SEEDS.getName(), 1),
            new Pair<>(ForagingSeedType.SUNFLOWER_SEEDS.getName(), 5)
        )));

// Fall Stock
        this.products.add(new ArrayList<>(Arrays.asList(
            new Pair<>(ForagingSeedType.CORN_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.EGGPLANT_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.PUMPKIN_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.BROCCOLI_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.AMARANTH_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.GRAPE_STARTER.getName(), 5),
            new Pair<>(ForagingSeedType.BEET_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.YAM_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.BOK_CHOY_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.CRANBERRY_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.SUNFLOWER_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.FAIRY_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.RARE_SEED.getName(), 1),
            new Pair<>(ForagingSeedType.WHEAT_SEEDS.getName(), 5)
        )));

// Winter Stock
        this.products.add(new ArrayList<>(Arrays.asList(
            new Pair<>(ForagingSeedType.POWDERMELON_SEEDS.getName(), 10)
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
        Pair<String, Integer> productPair = null;
        int partNumber = 0;
        for (ArrayList<Pair<String, Integer>> products : this.products) {
            for (Pair<String, Integer> product : products) {
                if(product.first().equals(productName)) {
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
        for (ArrayList<Pair<String, Integer>> products : this.products) {
            for (Pair<String, Integer> product : products) {
                if (product.first().equals(goodType.getName())) {
                    return new Pair<>(Good.newGoodType(product.first()), product.second());
                }
            }
        }
        return null;
    }
}
