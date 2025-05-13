package org.example.models.interactions.game_buildings;

import org.example.models.App;
import org.example.models.Pair;
import org.example.models.Result;
import org.example.models.enums.TileType;
import org.example.models.game_structure.Coordinate;
import org.example.models.game_structure.Tile;
import org.example.models.goods.Good;
import org.example.models.goods.GoodType;
import org.example.models.goods.products.Product;
import org.example.models.interactions.Building;
import org.example.models.interactions.NPCs.NPC;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class GameBuilding extends Building {
    private ArrayList<Tile> tiles;
    private String name;
    private NPC shopkeeper;
    private final Pair<Integer, Integer> hours;

    public abstract String showAllProducts();
    public abstract String showProducts();
    public abstract Result purchase(String productName, String count);


    public boolean isInWorkingHours() {
        if(App.getCurrentGame().getDateTime().getTime() < getHours().second()
                || App.getCurrentGame().getDateTime().getTime() > getHours().first())
            return true;
        return false;
    }

    public GameBuilding(ArrayList<Tile> tiles, String name, NPC shopkeeper, Pair<Integer, Integer> hours,
                        Coordinate startCoordinate, Coordinate endCoordinate) {
        super(startCoordinate, endCoordinate);
        this.tiles = tiles;
        this.name = name;
        this.shopkeeper = shopkeeper;
        this.hours = hours;
    }

    public boolean isInBuilding(Coordinate coordinate) {
        for (Tile tile : tiles) {
            if(tile.getCordinate().equals(coordinate))
                return true;
        }
        return false;
    }

    public String getName() {
        return name;
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

    protected static ArrayList<Tile> getTiles(ArrayList<Tile> tiles, int i2, int i3) {
        ArrayList<Tile> upgradeTiles = new ArrayList<>();
        for (int i = i2; i - i2 < 20; i++) {
            for (int j = i3; j - i3 < 20; j++) {
                upgradeTiles.add(tiles.get(i + (j * 150)));
                upgradeTiles.getLast().setTileType(TileType.GAME_BUILDING);
            }
        }
        return upgradeTiles;
    }
}
