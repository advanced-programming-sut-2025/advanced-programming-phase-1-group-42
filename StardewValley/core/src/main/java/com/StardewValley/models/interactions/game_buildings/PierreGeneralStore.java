package com.StardewValley.models.interactions.game_buildings;

import com.StardewValley.client.AppClient;
import com.StardewValley.models.Pair;
import com.StardewValley.models.Result;
import com.StardewValley.models.enums.TileAssets;
import com.StardewValley.models.game_structure.Coordinate;
import com.StardewValley.models.game_structure.Tile;
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

import java.util.ArrayList;
import java.util.Arrays;

public class PierreGeneralStore extends GameBuilding {
    private final ArrayList<ArrayList<Pair<GoodType, Integer>>> products;
    private final ArrayList<String> stockParts;

    public static ArrayList<Tile> getExpectedTiles(ArrayList<Tile> tiles) {
        return getTiles(tiles, new Coordinate(80, 60));
    }

    public PierreGeneralStore(ArrayList<Tile> tiles) {
        super(tiles,
                "PierreGeneralStore",
                new NPC(NPCTypes.PIERRE),
                new Pair<>(9, 17),
                new Coordinate(80, 60),
                new Coordinate(90, 70),
                TileAssets.PIERRE_SHOP.getTexture());

        stockParts = new ArrayList<>(Arrays.asList(
                "Year-Round Stock", "Spring", "Summer", "Fall", "Winter", "BackPacks"
        ));

        products = new ArrayList<>();

        // Year-Round Stock
        products.add(new ArrayList<>(Arrays.asList(
                new Pair<>(FoodType.RICE, Integer.MAX_VALUE),
                new Pair<>(FoodType.WHEAT_FLOUR, Integer.MAX_VALUE),
                new Pair<>(ProductType.BOUQUET, 2),
                new Pair<>(ProductType.WEDDING_RING, 2),
                new Pair<>(ArtisanType.DEHYDRATOR_RECIPE, 1),
                new Pair<>(CraftingRecipeType.GRASS_STARTER, 1),
                new Pair<>(FoodType.SUGAR, Integer.MAX_VALUE),
                new Pair<>(ProductType.OIL, Integer.MAX_VALUE),
                new Pair<>(ArtisanType.VINEGAR, Integer.MAX_VALUE),
                new Pair<>(ProductType.DELUXE_RETAINING_SOIL, Integer.MAX_VALUE),
                new Pair<>(CraftingType.GRASS_STARTER, Integer.MAX_VALUE),
                new Pair<>(ProductType.SPEED_GRO, Integer.MAX_VALUE),
                new Pair<>(FarmingTreeSaplingType.APPLE_SAPLING, Integer.MAX_VALUE),
                new Pair<>(FarmingTreeSaplingType.APRICOT_SAPLING, Integer.MAX_VALUE),
                new Pair<>(FarmingTreeSaplingType.CHERRY_SAPLING, Integer.MAX_VALUE),
                new Pair<>(FarmingTreeSaplingType.ORANGE_SAPLING, Integer.MAX_VALUE),
                new Pair<>(FarmingTreeSaplingType.PEACH_SAPLING, Integer.MAX_VALUE),
                new Pair<>(FarmingTreeSaplingType.POMEGRANATE_SAPLING, Integer.MAX_VALUE),
                new Pair<>(ProductType.BASIC_RETAINING_SOIL, Integer.MAX_VALUE),
                new Pair<>(ProductType.QUALITY_RETAINING_SOIL, Integer.MAX_VALUE)
        )));

        // Spring stock
        products.add(new ArrayList<>(Arrays.asList(
                new Pair<>(ForagingSeedType.PARSNIP_SEEDS, 5),
                new Pair<>(ForagingSeedType.BEAN_STARTER, 5),
                new Pair<>(ForagingSeedType.CAULIFLOWER_SEEDS, 5),
                new Pair<>(ForagingSeedType.POTATO_SEEDS, 5),
                new Pair<>(ForagingSeedType.TULIP_BULB, 5),
                new Pair<>(ForagingSeedType.KALE_SEEDS, 5),
                new Pair<>(ForagingSeedType.JAZZ_SEEDS, 5),
                new Pair<>(ForagingSeedType.GARLIC_SEEDS, 5),
                new Pair<>(ForagingSeedType.RICE_SHOOT, 5)
        )));

        // Summer stock
        products.add(new ArrayList<>(Arrays.asList(
                new Pair<>(ForagingSeedType.MELON_SEEDS, 5),
                new Pair<>(ForagingSeedType.TOMATO_SEEDS, 5),
                new Pair<>(ForagingSeedType.BLUEBERRY_SEEDS, 5),
                new Pair<>(ForagingSeedType.PEPPER_SEEDS, 5),
                new Pair<>(ForagingSeedType.WHEAT_SEEDS, 5),
                new Pair<>(ForagingSeedType.RADISH_SEEDS, 5),
                new Pair<>(ForagingSeedType.POPPY_SEEDS, 5),
                new Pair<>(ForagingSeedType.SPANGLE_SEEDS, 5),
                new Pair<>(ForagingSeedType.HOPS_STARTER, 5),
                new Pair<>(ForagingSeedType.CORN_SEEDS, 5),
                new Pair<>(ForagingSeedType.SUNFLOWER_SEEDS, 5),
                new Pair<>(ForagingSeedType.RED_CABBAGE_SEEDS, 5)
        )));

        // Fall stock
        products.add(new ArrayList<>(Arrays.asList(
                new Pair<>(ForagingSeedType.EGGPLANT_SEEDS, 5),
                new Pair<>(ForagingSeedType.CORN_SEEDS, 5),
                new Pair<>(ForagingSeedType.PUMPKIN_SEEDS, 5),
                new Pair<>(ForagingSeedType.BOK_CHOY_SEEDS, 5),
                new Pair<>(ForagingSeedType.YAM_SEEDS, 5),
                new Pair<>(ForagingSeedType.CRANBERRY_SEEDS, 5),
                new Pair<>(ForagingSeedType.SUNFLOWER_SEEDS, 5),
                new Pair<>(ForagingSeedType.FAIRY_SEEDS, 5),
                new Pair<>(ForagingSeedType.AMARANTH_SEEDS, 5),
                new Pair<>(ForagingSeedType.GRAPE_STARTER, 5),
                new Pair<>(ForagingSeedType.WHEAT_SEEDS, 5),
                new Pair<>(ForagingSeedType.ARTICHOKE_SEEDS, 5)
        )));

        // Winter stock
        products.add(new ArrayList<>());

        // BackPack
        products.add(new ArrayList<>(Arrays.asList(
                new Pair<>(ProductType.LARGE_PACK, 1),
                new Pair<>(ProductType.DELUXE_PACK, 1)
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
    public Result purchase(String productName, String count) {
        Pair<GoodType, Integer> productPair = null;
        int partNumber = 0;
        for (ArrayList<Pair<GoodType, Integer>> products : products) {
            for (Pair<GoodType, Integer> product : products) {
                if(product.first().getName().equals(productName)) {
                    productPair = product;
                    break;
                }
            }
            partNumber++;
        }

        if(productPair == null)
            return new Result(false, "There is no Good of this type in Pierre's General Store!");
        if(partNumber != 0 && partNumber != 5 &&
        partNumber != AppClient.getCurrentGame().getDateTime().getSeasonOfYear().getValue())
            return new Result(false, "This product is not available in season " +
                    AppClient.getCurrentGame().getDateTime().getSeasonOfYear().getName() + " in Pierre's General Store!");

        if(partNumber == 5) {
            if(!count.matches("-?\\d+") && !count.isEmpty())
                return new Result(false, "Invalid Quantity format!");

            int quantity = (count.isEmpty()) ? 1 : Integer.parseInt(count);
            if(productPair.second() < quantity)
                return new Result(false, productName + "'s stock is less than the quantity you want!");

            if(quantity * productPair.first().getSellPrice() > AppClient.getCurrentGame().getCurrentPlayer().getWallet().getBalance()) {
                return new Result(false, "You don't have enough money in your wallet to purchase this product(s)!");
            }

            if(productPair.first() == ProductType.LARGE_PACK) {
                AppClient.getCurrentGame().getCurrentPlayer().getInventory().increaseCapacity();
            }

            if(productPair.first() == ProductType.DELUXE_PACK) {
                if(AppClient.getCurrentGame().getCurrentPlayer().getInventory().getSize() != 12)
                    return new Result(false, "Your inventory should be large to make your inventory deluxe!");
                AppClient.getCurrentGame().getCurrentPlayer().getInventory().increaseCapacity();
            }

            int totalPrice = quantity * productPair.first().getSellPrice();
            AppClient.getCurrentGame().getCurrentPlayer().getWallet().decreaseBalance(totalPrice);
            if(productPair.second() != Integer.MAX_VALUE)
                productPair.setSecond(productPair.second() - quantity);

            return new Result(true, "Your inventory has been updated to " + productPair.first().getName());

        }
        else {
            return purchaseProduct(productName, count, productPair);
        }
    }

    @Override
    public Pair<GoodType, Integer> findProduct(GoodType goodType) {
        for (ArrayList<Pair<GoodType, Integer>> products : products) {
            for (Pair<GoodType, Integer> product : products) {
                if (product.first().getName().equals(goodType.getName())) {
                    return product;
                }
            }
        }
        return null;
    }
}
