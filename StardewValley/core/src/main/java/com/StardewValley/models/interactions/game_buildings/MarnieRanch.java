package com.StardewValley.models.interactions.game_buildings;

import com.StardewValley.models.App;
import com.StardewValley.models.Pair;
import com.StardewValley.models.Result;
import com.StardewValley.models.game_structure.Coordinate;
import com.StardewValley.models.game_structure.Tile;
import com.StardewValley.models.goods.Good;
import com.StardewValley.models.goods.products.Product;
import com.StardewValley.models.goods.products.ProductType;
import com.StardewValley.models.interactions.Animals.Animal;
import com.StardewValley.models.interactions.Animals.AnimalTypes;
import com.StardewValley.models.interactions.NPCs.NPC;
import com.StardewValley.models.interactions.NPCs.NPCTypes;

import java.util.ArrayList;
import java.util.List;

public class MarnieRanch extends GameBuilding {

    public ArrayList<AnimalTypes> animals = new ArrayList<>(
            List.of(AnimalTypes.RABBIT,
                    AnimalTypes.COW,
                    AnimalTypes.GOAT,
                    AnimalTypes.CHICKEN,
                    AnimalTypes.DINOSAUR,
                    AnimalTypes.DUCK,
                    AnimalTypes.SHEEP,
                    AnimalTypes.PIG)
    );

    public static ArrayList<Tile> getExpectedTiles(ArrayList<Tile> tiles) {
        return getTiles(tiles, new Coordinate(50, 90));
    }


    public MarnieRanch(ArrayList<Tile> tiles) {
        super(tiles,
                "MarineRanch",
                new NPC(NPCTypes.MARNIE),
                new Pair<>(9, 16),
                new Coordinate(50, 90),
                new Coordinate(60, 100));
    }


    @Override
    public String showAllProducts() {
        for (AnimalTypes animalType : animals) {
            System.out.println(animalType.getName() + "  " + animalType.getPrice()+" G");
        }
        return null;
    }

    @Override
    public String showProducts() {
        return "";
    }

    @Override
    public Result purchase(String productName, String count) {
        int countInt = Integer.parseInt(count);
        ProductType type = null;
        if (productName.equals("Hay")) {
            type = ProductType.HAY;
            if (!(App.getCurrentGame().getCurrentPlayer().getWallet().getBalance() > countInt * type.getSellPrice())) {
                return new Result(false, "You don't have enough money");
            } else {
                if (!App.getCurrentGame().getCurrentPlayer().getInventory().addGood((Good) new Product(ProductType.WOOD), countInt)) {
                    return new Result(false, "Your inventory is full");
                } else {
                    App.getCurrentGame().getCurrentPlayer().getWallet().decreaseBalance(countInt * type.getSellPrice());
                }
                return new Result(true, "You bought " + countInt + " Wood");
            }
        } else if (productName.equals("Shears")) {
            type = ProductType.SHEARS;
            if (!(App.getCurrentGame().getCurrentPlayer().getWallet().getBalance() > countInt * type.getSellPrice())) {
                return new Result(false, "You don't have enough money");
            } else {
                if (!App.getCurrentGame().getCurrentPlayer().getInventory().addGood((Good) new Product(ProductType.WOOD), countInt)) {
                    return new Result(false, "Your inventory is full");
                } else {
                    App.getCurrentGame().getCurrentPlayer().getWallet().decreaseBalance(countInt * type.getSellPrice());
                }
                return new Result(true, "You bought " + countInt + " Wood");
            }
        } else if (productName.equals("Milk Pail")) {
            type = ProductType.MILK_PAIL;
            if (!(App.getCurrentGame().getCurrentPlayer().getWallet().getBalance() > countInt * type.getSellPrice())) {
                return new Result(false, "You don't have enough money");
            } else {
                if (!App.getCurrentGame().getCurrentPlayer().getInventory().addGood((Good) new Product(ProductType.WOOD), countInt)) {
                    return new Result(false, "Your inventory is full");
                } else {
                    App.getCurrentGame().getCurrentPlayer().getWallet().decreaseBalance(countInt * type.getSellPrice());
                }
                return new Result(true, "You bought " + countInt + " Wood");
            }
        } else {
            return new Result(false, "This item is not available in this shop");
        }
    }

    public AnimalTypes getAnimalType(String animalType) {
        for (AnimalTypes type : animals) {
            if (type.name().equals(animalType)) {
                return type;
            }
        }
        return null;
    }

    public Animal buildAnimal(AnimalTypes animalType,String animalName) {
        return new Animal(animalType,animalName);
    }
}
