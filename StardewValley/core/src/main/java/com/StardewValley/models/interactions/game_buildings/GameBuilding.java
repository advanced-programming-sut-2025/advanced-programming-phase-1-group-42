package com.StardewValley.models.interactions.game_buildings;

import com.StardewValley.models.App;
import com.StardewValley.models.Pair;
import com.StardewValley.models.Result;
import com.StardewValley.models.enums.TileType;
import com.StardewValley.models.game_structure.Coordinate;
import com.StardewValley.models.game_structure.Tile;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.GoodType;
import com.StardewValley.models.interactions.Building;
import com.StardewValley.models.interactions.NPCs.NPC;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public abstract class GameBuilding extends Building {
    private NPC shopkeeper;
    private final Pair<Integer, Integer> hours;
    private final Texture texture;

    public abstract ArrayList<GoodType> showAllProducts();
    public abstract ArrayList<GoodType> showProducts();
    public abstract Result purchase(String productName, String count);
    public abstract Pair<GoodType, Integer> findProduct(GoodType goodType);


    public boolean isInWorkingHours() {
        if(App.getCurrentGame().getDateTime().getTime() < getHours().second()
                || App.getCurrentGame().getDateTime().getTime() > getHours().first())
            return true;
        return false;
    }

    public GameBuilding(ArrayList<Tile> tiles, String name, NPC shopkeeper, Pair<Integer, Integer> hours,
                        Coordinate startCoordinate, Coordinate endCoordinate, Texture texture) {
        super(startCoordinate, endCoordinate, tiles, name);
        this.shopkeeper = shopkeeper;
        this.hours = hours;
        this.texture = texture;
    }

    public boolean isInBuilding(Coordinate coordinate) {
        if(startCoordinate.getX() <= coordinate.getX() &&
                endCoordinate.getX() > coordinate.getX() &&
                startCoordinate.getY() <= coordinate.getY() &&
                endCoordinate.getY() > coordinate.getY())
            return true;
        return false;
    }

    public Texture getTexture() {
        return texture;
    }

    public Pair<Integer, Integer> getHours() {
        return hours;
    }

    protected static void listPartStock(StringBuilder list, ArrayList<Pair<GoodType, Integer>> products) {
        for (Pair<GoodType, Integer> product : products) {
            list.append("\t>> Name : ").append(product.first().getName()).append(", Stock: ");
            if(product.second() == Integer.MAX_VALUE)
                list.append("Unlimited\n");
            else
                list.append(product.second()).append("\n");
        }
        list.append("\n");
    }


    protected static void listAvailablePartStock(StringBuilder list, ArrayList<Pair<GoodType, Integer>> products) {
        for (Pair<GoodType, Integer> product : products) {
            list.append("\t>> Name : ").append(product.first().getName()).append(", Stock: ");
            if(product.second() == Integer.MAX_VALUE)
                list.append("Unlimited\n");
            else if(product.second() > 0)
                list.append(product.second()).append("\n");
        }
        list.append("\n");
    }

    protected static Result purchaseProduct(String productName, String count, Pair<GoodType, Integer> productPair) {
        if(!count.matches("-?\\d+") && !count.isEmpty())
            return new Result(false, "Invalid Quantity format!");

        int quantity = (count.isEmpty()) ? 1 : Integer.parseInt(count);
        if(productPair.second() < quantity)
            return new Result(false, productName + "'s stock is less than the quantity you want!");

        if(quantity * productPair.first().getSellPrice() > App.getCurrentGame().getCurrentPlayer().getWallet().getBalance()) {
            return new Result(false, "You don't have enough money in your wallet to purchase this product(s)!");
        }
        if(App.getCurrentGame().getCurrentPlayer().getInventory().isInInventory(productName) == null &&
                App.getCurrentGame().getCurrentPlayer().getInventory().isFull())
            return new Result(false, "Your inventory is full to purchase this product(s)!");

        int totalPrice = quantity * productPair.first().getSellPrice();
        App.getCurrentGame().getCurrentPlayer().getWallet().decreaseBalance(totalPrice);
        App.getCurrentGame().getCurrentPlayer().getInventory().addGood(Good.newGoods(productPair.first(), quantity));


        if(productPair.second() != Integer.MAX_VALUE)
            productPair.setSecond(productPair.second() - quantity);

        return new Result(true, productName + " " + quantity + "x stock purchased!");
    }

    protected static ArrayList<Tile> getTiles(ArrayList<Tile> tiles, Coordinate startCoordinate) {
        ArrayList<Tile> upgradeTiles = new ArrayList<>();
        for (int i = startCoordinate.getX(); i - startCoordinate.getX() < 10; i++) {
            for (int j = startCoordinate.getY(); j - startCoordinate.getY() < 10; j++) {
                upgradeTiles.add(tiles.get((i * 160) + j));
                upgradeTiles.getLast().setTileType(TileType.GAME_BUILDING);
            }
        }
        return upgradeTiles;
    }

    static ArrayList<GoodType> getGoodTypes(ArrayList<ArrayList<Pair<GoodType, Integer>>> products) {
        ArrayList<GoodType> goodTypes = new ArrayList<>();
        for (ArrayList<Pair<GoodType, Integer>> seasonProducts : products) {
            for (Pair<GoodType, Integer> product : seasonProducts) {
                goodTypes.add(product.first());
            }
        }

        return goodTypes;
    }

    static ArrayList<GoodType> getSeasonProducts(ArrayList<ArrayList<Pair<GoodType, Integer>>> products) {
        ArrayList<GoodType> goodTypes = new ArrayList<>();
        for (Pair<GoodType, Integer> goodTypeIntegerPair : products.get(0)) {
            goodTypes.add(goodTypeIntegerPair.first());
        }
        for (Pair<GoodType, Integer> goodTypeIntegerPair : products.get(
            App.getCurrentGame().getDateTime().getSeasonOfYear().getValue())) {
            goodTypes.add(goodTypeIntegerPair.first());
        }

        return goodTypes;
    }

    static ArrayList<GoodType> getWholeGoodType(ArrayList<Pair<GoodType, Integer>> products) {
        ArrayList<GoodType> goodTypes = new ArrayList<>();
        for (Pair<GoodType, Integer> product : products) {
            goodTypes.add(product.first());
        }
        return goodTypes;
    }
}
