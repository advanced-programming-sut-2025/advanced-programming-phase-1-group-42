package org.example.models.interactions.game_buildings;

import org.example.models.App;
import org.example.models.Pair;
import org.example.models.Result;
import org.example.models.game_structure.Coordinate;
import org.example.models.game_structure.Tile;
import org.example.models.goods.GoodType;
import org.example.models.goods.farmings.FarmingTreeSaplingType;
import org.example.models.goods.foods.FoodType;
import org.example.models.goods.foods.artisans.ArtisanType;
import org.example.models.goods.foragings.ForagingSeedType;
import org.example.models.goods.products.ProductType;
import org.example.models.interactions.NPCs.NPC;
import org.example.models.interactions.NPCs.NPCTypes;

import java.util.ArrayList;
import java.util.Arrays;

public class PierreGeneralStore extends GameBuilding {
    private final ArrayList<ArrayList<Pair<GoodType, Integer>>> products;
    private final ArrayList<String> stockParts;

    public static ArrayList<Tile> getExpectedTiles(ArrayList<Tile> tiles) {
        return getTiles(tiles, 75, 55);
    }

    public PierreGeneralStore(ArrayList<Tile> tiles) {
        super(tiles,
                "PierreGeneralStore",
                new NPC(NPCTypes.PIERRE),
                new Pair<>(9, 17),
                new Coordinate(75, 55),
                new Coordinate(95, 75));

        stockParts = new ArrayList<>(Arrays.asList(
                "Year-Round Stock", "Spring", "Summer", "Fall", "Winter", "BackPacks"
        ));

        products = new ArrayList<>();

        // Year-Round Stock
        products.add(new ArrayList<>(Arrays.asList(
                new Pair(FoodType.RICE, Integer.MAX_VALUE),
                new Pair(FoodType.WHEAT_FLOUR, Integer.MAX_VALUE),
                new Pair(ProductType.BOUQUET, 2),
                new Pair(ProductType.WEDDING_RING, 2),
                new Pair(ArtisanType.DEHYDRATOR_RECIPE, 1),
                new Pair(ProductType.GRASS_STARTER_RECIPE, 1),
                new Pair(FoodType.SUGAR, Integer.MAX_VALUE),
                new Pair(ProductType.OIL, Integer.MAX_VALUE),
                new Pair(ArtisanType.VINEGAR, Integer.MAX_VALUE),
                new Pair(ProductType.DELUXE_RETAINING_SOIL, Integer.MAX_VALUE),
                new Pair(ProductType.GRASS_STARTER, Integer.MAX_VALUE),
                new Pair(ProductType.SPEED_GRO, Integer.MAX_VALUE),
                new Pair(FarmingTreeSaplingType.APPLE_SAPLING, Integer.MAX_VALUE),
                new Pair(FarmingTreeSaplingType.APRICOT_SAPLING, Integer.MAX_VALUE),
                new Pair(FarmingTreeSaplingType.CHERRY_SAPLING, Integer.MAX_VALUE),
                new Pair(FarmingTreeSaplingType.ORANGE_SAPLING, Integer.MAX_VALUE),
                new Pair(FarmingTreeSaplingType.PEACH_SAPLING, Integer.MAX_VALUE),
                new Pair(FarmingTreeSaplingType.POMEGRANATE_SAPLING, Integer.MAX_VALUE),
                new Pair(ProductType.BASIC_RETAINING_SOIL, Integer.MAX_VALUE),
                new Pair(ProductType.QUALITY_RETAINING_SOIL, Integer.MAX_VALUE)
        )));

        // Spring stock
        products.add(new ArrayList<>(Arrays.asList(
                new Pair(ForagingSeedType.PARSNIP_SEEDS, 5),
                new Pair(ForagingSeedType.BEAN_STARTER, 5),
                new Pair(ForagingSeedType.CAULIFLOWER_SEEDS, 5),
                new Pair(ForagingSeedType.POTATO_SEEDS, 5),
                new Pair(ForagingSeedType.TULIP_BULB, 5),
                new Pair(ForagingSeedType.KALE_SEEDS, 5),
                new Pair(ForagingSeedType.JAZZ_SEEDS, 5),
                new Pair(ForagingSeedType.GARLIC_SEEDS, 5),
                new Pair(ForagingSeedType.RICE_SHOOT, 5)
        )));

        // Summer stock
        products.add(new ArrayList<>(Arrays.asList(
                new Pair(ForagingSeedType.MELON_SEEDS, 5),
                new Pair(ForagingSeedType.TOMATO_SEEDS, 5),
                new Pair(ForagingSeedType.BLUEBERRY_SEEDS, 5),
                new Pair(ForagingSeedType.PEPPER_SEEDS, 5),
                new Pair(ForagingSeedType.WHEAT_SEEDS, 5),
                new Pair(ForagingSeedType.RADISH_SEEDS, 5),
                new Pair(ForagingSeedType.POPPY_SEEDS, 5),
                new Pair(ForagingSeedType.SPANGLE_SEEDS, 5),
                new Pair(ForagingSeedType.HOPS_STARTER, 5),
                new Pair(ForagingSeedType.CORN_SEEDS, 5),
                new Pair(ForagingSeedType.SUNFLOWER_SEEDS, 5),
                new Pair(ForagingSeedType.RED_CABBAGE_SEEDS, 5)
        )));

        // Fall stock
        products.add(new ArrayList<>(Arrays.asList(
                new Pair(ForagingSeedType.EGGPLANT_SEEDS, 5),
                new Pair(ForagingSeedType.CORN_SEEDS, 5),
                new Pair(ForagingSeedType.PUMPKIN_SEEDS, 5),
                new Pair(ForagingSeedType.BOK_CHOY_SEEDS, 5),
                new Pair(ForagingSeedType.YAM_SEEDS, 5),
                new Pair(ForagingSeedType.CRANBERRY_SEEDS, 5),
                new Pair(ForagingSeedType.SUNFLOWER_SEEDS, 5),
                new Pair(ForagingSeedType.FAIRY_SEEDS, 5),
                new Pair(ForagingSeedType.AMARANTH_SEEDS, 5),
                new Pair(ForagingSeedType.GRAPE_STARTER, 5),
                new Pair(ForagingSeedType.WHEAT_SEEDS, 5),
                new Pair(ForagingSeedType.ARTICHOKE_SEEDS, 5)
        )));

        // Winter stock
        products.add(new ArrayList<>());

        // BackPack
        products.add(new ArrayList<>(Arrays.asList(
                new Pair(ProductType.LARGE_PACK, 1),
                new Pair(ProductType.DELUXE_PACK, 1)
        )));



    }

