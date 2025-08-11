package com.StardewValley.models.interactions.game_buildings;

import com.StardewValley.models.Pair;
import com.StardewValley.models.Result;
import com.StardewValley.models.enums.TileAssets;
import com.StardewValley.models.game_structure.Coordinate;
import com.StardewValley.models.game_structure.Tile;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.goods.artisans.ArtisanType;
import com.StardewValley.models.goods.craftings.CraftingType;
import com.StardewValley.models.goods.farmings.FarmingTreeSaplingType;
import com.StardewValley.models.goods.foods.FoodType;
import com.StardewValley.models.goods.foragings.ForagingSeedType;
import com.StardewValley.models.goods.products.ProductType;
import com.StardewValley.models.goods.recipes.CraftingRecipeType;
import com.StardewValley.models.interactions.NPCs.NPC;
import com.StardewValley.models.interactions.NPCs.NPCTypes;
import com.StardewValley.models.interactions.Player;
import com.StardewValley.server.ClientHandler;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Arrays;

public class PierreGeneralStore extends GameBuilding {
    private final ArrayList<ArrayList<Pair<String, Integer>>> products;
    private final ArrayList<String> stockParts;

    public static ArrayList<Tile> getExpectedTiles(ArrayList<Tile> tiles) {
        return getTiles(tiles, new Coordinate(80, 60));
    }