    @Override
    public String showAllProducts() {
        StringBuilder list = new StringBuilder();
        list.append("Pierre's General Store All Products:\n");
        int ptr = 0;
        for (ArrayList<Pair<GoodType, Integer>> products : products) {
            list.append(stockParts.get(ptr++)).append(" Stock:\n");
            listPartStock(list, products);
        }

        return list.toString();
    }

    @Override
    public String showProducts() {
        StringBuilder list = new StringBuilder();
        list.append("Pierre's General Store Available Products:\n");
        list.append(stockParts.get(0)).append(" Stock:\n");
        listAvailablePartStock(list, products.get(0));

        list.append(stockParts.get(
                App.getCurrentGame().getDateTime().getSeasonOfYear().getValue()
        )).append(" Stock:\n");
        listAvailablePartStock(list, products.get(
                App.getCurrentGame().getDateTime().getSeasonOfYear().getValue()
        ));

        list.append(stockParts.get(5));
        listAvailablePartStock(list, products.get(5));

        return list.toString();
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
        partNumber != App.getCurrentGame().getDateTime().getSeasonOfYear().getValue())
            return new Result(false, "This product is not available in season " +
                    App.getCurrentGame().getDateTime().getSeasonOfYear().getName() + " in Pierre's General Store!");

        if(partNumber == 5) {
            if(!count.matches("-?\\d+") && !count.isEmpty())
                return new Result(false, "Invalid Quantity format!");

            int quantity = (count.isEmpty()) ? 1 : Integer.parseInt(count);
            if(productPair.second() < quantity)
                return new Result(false, productName + "'s stock is less than the quantity you want!");

            if(quantity * productPair.first().getSellPrice() > App.getCurrentGame().getCurrentPlayer().getWallet().getBalance()) {
                return new Result(false, "You don't have enough money in your wallet to purchase this product(s)!");
            }

            if(productPair.first() == ProductType.LARGE_PACK) {
                App.getCurrentGame().getCurrentPlayer().getInventory().increaseCapacity();
            }

            if(productPair.first() == ProductType.DELUXE_PACK) {
                if(App.getCurrentGame().getCurrentPlayer().getInventory().getSize() != 12)
                    return new Result(false, "Your inventory should be large to make your inventory deluxe!");
                App.getCurrentGame().getCurrentPlayer().getInventory().increaseCapacity();
            }

            int totalPrice = quantity * productPair.first().getSellPrice();
            App.getCurrentGame().getCurrentPlayer().getWallet().decreaseBalance(totalPrice);
            if(productPair.second() != Integer.MAX_VALUE)
                productPair.setSecond(productPair.second() - quantity);

            return new Result(true, "Your inventory has been updated to " + productPair.first().getName());

        }
        else {
            return purchaseProduct(productName, count, productPair);
        }
    }
}