    public PierreGeneralStore(ArrayList<Tile> tiles, ArrayList<Player> players) {
        super(tiles,
                "PierreGeneralStore",
                new NPC(NPCTypes.PIERRE, players),
                new Pair<>(9, 17),
                new Coordinate(80, 60),
                new Coordinate(90, 70),
                (TileAssets.PIERRE_SHOP.getImagePath()));

        stockParts = new ArrayList<>(Arrays.asList(
                "Year-Round Stock", "Spring", "Summer", "Fall", "Winter", "BackPacks"
        ));

        products = new ArrayList<>();

        // Year-Round Stock
        products.add(new ArrayList<>(Arrays.asList(
            new Pair<>(FoodType.RICE.getName(), Integer.MAX_VALUE),
            new Pair<>(FoodType.WHEAT_FLOUR.getName(), Integer.MAX_VALUE),
            new Pair<>(ProductType.BOUQUET.getName(), 2),
            new Pair<>(ProductType.WEDDING_RING.getName(), 2),
            new Pair<>(ArtisanType.DEHYDRATOR_RECIPE.getName(), 1),
            new Pair<>(CraftingRecipeType.GRASS_STARTER.getName(), 1),
            new Pair<>(FoodType.SUGAR.getName(), Integer.MAX_VALUE),
            new Pair<>(ProductType.OIL.getName(), Integer.MAX_VALUE),
            new Pair<>(ArtisanType.VINEGAR.getName(), Integer.MAX_VALUE),
            new Pair<>(ProductType.DELUXE_RETAINING_SOIL.getName(), Integer.MAX_VALUE),
            new Pair<>(CraftingType.GRASS_STARTER.getName(), Integer.MAX_VALUE),
            new Pair<>(ProductType.SPEED_GRO.getName(), Integer.MAX_VALUE),
            new Pair<>(FarmingTreeSaplingType.APPLE_SAPLING.getName(), Integer.MAX_VALUE),
            new Pair<>(FarmingTreeSaplingType.APRICOT_SAPLING.getName(), Integer.MAX_VALUE),
            new Pair<>(FarmingTreeSaplingType.CHERRY_SAPLING.getName(), Integer.MAX_VALUE),
            new Pair<>(FarmingTreeSaplingType.ORANGE_SAPLING.getName(), Integer.MAX_VALUE),
            new Pair<>(FarmingTreeSaplingType.PEACH_SAPLING.getName(), Integer.MAX_VALUE),
            new Pair<>(FarmingTreeSaplingType.POMEGRANATE_SAPLING.getName(), Integer.MAX_VALUE),
            new Pair<>(ProductType.BASIC_RETAINING_SOIL.getName(), Integer.MAX_VALUE),
            new Pair<>(ProductType.QUALITY_RETAINING_SOIL.getName(), Integer.MAX_VALUE)
        )));

// Spring stock
        products.add(new ArrayList<>(Arrays.asList(
            new Pair<>(ForagingSeedType.PARSNIP_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.BEAN_STARTER.getName(), 5),
            new Pair<>(ForagingSeedType.CAULIFLOWER_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.POTATO_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.TULIP_BULB.getName(), 5),
            new Pair<>(ForagingSeedType.KALE_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.JAZZ_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.GARLIC_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.RICE_SHOOT.getName(), 5)
        )));

// Summer stock
        products.add(new ArrayList<>(Arrays.asList(
            new Pair<>(ForagingSeedType.MELON_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.TOMATO_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.BLUEBERRY_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.PEPPER_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.WHEAT_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.RADISH_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.POPPY_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.SPANGLE_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.HOPS_STARTER.getName(), 5),
            new Pair<>(ForagingSeedType.CORN_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.SUNFLOWER_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.RED_CABBAGE_SEEDS.getName(), 5)
        )));

// Fall stock
        products.add(new ArrayList<>(Arrays.asList(
            new Pair<>(ForagingSeedType.EGGPLANT_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.CORN_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.PUMPKIN_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.BOK_CHOY_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.YAM_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.CRANBERRY_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.SUNFLOWER_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.FAIRY_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.AMARANTH_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.GRAPE_STARTER.getName(), 5),
            new Pair<>(ForagingSeedType.WHEAT_SEEDS.getName(), 5),
            new Pair<>(ForagingSeedType.ARTICHOKE_SEEDS.getName(), 5)
        )));

// Winter stock
        products.add(new ArrayList<>());

// BackPack
        products.add(new ArrayList<>(Arrays.asList(
            new Pair<>(ProductType.LARGE_PACK.getName(), 1),
            new Pair<>(ProductType.DELUXE_PACK.getName(), 1)
        )));



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
        boolean flag = false;
        for (ArrayList<Pair<String, Integer>> products : products) {
            for (Pair<String, Integer> product : products) {
                if(product.first().equals(productName)) {
                    productPair = product;
                    flag = true;
                    break;
                }
            }
            if (flag)
                break;

            partNumber++;
        }

        if(productPair == null)
            return new Result(false, "There is no Good of this type in Pierre's General Store!");
//        System.out.println(partNumber);
//        System.out.println(clientHandler.getClientGame().getDateTime().getSeasonOfYear().getValue());
        if(partNumber != 0 && partNumber != 5 &&
        partNumber != clientHandler.getClientGame().getDateTime().getSeasonOfYear().getValue())
            return new Result(false, "This product is not available in season " +
                     clientHandler.getClientGame().getDateTime().getSeasonOfYear().getName() + " in Pierre's General Store!");

        if(partNumber == 5) {
            if(!count.matches("-?\\d+") && !count.isEmpty())
                return new Result(false, "Invalid Quantity format!");

            int quantity = (count.isEmpty()) ? 1 : Integer.parseInt(count);
            if(productPair.second() < quantity)
                return new Result(false, productName + "'s stock is less than the quantity you want!");

            if(quantity * Good.newGoodType(productPair.first()).getSellPrice() > clientHandler.getClientPlayer().getWallet().getBalance()) {
                return new Result(false, "You don't have enough money in your wallet to purchase this product(s)!");
            }

            if(productPair.first().equals(ProductType.LARGE_PACK.getName())) {
                clientHandler.getClientPlayer().getInventory().increaseCapacity();
            }

            if(productPair.first().equals(ProductType.DELUXE_PACK.getName())) {
                if(clientHandler.getClientPlayer().getInventory().getSize() != 12)
                    return new Result(false, "Your inventory should be large to make your inventory deluxe!");
                clientHandler.getClientPlayer().getInventory().increaseCapacity();
            }

            int totalPrice = quantity * Good.newGoodType(productPair.first()).getSellPrice();
            clientHandler.getClientPlayer().getWallet().decreaseBalance(totalPrice);
            if(productPair.second() != Integer.MAX_VALUE)
                productPair.setSecond(productPair.second() - quantity);

            return new Result(true, "Your inventory has been updated to " + productPair.first());

        }
        else {
            return purchaseProduct(productName, count, productPair, clientHandler);
        }
    }

    @Override
    public Pair<GoodType, Integer> findProduct(GoodType goodType) {
        for (ArrayList<Pair<String, Integer>> products : products) {
            for (Pair<String, Integer> product : products) {
                if (product.first().equals(goodType.getName())) {
                    return new Pair<>(Good.newGoodType(product.first()), product.second());
                }
            }
        }
        return null;
    }
}
